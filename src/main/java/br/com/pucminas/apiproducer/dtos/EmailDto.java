package br.com.pucminas.apiproducer.dtos;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto implements Serializable {
    private String uuid;
    private String de;
    private String para;
    private String assunto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> emails;
    private String corpo;
}
