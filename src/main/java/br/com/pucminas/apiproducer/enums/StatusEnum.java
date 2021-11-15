package br.com.pucminas.apiproducer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    EM_ANDAMENTO(1, "Em Andamento", "start.jpg","green"),
    AGUARDANDO_CLIENTE(2, "Aguardando cliente ","waitingClient.png","blue"),
    FINALIZADO(3, " Finalizado ","finalized.jpg","white"),
    INICIADO(4, "Iniciada","timeline.jpg","blue");

    private Integer id;
    private String descricao;
    private String icone;
    private String cor;

    public Optional<StatusEnum> fromId(Integer id) {
        return Arrays.stream(StatusEnum.values()).filter(
                status -> status.getId().equals(id)
        ).findFirst();
    }

}
