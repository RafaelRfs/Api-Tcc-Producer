package br.com.pucminas.apiproducer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum RequestStatusEnum {

    EM_ANDAMENTO(
            Arrays.asList(
                    StatusEnum.ANALISANDO_INFORMACOES,
                    StatusEnum.PAGAMENTO_EFETUADO,
                    StatusEnum.INICIADO,
                    StatusEnum.APROVADO,
                    StatusEnum.EM_ANDAMENTO
            )
    ),

    AGUARDANDO_CLIENTE(
            Arrays.asList(
                    StatusEnum.AGUARDANDO_PAGAMENTO,
                    StatusEnum.AGUARDANDO_APROVACAO,
                    StatusEnum.AGUARDANDO_INFORMACOES
            )
    ),
    FINALIZADO(
            Arrays.asList(
                    StatusEnum.CONCLUIDO
            )
    );

    private List<StatusEnum> status;
}
