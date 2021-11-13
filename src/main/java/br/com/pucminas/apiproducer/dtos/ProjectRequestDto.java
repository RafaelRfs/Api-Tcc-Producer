package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ProjectRequestDto implements Serializable {
    private Long id;
    private Long usuarioId;

    @NotBlank(message = "necessario informar o nome projeto")
    private String nome;

    @NotBlank(message = "necessario informar o cliente projeto")
    private String cliente;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusEnum nomeStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusRequestDto status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "necessario informar o campo data previsao entrega do projeto")
    private LocalDate dataPrevisaoEntrega;
}
