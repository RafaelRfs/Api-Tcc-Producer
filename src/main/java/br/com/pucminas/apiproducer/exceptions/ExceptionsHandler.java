package br.com.pucminas.apiproducer.exceptions;

import br.com.pucminas.apiproducer.dtos.erro.ErroData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErroData> geraErroUsuario(UserException userException, HttpServletRequest request){
        log.error("Erro do usuario: {} /id: {}", userException.getMessage(), userException.getUuid());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(
                ErroData.builder()
                        .codigo(status.value())
                        .mensagem(userException.getMessage())
                        .build()
        );
    }
}
