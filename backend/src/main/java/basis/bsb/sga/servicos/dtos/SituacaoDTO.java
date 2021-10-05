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
public class SituacaoDTO implements Serializable {

    private Long id;
    private String descricao;

}
