package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.exceptions.UserException;
import br.com.pucminas.apiproducer.repositories.UsuarioRepository;
import br.com.pucminas.apiproducer.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;

    public static final String MSG_ERROR = "Usuario já cadastrado no banco de dados";
    public static final String MSG_ERROR_USER_NOT_FOUND = "Usuario não encontrado";

    @Override
    public UserResponseDto insertUser(UsersRequestDto user) {
        user.setUuid(UUID.randomUUID().toString());
        validaUsuarioExistente(user);
        return saveUser(modelMapper.map(user, User.class));
    }

    @Override
    public UserResponseDto updateUserData(User usr) {
        return saveUser(usr);
    }

    private UserResponseDto saveUser(User usr) {
        User user = usuarioRepository.save(usr);
        return UserResponseDto.builder()
                .nome(user.getNome())
                .usuarioId(user.getId())
                .build();
    }

    @Override
    public User findUserByEmail(String email) {
        return findByEmail(email).orElseThrow(
                () -> new UserException(MSG_ERROR_USER_NOT_FOUND, UUID.randomUUID().toString())
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private void validaUsuarioExistente(UsersRequestDto user) {
        if (usuarioRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException(MSG_ERROR, user.getUuid());
        }
    }

}
