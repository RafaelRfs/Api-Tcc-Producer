package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.RequestStatusEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointsConstants.PROJECTS_ENDPOINT)
public class ProjectsController extends AbsController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectRequestDto> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto) {
        return ResponseEntity.created(null)
                .body(projectService
                        .createProject(projectRequestDto));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectRequestDto> findById(@PathVariable Long projectId) {
        return ResponseEntity
                .ok(projectService.findProjectById(projectId));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteById(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user")
    public ResponseEntity<Object> findProjectsByUser() {
        return ResponseEntity
                .ok(projectService.findProjectsByUser());
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<Object> findProjectsByStatus(@PathVariable RequestStatusEnum status) {
        return ResponseEntity
                .ok(projectService.findProjectsByStatus(status));
    }

    @GetMapping("/by-status/{status}/{event}")
    public ResponseEntity<Object> findProjectsByStatusEvent(@PathVariable StatusEnum status, @PathVariable EventsEnum event) {
        return ResponseEntity
                .ok(projectService.findProjectsByStatusEvent(
                        status, event
                ));
    }


    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody @Valid ProjectUpdateRequestDto projectRequestDto) {
        projectService.updateProject(projectRequestDto);
        return ResponseEntity.noContent().build();
    }

}
