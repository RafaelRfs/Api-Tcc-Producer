package br.com.pucminas.apiproducer.enums;

import br.com.pucminas.apiproducer.exceptions.StatusErrorException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_STATUS_INVALID;
import static br.com.pucminas.apiproducer.utils.Helpers.validateIntegerEquals;
import static br.com.pucminas.apiproducer.utils.Helpers.validateStringEquals;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    EM_PAUTA_PARA_DISCUSSAO(1, "Em Pauta para Discussao", "Em Pauta para Discussão", "play-circle", "project-status-gray"),
    EM_APROVACAO_DA_CAMARA(2, "Em aprovacao da camara", "Em aprovação da camara", "clock", "project-status-yellow"),
    APROVADO_CAMARA_MUNICIPAL(3, "Aprovado Camara Municipal", "Aprovado Camara Municipal", "check-circle", "project-status-green"),
    REPROVADO_CAMARA_MUNICIPAL(4, "Reprovado Camara Municipal", "Reprovado Camara Municipal", "tasks", "project-status-gray"),
    EM_ANDAMENTO(5, "Em Andamento", "Em Andamento", "clock", "project-status-yellow"),
    EM_VIGOR(6, "Analisando informações", "Analisando informações", "search", "project-status-gray"),
    EM_FUNCIONAMENTO(7, "Aguardando aprovação", "Aguardando aprovação ", "clock", "project-status-yellow"),
    AGUARDANDO_INFORMACOES(8, "Aguardando Informacoes","Aguardando Informações","clock", "project-status-yellow"),
    CONSTRUIDO(8, "Construido", " Construido ", "check-circle", "project-status-green"),
    CONCLUIDO(9, "Concluido", "Concluido", "thumbtack", "project-status-blue");

    private Integer id;
    private String nome;
    private String descricao;
    private String icone;
    private String cor;

    public static Optional<StatusEnum> fromId(Integer id) {
        return Arrays.stream(StatusEnum.values()).filter(
                status -> status.getId().equals(id)
        ).findFirst();
    }

    @JsonCreator
    public static StatusEnum decode(Object value) {
        if (value instanceof Integer) {
            return decodeInteger((Integer) value);
        } else if (value instanceof String) {
            return decodeString((String) value);
        } else {
            throw new StatusErrorException(MSG_ERROR_STATUS_INVALID);
        }
    }


    public static StatusEnum decodeString(final String value) {
        if (StringUtils.isNumeric(value)) {
            return Stream.of(StatusEnum.values())
                    .filter(val -> validateIntegerEquals(val.getId(), value))
                    .findFirst()
                    .orElseThrow(() -> new StatusErrorException(MSG_ERROR_STATUS_INVALID));
        } else {
            return Stream.of(StatusEnum.values())
                    .filter(val -> validateStringEquals(val.getNome(), value))
                    .findFirst()
                    .orElseThrow(() -> new StatusErrorException(MSG_ERROR_STATUS_INVALID));
        }
    }


    public static StatusEnum decodeInteger(final Integer value) {
        return Stream.of(StatusEnum.values())
                .filter(val -> validateIntegerEquals(val.getId(), value))
                .findFirst()
                .orElseThrow(() -> new StatusErrorException(MSG_ERROR_STATUS_INVALID));
    }


}
