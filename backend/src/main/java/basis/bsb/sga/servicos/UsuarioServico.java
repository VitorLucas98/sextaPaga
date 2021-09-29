package basis.bsb.sga.servicos;

import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.repositorios.UsuarioRepositorio;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import basis.bsb.sga.servicos.dtos.UsuarioListagemDTO;
import basis.bsb.sga.servicos.excecoes.ObjetoNaoEncontrado;
import basis.bsb.sga.servicos.excecoes.ValidadorExcecoes;
import basis.bsb.sga.servicos.filtros.UsuarioFiltro;
import basis.bsb.sga.servicos.mappers.UsuarioListagemMapper;
import basis.bsb.sga.servicos.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio repositorio;

    private final UsuarioListagemMapper listagemMapper;

    private final EventoServico eventoServico;

    private final UsuarioMapper mapper;

    public List<UsuarioListagemDTO> buscarTodosFiltrado(UsuarioFiltro filtro) {
        return listagemMapper.toDto(repositorio.findAll(filtro.filter()));
    }
    public UsuarioDTO buscarPorId(Long id){
        Usuario usuario = repositorio.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado("Usuario do id: "+id+" não encontrado !"));
        return mapper.toDto(usuario);
    }

    public UsuarioDTO inserir(UsuarioDTO dto){
        validadorCPF(dto);
        validadorEmail(dto);
        dto.setStatus(true);
        Usuario usuario = mapper.toEntity(dto);
        usuario = repositorio.save(usuario);
        return mapper.toDto(usuario);
    }

    public UsuarioDTO editar(UsuarioDTO dto, Long id){
        UsuarioDTO usuarioDTO = buscarPorId(id);
        Usuario usuario = mapper.toEntity(dto);
        usuario = repositorio.save(usuario);
        return mapper.toDto(usuario);
    }

    private void validadorEmail(UsuarioDTO dto){
        boolean validacao = repositorio.existsByEmail(dto.getEmail());
        if (validacao){
            throw new ValidadorExcecoes("Email já cadastrado");
        }
    }

    private void validadorCPF(UsuarioDTO dto){
        boolean validacao = repositorio.existsByCpf(dto.getCpf());
        if (validacao){
            throw new ValidadorExcecoes("CPF já cadastrado");
        }
    }

    public UsuarioDTO ativarStatus(Long id){
        UsuarioDTO dto = buscarPorId(id);
        dto.setStatus(true);
        editar(dto, id);
        return dto;
    }

    public UsuarioDTO desativarStatus(Long id){
        UsuarioDTO dto = buscarPorId(id);
        eventoServico.checkUsuario(mapper.toEntity(dto));
        dto.setStatus(false);
        editar(dto, id);
        return dto;
    }


}