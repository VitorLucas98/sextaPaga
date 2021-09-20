package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Override
    @Mapping(source = "idSituacao", target = "situacao.id")
    Evento toEntity(EventoDTO dto);

    @Override
    @InheritInverseConfiguration
    EventoDTO toDto(Evento entity);
}