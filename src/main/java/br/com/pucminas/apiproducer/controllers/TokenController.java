package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.TOKEN_ENDPOINT)
public class TokenController extends AbsController {
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<Object> tokenValidate(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(){
        return new ResponseEntity<>(authService.refreshToken(), HttpStatus.CREATED);
    }

}
