package basis.bsb.sga.servicos.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioListagemDTO implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private boolean status;
    private SelectDTO cargo;
}
