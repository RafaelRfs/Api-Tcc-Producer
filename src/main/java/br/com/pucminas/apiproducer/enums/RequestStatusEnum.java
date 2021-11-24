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
                    StatusEnum.APROVADO_CAMARA_MUNICIPAL,
                    StatusEnum.EM_ANDAMENTO
            )
    ),
    EM_APROVACAO(
            Arrays.asList(
                    StatusEnum.EM_ESCOPO,
                    StatusEnum.AGUARDANDO_INFORMACOES,
                    StatusEnum.EM_APROVACAO_DA_CAMARA
            )
    )
    ,
    CONCLUIDO(
            Arrays.asList(
                    StatusEnum.REPROVADO_CAMARA_MUNICIPAL,
                    StatusEnum.EM_VIGOR,
                    StatusEnum.CONCLUIDO,
                    StatusEnum.CONSTRUIDO

            )
    );

    private List<StatusEnum> status;
}
