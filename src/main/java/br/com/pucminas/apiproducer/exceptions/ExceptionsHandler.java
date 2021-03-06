package br.com.pucminas.apiproducer.exceptions;

import br.com.pucminas.apiproducer.dtos.erro.Campos;
import br.com.pucminas.apiproducer.dtos.erro.ErroData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErroData> getUserError(ApplicationException applicationException, HttpServletRequest request){
        log.error("Erro do usuario: {} /id: {}", applicationException.getMessage(), applicationException.getUuid());
        return getResponseData(applicationException.getMessage(), null,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<ErroData> getUserNotAuthorized(UserNotAuthorizedException applicationException, HttpServletRequest request){
        log.error("Erro de autorizacao do usuario: {} ", applicationException.getMessage());
        return getResponseData(applicationException.getMessage(),null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErroData> getProjectNotFoundError(ProjectNotFoundException projectNotFoundException){
        log.error("Erro de projeto: {} /id: {}", projectNotFoundException.getMessage(), projectNotFoundException.getProjectID());
        return getResponseData(projectNotFoundException.getMessage(),null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroData> getBadRequestErrors(MethodArgumentNotValidException methodArgumentNotValidException){

        log.error("Erro ao validar os campos: {} ", methodArgumentNotValidException.getMessage());
        List<Campos> campos = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new Campos(getSnakeCase(fieldError.getField()), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return getResponseData("Erro na validacao de campos ",campos, HttpStatus.BAD_REQUEST);

    }

    private String getSnakeCase(String value){
        return value.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase(Locale.ROOT);
    }

    private ResponseEntity<ErroData> getResponseData(String msg, List<Campos> campos, HttpStatus status) {
        return ResponseEntity.status(status).body(
                ErroData.builder()
                        .codigo(status.value())
                        .mensagem(msg)
                        .campos(campos)
                        .build()
        );
    }
}
