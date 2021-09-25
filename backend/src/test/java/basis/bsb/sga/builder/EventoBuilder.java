package basis.bsb.sga.builder;


import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.dominio.Situacao;
import basis.bsb.sga.repositorios.EventoRepositorio;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.servicos.mappers.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private EventoMapper eventoMapper;

    @Override
    protected Evento construirEntidade() throws ParseException {
        Evento evento = new Evento();
        evento.setNome("Francisco Doglas");
        evento.setDataEvento(LocalDate.now().minusDays(6));
        evento.setValor(100.00);
        /*
        Motivo motivo = new Motivo();
        motivo.setId(1L);
        evento.setMotivo(motivo);

        Situacao situacao = new Situacao();
        situacao.setId(1L);
        evento.setSituacao(situacao);
        */
        return evento;
    }

    @Override
    protected Evento persistir(Evento entidade) {
        return eventoRepositorio.save(entidade);
    }

    @Override
    protected Collection<Evento> obterTodos() {
        return eventoRepositorio.findAll();
    }

    @Override
    protected Evento obterPorId(Long id) {
        return eventoRepositorio.findById(id).get();
    }

    @Override
    public Evento construir() throws ParseException {
        return super.construir();
    }


    public EventoDTO construirDTO() throws ParseException {
        return eventoMapper.toDto(construir());
    }


    public EventoDTO construirObjDTO() throws ParseException {
        return eventoMapper.toDto(construirEntidade());
    }


    public void delete() {
        eventoRepositorio.deleteAll();
    }

}
