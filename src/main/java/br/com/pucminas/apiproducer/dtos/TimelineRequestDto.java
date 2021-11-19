package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TimelineRequestDto {
    private Long id;
    @NotNull(message = "o campo projeto_id nao pode ser nulo ou vazio")
    private Long projetoId;

    @NotBlank(message = "o campo descricao nao pode ser nulo ou vazio")
    private String descricao;
    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataPostagem;


    private StatusEnum status;
    private String legenda;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean alerta = false;

}
