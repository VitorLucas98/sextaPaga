package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.servicos.dtos.UsuarioListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { CargoSelectMapper.class})
public interface UsuarioListagemMapper extends EntityMapper<UsuarioListagemDTO, Usuario> {

}
