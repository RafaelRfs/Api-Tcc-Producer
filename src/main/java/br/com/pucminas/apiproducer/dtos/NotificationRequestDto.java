package br.com.pucminas.apiproducer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotificationRequestDto implements Serializable {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "o campo projeto id deve ser informado")
    private Long projetoId;

    @NotBlank(message = "o campo nome não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "o campo email não pode ser nulo ou vazio")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uuid;
}
