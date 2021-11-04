package br.com.pucminas.apiproducer.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusRequestDto {
    private Long id;

    @NotBlank(message = "o campo nome nao pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "o campo cor nao pode ser nulo ou vazio")
    private String cor;
    private String icone;
}
