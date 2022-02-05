package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioSelectMapper extends EntityMapper<SelectDTO, Usuario> {

    @Override
    @Mapping(source = "id", target = "value")
    @Mapping(source = "nome", target = "label")
    SelectDTO toDto(Usuario usuario);

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(SelectDTO dto);
}
