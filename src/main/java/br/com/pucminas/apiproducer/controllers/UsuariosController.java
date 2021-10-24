package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UsersRequestDto;
import br.com.pucminas.apiproducer.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(EndpointsConstants.USER_ENDPOINT)
@RequiredArgsConstructor
public class UsuariosController extends AbsController{

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody @Valid UsersRequestDto user){
        return new ResponseEntity<>(usuarioService.insereUsuario(user),
                HttpStatus.CREATED
                );
    }

}
