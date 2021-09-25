package basis.bsb.sga.servicos;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.repositorios.EventoRepositorio;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.servicos.excecoes.ObjetoNaoEncontrado;
import basis.bsb.sga.servicos.excecoes.ValidadorExcecoes;
import basis.bsb.sga.servicos.filtros.EventoFiltro;
import basis.bsb.sga.servicos.mappers.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServico {

    private final EventoRepositorio repositorio;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventoServico.class);
    private final EventoMapper mapper;

    public EventoDTO buscarPorId(Long id) {
        Evento eve = repositorio.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado("Evento de id: " + id + " não existe"));
        return mapper.toDto(eve);
    }

    public List<EventoDTO> buscarTodosFiltrado(EventoFiltro filtro) {
        return mapper.toDto(repositorio.findAll(filtro.filter()));
    }

    public List<EventoDTO> buscarTodos() {
        return mapper.toDto(repositorio.findAll());
    }

    public EventoDTO inserir(EventoDTO dto) {
        validaDataEvento(dto);
        Evento entity = mapper.toEntity(dto);
        entity = repositorio.save(entity);
        return mapper.toDto(entity);
    }

    public EventoDTO editar(EventoDTO dto, Long id) {
        dto.setId(id);
        validaDataEvento(dto);
        Evento entity = mapper.toEntity(dto);
        entity = repositorio.save(entity);
        return mapper.toDto(entity);
    }

    public void adiarEvento(Long id) {
        Evento evento = mapper.toEntity(buscarPorId(id));
        List<Evento> eventos = repositorio.findByDateAlter(evento.getDataEvento());
        eventos.stream().forEach(x -> {
                    x.setDataEvento(x.getDataEvento().plusDays(7));
                    repositorio.save(x);
                }
        );
    }

    public List<EventoDTO> trocarEventos(Long idPri, Long idSec) {
        Evento ev1 = mapper.toEntity(buscarPorId(idPri));
        Evento ev2 = mapper.toEntity(buscarPorId(idSec));
        LocalDate dataAux = ev1.getDataEvento();
        ev1.setDataEvento(ev2.getDataEvento());
        ev2.setDataEvento(dataAux);
        return mapper.toDto(repositorio.saveAll(Arrays.asList(ev1, ev2)));
    }

    public void checkUsuario(Usuario usuario){
        List<Evento> eventos = repositorio.getAllByUsuarios(usuario);
        List<Usuario> usuarios = new ArrayList<>();
        for (Evento evento : eventos){
            usuarios = evento.getUsuarios();
            if (usuarios.size() == 1){
                repositorio.delete(evento);
            }else {
                usuarios.remove(usuario);
                evento.setUsuarios(usuarios);
                repositorio.save(evento);
            }
        }
    }

    private void validaDataEvento(EventoDTO dto) {
        if (repositorio.existsByDataEvento(dto.getDataEvento()) && dto.getId() == null) {
            throw new ValidadorExcecoes("Não é possivel cadastrar um evento no mesmo dia que outro evento");
        }
    }

}