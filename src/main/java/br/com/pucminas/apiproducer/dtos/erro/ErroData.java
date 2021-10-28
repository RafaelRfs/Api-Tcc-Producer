package br.com.pucminas.apiproducer.dtos.erro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroData {
    private Integer codigo;
    private String mensagem;
}
