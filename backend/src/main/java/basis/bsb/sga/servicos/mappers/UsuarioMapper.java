package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario>{

    @Override
    @Mapping(source = "idCargo", target = "cargo.id")
    Usuario toEntity(UsuarioDTO dto);

    @Override
    @InheritInverseConfiguration
    UsuarioDTO toDto(Usuario entity);

}
