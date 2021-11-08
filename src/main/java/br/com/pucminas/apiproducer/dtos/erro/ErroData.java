package br.com.pucminas.apiproducer.dtos.erro;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErroData {
    private Integer codigo;
    private String mensagem;
    private List<Campos> campos;
}
