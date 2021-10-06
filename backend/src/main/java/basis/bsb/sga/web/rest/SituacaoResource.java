package basis.bsb.sga.web.rest;

import basis.bsb.sga.servicos.SituacaoService;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import basis.bsb.sga.servicos.dtos.SituacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "api/situacoes")
@RequiredArgsConstructor
public class SituacaoResource {

    private final SituacaoService service;

    @GetMapping
    public ResponseEntity<List<SelectDTO>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }
}
