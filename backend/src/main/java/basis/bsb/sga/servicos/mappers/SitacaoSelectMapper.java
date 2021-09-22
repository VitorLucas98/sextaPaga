package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Situacao;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SitacaoSelectMapper extends EntityMapper<SelectDTO, Situacao> {

    @Override
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descricao", target = "label")
    SelectDTO toDto(Situacao situacao);

    @Override
    @InheritInverseConfiguration
    Situacao toEntity(SelectDTO dto);
}
