package basis.bsb.sga.servicos;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.repositorios.EventoRepositorio;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.servicos.excecoes.ObjetoNaoEncontrado;
import basis.bsb.sga.servicos.mappers.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServico {

    private final EventoRepositorio repositorio;

    private final EventoMapper mapper;

    public EventoDTO buscarPorId(Long id){
        Evento eve = repositorio.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado("Evento de id: "+id+" não existe"));
        return mapper.toDto(eve);
    }

    public List<EventoDTO> buscarTodos(){
        return mapper.toDto(repositorio.findAll());
    }

    public EventoDTO inserir(EventoDTO dto){
        Evento entity = mapper.toEntity(dto);
        entity = repositorio.save(entity);
        return mapper.toDto(entity);
    }

    public EventoDTO editar(EventoDTO dto, Long id){
        dto.setId(id);
        Evento entity = mapper.toEntity(dto);
        entity = repositorio.save(entity);
        return mapper.toDto(entity);
    }

    public void adiarEvento(Long id){
    }

}