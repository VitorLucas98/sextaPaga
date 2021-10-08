package basis.bsb.sga.servicos.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MotivoDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Titulo invalido")
    private String titulo;

    @NotBlank(message = "Descrição invalido")
    private String descricao;
}