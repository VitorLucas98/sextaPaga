package basis.bsb.sga.servicos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventoDTO implements Serializable {

    private Long id;
    private String nome;
    private LocalDate dataEvento;
    private Double valor;
    private MotivoDTO motivo;
    private Long idSituacao;
}
