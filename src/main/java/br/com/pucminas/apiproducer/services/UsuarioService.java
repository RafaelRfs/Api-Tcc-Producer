package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.Usuario;

public interface UsuarioService {
    UserResponseDto insertUser(UsersRequestDto user);
    Usuario findUserByEmail(String email);
}
