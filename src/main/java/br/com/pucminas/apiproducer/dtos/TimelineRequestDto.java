package br.com.pucminas.apiproducer.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TimelineRequestDto {
    private Long id;
    private Long projetoId;
    private Long statusId;
    private String descricao;
    private String url;
    private LocalDateTime dataPostagem;
}
