package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
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

    @GetMapping("/{timelineId}")
    public ResponseEntity<TimelineRequestDto> findTimelineById(@PathVariable Long timelineId){
        return ResponseEntity.ok(
                timelineService.findById(timelineId)
        );
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<TimelineRequestDto>> findTimelinesByProject(@PathVariable Long projectId){
        return ResponseEntity.ok(
                timelineService.findTimelinesByProjectId(projectId)
        );
    }

}
