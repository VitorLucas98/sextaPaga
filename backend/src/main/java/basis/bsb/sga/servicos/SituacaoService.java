package basis.bsb.sga.servicos;


import basis.bsb.sga.repositorios.SituacaoRepositorio;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import basis.bsb.sga.servicos.dtos.SituacaoDTO;
import basis.bsb.sga.servicos.mappers.SitacaoSelectMapper;
import basis.bsb.sga.servicos.mappers.SituacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SituacaoService {

    private final SituacaoRepositorio repositorio;

    private final SitacaoSelectMapper  mapper;

    public List<SelectDTO> buscarTodos(){
        return mapper.toDto(repositorio.findAll());
    }
}
