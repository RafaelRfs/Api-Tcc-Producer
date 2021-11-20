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
    INICIADO(1, "Iniciado", "Iniciado", "play-circle", "project-status-gray"),
    AGUARDANDO_PAGAMENTO(2, "Aguardando pagamento", "Aguardando pagamento", "clock", "project-status-yellow"),
    PAGAMENTO_EFETUADO(3, "Pagamento Efetuado", "Pagamento Efetuado", "check-circle", "project-status-green"),
    EM_ANDAMENTO(4, "Em Andamento", "Em Andamento", "tasks", "project-status-gray"),
    AGUARDANDO_INFORMACOES(5, "Aguardando informações", "Aguardando informações", "clock", "project-status-yellow"),
    ANALISANDO_INFORMACOES(6, "Analisando informações", "Analisando informações", "search", "project-status-gray"),
    AGUARDANDO_APROVACAO(7, "Aguardando aprovação", "Aguardando aprovação ", "clock", "project-status-yellow"),
    APROVADO(8, "Aprovado", " Aprovado ", "check-circle", "project-status-green"),
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
