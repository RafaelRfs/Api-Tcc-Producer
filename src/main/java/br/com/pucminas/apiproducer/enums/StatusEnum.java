package br.com.pucminas.apiproducer.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum StatusEnum {
    EM_ANDAMENTO(1, "Em Andamento"),
    AGUARDANDO_CLIENT(2, "Aguardando cliente "),
    FINALIZADO(3, " Finalizado ");

    private Integer id;
    private String descricao;

    StatusEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Optional<StatusEnum> fromId(Integer id) {
        return Arrays.stream(StatusEnum.values()).filter(
                status -> status.getId().equals(id)
        ).findFirst();
    }

}
