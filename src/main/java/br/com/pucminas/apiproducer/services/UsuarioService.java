package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.User;
import java.util.Optional;

public interface UsuarioService {
    UserResponseDto insertUser(UsersRequestDto user);
    UserResponseDto updateUserData(User user);
    User findUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
