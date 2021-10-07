package basis.bsb.sga.servicos.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MotivoDTO implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
}