package basis.bsb.sga.web.rest;

import basis.bsb.sga.servicos.MotivoServico;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/motivos")
public class MotivoRecurso {

    private final MotivoServico servico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MotivoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MotivoDTO>>buscarTodos(){
        return ResponseEntity.ok(servico.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<MotivoDTO> inserir(@RequestBody @Valid MotivoDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(servico.inserir(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MotivoDTO> editar(@RequestBody @Valid MotivoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(servico.editar(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
