package basis.bsb.sga.web.rest;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.servicos.EventoServico;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.servicos.filtros.EventoFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/eventos")
public class EventoRecurso {

    private final EventoServico servico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @GetMapping(value = "/filtro")
    public ResponseEntity<List<EventoDTO>> buscarTodosFiltrado(EventoFiltro filto){
        return ResponseEntity.ok(servico.buscarTodosFiltrado(filto));
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> buscarTodos(){
        return ResponseEntity.ok(servico.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<EventoDTO> inserir(@RequestBody @Valid EventoDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(servico.inserir(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventoDTO> editar(@RequestBody @Valid EventoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(servico.editar(dto, id));
    }

    @PutMapping(value = "/adiar/{id}")
    public ResponseEntity<Void> adiarEvento(@PathVariable Long id){
        servico.adiarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/trocarevento/{idPri}/{idSec}")
    public ResponseEntity<List<EventoDTO>> trocarEvento(@PathVariable Long idPri,@PathVariable Long idSec){
        return ResponseEntity.ok(servico.trocarEventos(idPri, idSec));
    }
}