package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        MotivoSelectMapper.class,
        SitacaoSelectMapper.class,
        UsuarioSelectMapper.class
        })
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

}