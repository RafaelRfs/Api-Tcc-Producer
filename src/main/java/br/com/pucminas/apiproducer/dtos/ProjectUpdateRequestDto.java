package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectUpdateRequestDto implements Serializable {

    @NotNull(message = "necessario informar o campo id do projeto")
    private Long id;

    @NotBlank(message = "necessario informar o nome projeto")
    private String nome;

    @NotBlank(message = "necessario informar o cliente projeto")
    private String cliente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "necessario informar o campo data previsao entrega do projeto")
    private LocalDate dataPrevisaoEntrega;

    @NotNull(message = "necessario informar o status")
    private StatusEnum status;

    @NotNull(message = "necessario informar o evento")
    private EventsEnum evento;
}
