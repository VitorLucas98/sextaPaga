package basis.bsb.sga.servicos.mappers;

import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.servicos.dtos.UsuarioListagemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsuarioListagemMapper extends EntityMapper<UsuarioListagemDTO, Usuario> {

    @Override
    @Mapping(source = "idCargo", target = "cargo.id")
    Usuario toEntity(UsuarioListagemDTO dto);

    @Override
    @InheritInverseConfiguration
    UsuarioListagemDTO toDto(Usuario entity);
}
