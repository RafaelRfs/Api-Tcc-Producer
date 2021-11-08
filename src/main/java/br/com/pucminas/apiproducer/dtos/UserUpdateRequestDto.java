package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.annotations.EmailValidator;
import br.com.pucminas.apiproducer.annotations.NullAnythingTextValidator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserUpdateRequestDto{

    @NotNull(message = "Id de usuario invalido ")
    private Long userId;

    @NotEmpty(message = "valor invalido para o campo nome")
    @Size(min = 3, max=30, message = "o tamanho minimo para o campo nome é de 3 caracteres e o maximo é de 30 chars")
    private String nome;

    @EmailValidator(message = "valor invalido para o campo email")
    private String email;

    @NotBlank(message = " preencha uma nova senha valida ")
    @Size(min = 3, message = "o tamanho minimo para o campo nova senha é de 3 caracteres")
    private String senha;

    @NullAnythingTextValidator(message = "valor minimo permitido de 6 caracteres", size = 6)
    private String novaSenha;

    @NullAnythingTextValidator(message = "valor minimo permitido de 6 caracteres", size = 6)
    private String confirmacaoNovaSenha;

}
