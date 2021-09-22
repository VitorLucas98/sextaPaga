package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Cargo;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoSelectMapper extends EntityMapper<SelectDTO, Cargo> {

    @Override
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descricao", target = "label")
    SelectDTO toDto(Cargo entity);

    @Override
    @InheritInverseConfiguration
    Cargo toEntity(SelectDTO dto);

}
