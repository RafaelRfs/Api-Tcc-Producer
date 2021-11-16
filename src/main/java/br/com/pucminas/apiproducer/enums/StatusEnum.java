package br.com.pucminas.apiproducer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    AGUARDANDO_PAGAMENTO(1,"Aguardando pagamento","Aguardando pagamento", "clock","project-status-yellow"),
    INICIADO(2, "Iniciado", "Iniciado", "play-circle", "project-status-gray"),
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

}
