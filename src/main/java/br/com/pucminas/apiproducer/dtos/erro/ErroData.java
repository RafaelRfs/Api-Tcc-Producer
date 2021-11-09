package br.com.pucminas.apiproducer.dtos.erro;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ErroData {
    private Integer codigo;
    private String mensagem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Campos> campos;
}
