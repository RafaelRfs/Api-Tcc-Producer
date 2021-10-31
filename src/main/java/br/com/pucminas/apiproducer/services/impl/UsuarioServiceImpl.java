package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.EmailDto;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.entities.Usuario;
import br.com.pucminas.apiproducer.exceptions.UserException;
import br.com.pucminas.apiproducer.producers.EmailProducer;
import br.com.pucminas.apiproducer.repositories.UsuarioRepository;
import br.com.pucminas.apiproducer.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final ModelMapper modelMapper;
    private final EmailProducer emailProducer;
    private final UsuarioRepository usuarioRepository;
    public static final String MSG_ERROR = "Usuario já cadastrado no banco de dados";
    public static final String MSG_ERROR_USER_NOT_FOUND = "Usuario não encontrado";

    @Override
    public UserResponseDto insertUser(UsersRequestDto user) {
        user.setUuid(UUID.randomUUID().toString());
        validaUsuarioExistente(user);
        Usuario usuario = usuarioRepository.save(modelMapper.map(user, Usuario.class));
        emailProducer.sendDataQueue(
                EmailDto.builder()
                        .uuid(user.getUuid())
                        .assunto("Adicionado novo usuario " + user.getNome())
                        .corpo("Email >> " + user.getEmail())
                        .emails(
                                Arrays.asList(
                                        user.getEmail()
                                )
                        )
                        .de("teste@teste.com")
                        .para("fulano@fulano.com")
                        .build()
        );

        return UserResponseDto.builder()
                .nome(usuario.getNome())
                .usuarioId(usuario.getId())
                .build();
    }

    @Override
    public Usuario findUserByEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new UserException(MSG_ERROR_USER_NOT_FOUND, UUID.randomUUID().toString())
        );
    }

    private void validaUsuarioExistente(UsersRequestDto user) {
        if (usuarioRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException(MSG_ERROR, user.getUuid());
        }
    }

}
