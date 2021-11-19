package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

}
