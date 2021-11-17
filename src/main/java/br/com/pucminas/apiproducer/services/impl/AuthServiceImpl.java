package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.configs.security.jwt.JwtProvider;
import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.LoginRequestDto;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UserUpdateRequestDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.exceptions.UserNotAuthorizedException;
import br.com.pucminas.apiproducer.services.AuthService;
import br.com.pucminas.apiproducer.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final JwtProvider jwtProvider;

    @Override
    public UserResponseDto signup(UsersRequestDto usersRequestDto) {
        usersRequestDto.setSenha(encodePassword(usersRequestDto.getSenha()));
        return usuarioService.insertUser(usersRequestDto);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        return UserResponseDto.builder()
                .token(getToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                ))
                .build();
    }

    private String getToken(String username, String password) {
        Authentication authenticate = getAuthentication(username, password);
        return jwtProvider.generateToken(authenticate);
    }

    private Authentication getAuthentication(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate;
    }

    @Override
    public UserResponseDto updateUserData(UserUpdateRequestDto userUpdateRequestDto) {
        User user = getCurrentUser();
        validateIDUser(userUpdateRequestDto, user);
        validateOldPass(userUpdateRequestDto, user);
        validateProcessNewPassword(userUpdateRequestDto, user);
        validateEmail(userUpdateRequestDto, user);
        validateName(userUpdateRequestDto, user);
        return updateUserAndProcessTokenResponse(user);
    }

    private UserResponseDto updateUserAndProcessTokenResponse(User user) {
        UserResponseDto response = usuarioService.updateUserData(user);
        response.setToken(
                jwtProvider.generateTokenWithUserName(user.getEmail())
        );
        return response;
    }

    private void validateProcessNewPassword(UserUpdateRequestDto userUpdateRequestDto, User user) {
        Optional<String> optNewSenha = Optional.ofNullable(userUpdateRequestDto.getNovaSenha());
        if (optNewSenha.isPresent() && !optNewSenha.get().isBlank()) {
            validateConfirmationNewPassword(userUpdateRequestDto.getNovaSenha(), userUpdateRequestDto.getConfirmacaoNovaSenha(), ApiConstants.MSG_CONFIRMATION_NEW_PASS_INVALID);
            processNewPassword(userUpdateRequestDto, user);
        }
    }

    private void validateName(UserUpdateRequestDto userUpdateRequestDto, User user) {
        if (!user.getNome().equals(userUpdateRequestDto.getNome())) {
            user.setNome(userUpdateRequestDto.getNome());
        }
    }

    private void validateEmail(UserUpdateRequestDto userUpdateRequestDto, User user) {
        if (!user.getEmail().equals(userUpdateRequestDto.getEmail())) {
            if (usuarioService.findByEmail(userUpdateRequestDto.getEmail()).isPresent()) {
                throw new UserNotAuthorizedException(ApiConstants.MSG_ERROR_EMAIL_EXISTS);
            } else {
                user.setEmail(userUpdateRequestDto.getEmail());
            }
        }
    }

    private void processNewPassword(UserUpdateRequestDto userUpdateRequestDto, User user) {
        user.setSenha(encodePassword(userUpdateRequestDto.getNovaSenha()));
        userUpdateRequestDto.setSenha(userUpdateRequestDto.getNovaSenha());
    }

    private void validateConfirmationNewPassword(String novaSenha, String confirmacaoNovaSenha, String msgConfirmationNewPassInvalid) {
        if (!novaSenha.equals(confirmacaoNovaSenha)) {
            throw new UserNotAuthorizedException(msgConfirmationNewPassInvalid);
        }
    }

    private void validateOldPass(UserUpdateRequestDto userUpdateRequestDto, User user) {
        getAuthentication(user.getEmail(), userUpdateRequestDto.getSenha());
    }

    private void validateIDUser(UserUpdateRequestDto userUpdateRequestDto, User user) {
        if (!user.getId().equals(userUpdateRequestDto.getUserId())) {
            throw new UserNotAuthorizedException(ApiConstants.MSG_USER_NOT_AUTHORIZED);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return usuarioService.findUserByEmail(principal.getUsername());
    }

    @Override
    public UserResponseDto getUserData() {
        return modelMapper.map(
                getCurrentUser(),
                UserResponseDto.class
        );
    }

    @Override
    public UserResponseDto refreshToken() {
        return UserResponseDto.builder()
                .token(jwtProvider
                        .generateTokenWithUserName(
                                getCurrentUser().getEmail()
                        )
                )
                .build();
    }

}
