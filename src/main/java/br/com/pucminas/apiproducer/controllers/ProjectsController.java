package br.com.pucminas.apiproducer.controllers;

import br.com.pucminas.apiproducer.constants.EndpointsConstants;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.enums.AreasEnum;
import br.com.pucminas.apiproducer.enums.RequestStatusEnum;
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

    @GetMapping("/by-segment/{segment}")
    public ResponseEntity<Object> findBySegment(@PathVariable AreasEnum segment) {
        return ResponseEntity.ok(
                projectService.findByArea(segment)
        );
    }

    @GetMapping("/by-segment/{segment}/by-status/{status}")
    public ResponseEntity<Object> findBySegment(@PathVariable AreasEnum segment, @PathVariable RequestStatusEnum status) {
        return ResponseEntity.ok(
                projectService.findByAreaAndStatus(segment, status)
        );
    }

    @GetMapping("/statistics")
    public ResponseEntity<Object> findCount() {
        return ResponseEntity.ok(
                projectService.findCountByArea()
        );
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody @Valid ProjectUpdateRequestDto projectRequestDto) {
        projectService.updateProject(projectRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/public")
    public ResponseEntity<?> getAllProjects() {

        return ResponseEntity.ok(
                projectService.findAllProjects()
        );

    }

    @GetMapping("/public/by-status/{status}")
    public ResponseEntity<?> getAllProjectsByStatus(@PathVariable RequestStatusEnum status) {
        return ResponseEntity.ok(
                projectService.findAllProjectsByStatus(status)
        );
    }




}
