package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusUpdateRequestDto {

    @NotNull(message = "o campo id do status nao pode ser nulo")
    private Long id;

    @NotBlank(message = "o campo nome nao pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "o campo cor nao pode ser nulo ou vazio")
    private String cor;
    private String icone;

    @NotNull(message = "Informe o status")
    private StatusEnum status;

    @NotNull(message = "Informe o evento")
    private EventsEnum evento;
}
