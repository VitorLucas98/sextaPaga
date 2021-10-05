package basis.bsb.sga.web.rest;

import basis.bsb.sga.SgaApplication;
import basis.bsb.sga.builder.MotivoBuilder;
import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SgaApplication.class)
public class MotivoRecursoTeste {

private static final String BASE_URL = "/api/motivos";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;

    @Autowired
    private MotivoBuilder motivoBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.motivoBuilder.setCustomizacao(null);
        this.motivoBuilder.delete();
    }

    @Test
    @SneakyThrows
    public void inserir(){
        MotivoDTO motivoDTO = motivoBuilder.construirObjDTO();
        mockMvc.perform(post(BASE_URL)
                .content(TestUtil.convertObjectToJsonBytes(motivoDTO))
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void buscarTodos(){
        Motivo motivo = motivoBuilder.construir();
        mockMvc.perform(get(BASE_URL)
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarPorIdSucesso(){
        Motivo motivo = motivoBuilder.construir();
        String idMotivo =  "/"+motivo.getId();

        mockMvc.perform(get(BASE_URL+idMotivo)
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void atualizacaoComSucesso(){
        Motivo motivoSalvo = motivoBuilder.construir();
        MotivoDTO motivoDTO = motivoBuilder.construirObjDTO();
        mockMvc.perform(put(BASE_URL+"/"+motivoSalvo.getId())
                .content(TestUtil.convertObjectToJsonBytes(motivoDTO))
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());

    }

    @Test
    @SneakyThrows
    public void deletar(){
        Motivo motivo = motivoBuilder.construir();
        mockMvc.perform(delete(BASE_URL+"/"+motivo.getId())
        ).andExpect(status().isNoContent());
    }


}
