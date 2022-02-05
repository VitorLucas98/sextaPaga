package basis.bsb.sga.servicos.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Nome invalido")
    private String nome;

    @Email(message = "email invalido")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "A data deve ser do passado !")
    private LocalDate dataNascimento;

    @CPF(message = "CPF invalido")
    private String cpf;

    @Size(max = 11, min = 9, message = "Telefone invalido")
    private String telefone;

    private boolean status;

    private SelectDTO cargo;
}
