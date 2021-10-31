package br.com.pucminas.apiproducer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "digite um usuario valido ")
    private String username;

    @NotBlank(message = "digite uma senha valida ")
    private String password;
}