package br.com.pucminas.apiproducer.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectRequestDto implements Serializable {
    private Long id;
    private Long usuarioId;
    private String nome;
    private String cliente;
    private LocalDateTime dataPrevisaoEntrega;
}
