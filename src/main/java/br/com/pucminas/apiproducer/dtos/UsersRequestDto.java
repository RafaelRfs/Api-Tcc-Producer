package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.annotations.EmailValidator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UsersRequestDto {

    @NotEmpty(message = "valor invalido para o campo nome")
    @Size(min = 3, max=30, message = "o tamanho minimo para o campo nome é de 3 caracteres e o maximo é de 30 chars")
    private String nome;

    @EmailValidator(message = "valor invalido para o campo email")
    private String email;

    @NotEmpty(message = "valor invalido para o campo senha")
    @Size(min = 6, message = "o tamanho minimo para o campo senha é de 6 caracteres")
    private String senha;
    private String uuid;
}
