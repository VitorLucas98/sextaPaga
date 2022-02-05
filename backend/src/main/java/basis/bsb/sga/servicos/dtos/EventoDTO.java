package basis.bsb.sga.servicos.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventoDTO implements Serializable {

    private Long id;

    @NotBlank(message = "campo invalido")
    private String nome;

    @FutureOrPresent(message = "data deve ser do futuro ou do presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    @Positive(message = "valor deve ser positivo")
    private Double valor;

    @NotBlank(message = "campo invalido")
    private SelectDTO motivo;

    @NotBlank(message = "campo invalido")
    private SelectDTO situacao;

    @NotBlank(message = "campo invalido")
    private List<SelectDTO> usuarios;
}
