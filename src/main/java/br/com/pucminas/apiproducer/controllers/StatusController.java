package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.dtos.StatusUpdateRequestDto;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.STATUS_ENDPOINT)
public class StatusController extends AbsController {

    private final StatusService statusService;

    @PostMapping
    public ResponseEntity<StatusRequestDto> createStatus(@RequestBody @Valid StatusRequestDto statusRequest) {
        StatusRequestDto response = statusService.createStatus(statusRequest);
        return ResponseEntity.created(URI.create("/".concat(response.getId().toString())))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateStatus(@RequestBody @Valid StatusUpdateRequestDto statusRequest) {
        statusService.updateStatus(statusRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<StatusRequestDto> findStatusById(@PathVariable Long statusId) {
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

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<StatusRequestDto>> findStatusByStatus(@PathVariable StatusEnum status) {
        return ResponseEntity.ok(
                statusService.findStatus(status)
        );
    }

    @GetMapping("/by-status/{status}/{event}")
    public ResponseEntity<List<StatusRequestDto>> findStatusByStatusEvent(@PathVariable StatusEnum status, @PathVariable EventsEnum event) {
        return ResponseEntity.ok(
                statusService.findStatusEvent(
                        status,
                        event
                )
        );
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<?> deleteStatusById(@PathVariable Long statusId) {
        statusService.deleteById(statusId);
        return ResponseEntity.noContent().build();
    }

}
