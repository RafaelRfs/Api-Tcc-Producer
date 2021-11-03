package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
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
                .body(projectService.createProject(projectRequestDto));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectRequestDto> findById(@PathVariable Long projectId) {
        return ResponseEntity
                .ok(projectService.findProjectById(projectId));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Object> findProjectsByUser(@PathVariable Long userId) {
        return ResponseEntity
                .ok(projectService.findProjectsByUserId(userId));
    }

}
