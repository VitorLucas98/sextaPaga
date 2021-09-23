package basis.bsb.sga.servicos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventoDTO implements Serializable {

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;
    private Double valor;
    private SelectDTO motivo;
    private SelectDTO situacao;
    private List<SelectDTO> usuarios;
}
