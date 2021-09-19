package basis.bsb.sga.web.rest.excecoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessagemCampo implements Serializable {

    private String campo;
    private String mensagem;

}
