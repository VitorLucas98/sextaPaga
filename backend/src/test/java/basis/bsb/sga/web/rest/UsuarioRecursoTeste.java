package basis.bsb.sga.web.rest;

import basis.bsb.sga.SgaApplication;
import basis.bsb.sga.builder.UsuarioBuilder;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import basis.bsb.sga.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SgaApplication.class)
public class UsuarioRecursoTeste {

    private static final String BASE_URL = "/api/usuarios";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.usuarioBuilder.setCustomizacao(null);
        this.usuarioBuilder.delete();
    }

    @Test
    @SneakyThrows
    public void inserir(){
        UsuarioDTO usuarioDTO = usuarioBuilder.construirDTO();
        mockMvc.perform(post(BASE_URL)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDTO))
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isCreated());
    }

    @Test
    public void buscarTodos(){

    }


}
