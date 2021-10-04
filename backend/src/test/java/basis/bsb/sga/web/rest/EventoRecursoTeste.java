package basis.bsb.sga.web.rest;

import basis.bsb.sga.SgaApplication;
import basis.bsb.sga.builder.EventoBuilder;
import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.servicos.dtos.EventoDTO;
import basis.bsb.sga.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SgaApplication.class)
public class EventoRecursoTeste {

    private static final String BASE_URL = "/api/eventos";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Object eventoDTO;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.eventoBuilder.setCustomizacao(null);
        this.eventoBuilder.delete();
    }

    @Test
    @SneakyThrows
    public void inserir() {
        EventoDTO eventoDTO = eventoBuilder.construirObjDTO();
        mockMvc.perform(post(BASE_URL)
                .content(TestUtil.convertObjectToJsonBytes(eventoDTO))
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void buscarTodos() {
        Evento evento = eventoBuilder.construir();
        mockMvc.perform(get(BASE_URL+"/filtro")
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarPorIdSucesso() {
        Evento evento = eventoBuilder.construir();
        String idEvento = "/"+evento.getId();

        mockMvc.perform(get(BASE_URL+idEvento)
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void atualizacaoComSucesso() {
        Evento eventoSalvo = eventoBuilder.construir();
        EventoDTO eventAtualizar = eventoBuilder.construirObjDTO();
        eventAtualizar.setNome("Jose da silva");
        eventAtualizar.setDataEvento(LocalDate.now().minusDays(5));
        eventAtualizar.setValor(80.00);
        mockMvc.perform(put(BASE_URL+"/"+eventoSalvo.getId())
                .content(TestUtil.convertObjectToJsonBytes(eventAtualizar))
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());

    }
    @Test
    @SneakyThrows
    public void adiarEvento(){
        Evento eventoSalvo = eventoBuilder.construir();
        mockMvc.perform(put(BASE_URL+"/adiar/"+eventoSalvo.getId())
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNoContent());
    }
    @Test
    @SneakyThrows
    public void trocarEvento(){
        Evento evento1Salvo = eventoBuilder.construir();
        Evento evento2Salvo = eventoBuilder.construir();
        evento2Salvo.setDataEvento(evento1Salvo.getDataEvento().plusDays(7));
        mockMvc.perform(put(BASE_URL+"/trocarevento/"+evento1Salvo.getId()+"/"+evento2Salvo.getId())
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

}


