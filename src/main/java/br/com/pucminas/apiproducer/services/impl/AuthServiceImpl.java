package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.configs.security.jwt.JwtProvider;
import br.com.pucminas.apiproducer.dtos.LoginRequestDto;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.services.AuthService;
import br.com.pucminas.apiproducer.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public UserResponseDto signup(UsersRequestDto usersRequestDto){
        usersRequestDto.setSenha(encodePassword(usersRequestDto.getSenha()));
        return usuarioService.insertUser(usersRequestDto);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password.toLowerCase(Locale.ROOT).trim());
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword() ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return UserResponseDto.builder()
                .token(authenticationToken)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return usuarioService.findUserByEmail(principal.getUsername());
    }

    @Override
    public UserResponseDto refreshToken(){
        return UserResponseDto.builder()
                .token(jwtProvider
                        .generateTokenWithUserName(
                                getCurrentUser().getEmail()
                        )
                )
                .build();
    }

}
