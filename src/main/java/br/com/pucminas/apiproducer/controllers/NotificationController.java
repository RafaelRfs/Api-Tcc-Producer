package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.NotificationUpdateRequestDto;
import br.com.pucminas.apiproducer.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.NOTIFICATION_ENDPOINT)
public class NotificationController extends AbsController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody @Valid NotificationRequestDto notificationRequest) {
        notificationRequest.setUuid(UUID.randomUUID().toString());
        notificationService.insertNotification(
                notificationRequest
        );
        return ResponseEntity.created(null).build();
    }

    @PutMapping
    public ResponseEntity<?> updateNotification(@RequestBody @Valid NotificationUpdateRequestDto notificationRequest) {
        notificationService.updateNotification(notificationRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationRequestDto> findNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(
                notificationService.findNotificationById(id)
        );
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<NotificationRequestDto>> findNotificationByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(
                notificationService.findNotificationByProjectId(projectId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

}
