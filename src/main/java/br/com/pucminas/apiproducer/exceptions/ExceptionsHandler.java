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
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErroData> getUserError(ApplicationException applicationException, HttpServletRequest request){
        log.error("Erro do usuario: {} /id: {}", applicationException.getMessage(), applicationException.getUuid());
        return getResponseData(applicationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErroData> getResponseData(String msg, HttpStatus status) {
        return ResponseEntity.status(status).body(
                ErroData.builder()
                        .codigo(status.value())
                        .mensagem(msg)
                        .build()
        );
    }
}
