package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.UserResponseDto;
import br.com.pucminas.apiproducer.dtos.UserUpdateRequestDto;
import br.com.pucminas.apiproducer.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.USERS_ENDPOINT)
public class UsersController {

    private final AuthService authService;

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody @Valid UserUpdateRequestDto userUpdateRequest){
        return ResponseEntity.ok(
                authService.updateUserData(userUpdateRequest)
        );
    }

}
