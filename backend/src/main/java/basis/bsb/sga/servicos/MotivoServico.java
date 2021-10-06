package basis.bsb.sga.servicos;


import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.repositorios.MotivoRepositorio;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import basis.bsb.sga.servicos.excecoes.ObjetoNaoEncontrado;
import basis.bsb.sga.servicos.mappers.MotivoMapper;
import basis.bsb.sga.servicos.mappers.MotivoSelectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MotivoServico  {

    private final MotivoRepositorio repositorio;

    private final MotivoMapper mapper;

    private final MotivoSelectMapper selectMapper;

    public MotivoDTO buscarPorId(Long id){
        Motivo motivo = repositorio.findById(id).orElseThrow( () -> new ObjetoNaoEncontrado("Motivo  não encontrado"));
        return mapper.toDto(motivo);
    }

    public List<MotivoDTO> buscarTodos(){
        return mapper.toDto(repositorio.findAll());
    }

    public List<SelectDTO>buscarTodosSelect(){
        return selectMapper.toDto(repositorio.findAll());
    }

    public MotivoDTO inserir(MotivoDTO dto){
        Motivo entidade = mapper.toEntity(dto);
        entidade = repositorio.save(entidade);
        return mapper.toDto(entidade);
    }

    public MotivoDTO editar(MotivoDTO dto, Long id){
        dto.setId(id);
        Motivo entidade = mapper.toEntity(dto);
        entidade = repositorio.save(entidade);
        return mapper.toDto(entidade);
    }

    public void deletar(Long id){
        try {
            repositorio.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjetoNaoEncontrado("Motivo não encontrado!");
        }
    }
}
