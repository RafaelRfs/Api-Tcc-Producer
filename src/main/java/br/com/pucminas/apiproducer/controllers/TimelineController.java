package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.dtos.TimelineUpdateRequestDto;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.services.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.TIMELINES_ENDPOINT)
public class TimelineController extends AbsController {

    private final TimelineService timelineService;

    @PostMapping
    public ResponseEntity<TimelineRequestDto> createTimeline(@RequestBody @Valid TimelineRequestDto request) {
        TimelineRequestDto response = timelineService.createTimeline(request);
        return ResponseEntity.created(
                URI.create("/" + response.getId())
        ).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTimeline(@RequestBody @Valid TimelineUpdateRequestDto request) {
        timelineService.updateTimeline(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{timelineId}")
    public ResponseEntity<TimelineRequestDto> findTimelineById(@PathVariable Long timelineId) {
        return ResponseEntity.ok(
                timelineService.findById(timelineId)
        );
    }

    @DeleteMapping("/{timelineId}")
    public ResponseEntity<?> deleteTimelineById(@PathVariable Long timelineId) {
        timelineService.deleteTimeline(timelineId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<TimelineRequestDto>> findTimelinesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(
                timelineService.findTimelinesByProjectId(projectId)
        );
    }

    @GetMapping("/by-project-status/{projectId}/{status}")
    public ResponseEntity<List<TimelineRequestDto>> findTimelinesByStatus(
            @PathVariable Long projectId,
            @PathVariable StatusEnum status
    ) {
        return ResponseEntity.ok(
                timelineService.findTimelinesByStatus(projectId, status)
        );
    }

}
