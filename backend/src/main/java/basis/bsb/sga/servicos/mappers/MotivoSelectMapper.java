package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface MotivoSelectMapper extends EntityMapper<SelectDTO, Motivo> {


    @Mapping(source = "id", target = "value")
    @Mapping(source = "titulo", target = "label")
    SelectDTO toDto(Motivo motivo);

    @InheritInverseConfiguration
    Motivo toEntity(SelectDTO dto);
}
