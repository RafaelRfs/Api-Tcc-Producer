package br.com.pucminas.apiproducer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum EventsEnum {

    ANALISANDO_INFORMACOES(1, "Analisando informações"),
    PAGAMENTO_EFETUADO(2, "Pagamento efetuado"),
    PROJETO_INICIADO(3, "Projeto iniciado"),

    AGUARDANDO_PAGAMENTO(4, "Aguardando pagamento"),
    AGUARDANDO_APROVACAO(5, "Aguardando aprovação"),
    AGUARDANDO_INFORMACOES(6, "Aguardando informações"),

    PROJETO_CONCLUIDO(7, "Projeto concluido"),

    INICIADO(8,"Timeline iniciada ");

    private Integer id;
    private String nome;

    Optional<EventsEnum> fromId(Integer id){
       return Arrays.stream(EventsEnum.values())
                .filter(event -> event.getId().equals(id))
               .findFirst();
    }

}
