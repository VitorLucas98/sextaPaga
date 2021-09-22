package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SitacaoSelectMapper.class, UsuarioMapper.class})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

}