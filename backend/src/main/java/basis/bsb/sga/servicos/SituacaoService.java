package basis.bsb.sga.servicos;


import basis.bsb.sga.repositorios.SituacaoRepositorio;
import basis.bsb.sga.servicos.dtos.SituacaoDTO;
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

    private final SituacaoMapper mapper;

    public List<SituacaoDTO> buscarTodos(){
        return mapper.toDto(repositorio.findAll());
    }
}
