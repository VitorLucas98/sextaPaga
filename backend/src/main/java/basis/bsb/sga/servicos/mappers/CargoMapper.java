package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Cargo;
import basis.bsb.sga.servicos.dtos.CargoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper extends EntityMapper<CargoDTO, Cargo>{


}
