package br.com.pucminas.apiproducer.enums;

import br.com.pucminas.apiproducer.exceptions.EventsErrorException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_EVENT_INVALID;
import static br.com.pucminas.apiproducer.utils.Helpers.validateIntegerEquals;
import static br.com.pucminas.apiproducer.utils.Helpers.validateStringEquals;

@Getter
@AllArgsConstructor
public enum EventsEnum {

    ANALISANDO_INFORMACOES(1, "Analisando informacoes"),
    PAGAMENTO_EFETUADO(2, "Pagamento efetuado"),
    PROJETO_INICIADO(3, "Projeto iniciado"),

    AGUARDANDO_PAGAMENTO(4, "Aguardando pagamento"),
    AGUARDANDO_APROVACAO(5, "Aguardando aprovacao"),
    AGUARDANDO_INFORMACOES(6, "Aguardando informacoes"),

    PROJETO_CONCLUIDO(7, "Projeto concluido"),

    INICIADO(8, "Iniciado");

    private Integer id;
    private String nome;

    Optional<EventsEnum> fromId(Integer id) {
        return Arrays.stream(EventsEnum.values())
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    @JsonCreator
    public static EventsEnum decode(Object value) {

        if (value instanceof Integer) {
            return decodeInteger((Integer) value);
        } else if (value instanceof String) {
            return decodeString((String) value);
        } else {
            throw new EventsErrorException(MSG_ERROR_EVENT_INVALID);
        }
    }


    public static EventsEnum decodeString(String value) {
        if (StringUtils.isNumeric(value)) {
            return Stream.of(EventsEnum.values())
                    .filter(val -> validateIntegerEquals(val.getId(), value))
                    .findFirst()
                    .orElseThrow(() -> new EventsErrorException(MSG_ERROR_EVENT_INVALID));
        } else {
            return Stream.of(EventsEnum.values())
                    .filter(val -> validateStringEquals(val.getNome(), value))
                    .findFirst()
                    .orElseThrow(() -> new EventsErrorException(MSG_ERROR_EVENT_INVALID));
        }
    }


    public static EventsEnum decodeInteger(Integer value) {
        return Stream.of(EventsEnum.values())
                .filter(val -> validateIntegerEquals(val.getId(), value))
                .findFirst()
                .orElseThrow(() -> new EventsErrorException(MSG_ERROR_EVENT_INVALID));

    }


}
