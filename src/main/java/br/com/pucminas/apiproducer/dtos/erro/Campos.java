package br.com.pucminas.apiproducer.dtos.erro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campos {
    private String campo;
    private String mensagem;
}
