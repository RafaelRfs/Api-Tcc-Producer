package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.EventsDto;
import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.STATUS_ENDPOINT)
public class StatusController extends AbsController {

    private final StatusService statusService;

    @GetMapping("/{statusId}")
    public ResponseEntity<StatusRequestDto> findStatusById(@PathVariable Integer statusId) {
        return ResponseEntity.ok(
                statusService.findStatusById(statusId)
        );
    }

    @GetMapping
    public ResponseEntity<List<StatusRequestDto>> findAllStatuses() {
        return ResponseEntity.ok(
                statusService.findAllStatuses()
        );
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventsDto>> findAllEvents(){
        return ResponseEntity.ok(
                Arrays.stream(EventsEnum.values())
                .map( evnt -> EventsDto.builder()
                        .id(evnt.getId())
                        .nome(evnt.getNome())
                        .build()
                )
                .collect(Collectors.toList())
        );
    }

}
