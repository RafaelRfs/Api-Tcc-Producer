package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.AreasRequestDto;
import br.com.pucminas.apiproducer.enums.AreasEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.AREAS_ENDPOINT)
public class AreasController {

    @GetMapping
    public ResponseEntity<List<AreasRequestDto>> getAreas(){
        return ResponseEntity.ok(
                Stream.of(AreasEnum.values())
                .map(area -> AreasRequestDto.builder()
                        .codigo(area.getCodigo())
                        .nome(area.getNome())
                        .build())
                        .sorted((val,val2) -> val.getNome().compareTo(val2.getNome()))
                .collect(Collectors.toList())
        );
    }

}
