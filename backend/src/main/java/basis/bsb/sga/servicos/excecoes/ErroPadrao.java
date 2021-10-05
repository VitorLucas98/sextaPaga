package basis.bsb.sga.servicos.excecoes;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroPadrao implements Serializable {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDoErro;
    private Integer status;
    private String erro;
    private String mensagem;
    private String caminho;
}
