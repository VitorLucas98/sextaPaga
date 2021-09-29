package basis.bsb.sga.web.rest;

import basis.bsb.sga.servicos.SituacaoService;
import basis.bsb.sga.servicos.dtos.SituacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/situacoes")
@RequiredArgsConstructor
public class SituacaoResource {

    private final SituacaoService service;

    @GetMapping
    public ResponseEntity<List<SituacaoDTO>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }
}
