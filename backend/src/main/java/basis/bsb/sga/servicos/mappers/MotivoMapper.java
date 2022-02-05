package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MotivoMapper extends EntityMapper<MotivoDTO, Motivo>{
}
