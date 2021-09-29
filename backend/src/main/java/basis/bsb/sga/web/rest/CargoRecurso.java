package basis.bsb.sga.web.rest;

import basis.bsb.sga.servicos.CargoServico;
import basis.bsb.sga.servicos.dtos.CargoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cargos")
public class CargoRecurso {

    private final CargoServico servico;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> listarTodos() {
        return ResponseEntity.ok(servico.listarTodos());
    }
}