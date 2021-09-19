package basis.bsb.sga.web.rest.excecoes;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ErroValidacao extends ErroPadrao{

    private List<MessagemCampo> erros = new ArrayList<>();

    public void addErro(String campo, String mensagem) {
        erros.add(new MessagemCampo(campo, mensagem));
    }
}
