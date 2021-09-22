package basis.bsb.sga.web.rest;


import basis.bsb.sga.servicos.UsuarioServico;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import basis.bsb.sga.servicos.dtos.UsuarioListagemDTO;
import basis.bsb.sga.servicos.filtros.UsuarioFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRecurso {

    private final UsuarioServico service;

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDTO>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping(value = "/filtro")
    public ResponseEntity<List<UsuarioDTO>> buscarTodosFiltrado(UsuarioFiltro filtro){
        return ResponseEntity.ok(service.buscarTodosFiltrado(filtro));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody @Valid UsuarioDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.inserir(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody @Valid UsuarioDTO dto,@PathVariable Long id){
        return ResponseEntity.ok(service.editar(dto, id));
    }

    @PutMapping(value = "/ativar/{id}")
    public ResponseEntity<UsuarioDTO> ativacaoUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.ativarStatus(id));
    }

    @PutMapping(value = "/desativar/{id}")
    public ResponseEntity<UsuarioDTO> desativacaoUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.desativarStatus(id));
    }
}

