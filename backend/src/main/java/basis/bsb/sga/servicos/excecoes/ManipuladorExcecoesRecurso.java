package basis.bsb.sga.servicos.excecoes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ManipuladorExcecoesRecurso {

    @ExceptionHandler(ObjetoNaoEncontrado.class)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjetoNaoEncontrado e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao error = new ErroPadrao(LocalDateTime.now(), status.value(),
                "Objeto não encontrado !", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> validacoesEntrada(MethodArgumentNotValidException  e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErroValidacao error = new ErroValidacao();
        error.setErro("Erro no preenchimento dos dados !");
        error.setMensagem(e.getMessage());
        error.setDataDoErro(LocalDateTime.now());
        error.setStatus(status.value());
        error.setCaminho(request.getRequestURI());

        for (FieldError f: e.getBindingResult().getFieldErrors()) {
            error.addErro(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ValidadorExcecoes.class)
    public ResponseEntity<ErroPadrao> validacoesBanco(ValidadorExcecoes e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao error = new ErroPadrao(LocalDateTime.now(), status.value(),
                "O dado já persiste no banco!", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
