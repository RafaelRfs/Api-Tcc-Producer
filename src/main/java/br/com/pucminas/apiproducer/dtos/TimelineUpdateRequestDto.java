package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TimelineUpdateRequestDto {

    @NotNull(message = "o campo id da timeline nao pode ser nulo ")
    private Long id;

    @NotNull(message = "o campo projeto_id nao pode ser nulo ou vazio")
    private Long projetoId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "o campo status_id nao pode ser nulo ou vazio")
    private Long statusId;

    @NotBlank(message = "o campo descricao nao pode ser nulo ou vazio")
    private String descricao;
    private String url;

    @NotNull(message = " o campo status nao deve ser nulo ou vazio ")
    private StatusEnum status;

    private String legenda;

    private Boolean alerta;

}
