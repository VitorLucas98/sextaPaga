package basis.bsb.sga.servicos.mappers;


import basis.bsb.sga.dominio.Situacao;
import basis.bsb.sga.servicos.dtos.SituacaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SituacaoMapper extends EntityMapper<SituacaoDTO, Situacao>{
}
