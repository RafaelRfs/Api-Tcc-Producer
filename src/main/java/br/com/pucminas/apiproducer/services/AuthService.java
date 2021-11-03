package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.LoginRequestDto;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.User;

public interface AuthService {
    UserResponseDto signup(UsersRequestDto usersRequestDto);
    UserResponseDto login(LoginRequestDto loginRequestDto);
    User getCurrentUser();
    UserResponseDto refreshToken();
}
