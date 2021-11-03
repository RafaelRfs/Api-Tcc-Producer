package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Projeto> findById(Long id);
    List<ProjectRequestDto> findProjectsByUserId(Long id);
    ProjectRequestDto findProjectById(Long id);
    ProjectRequestDto createProject(ProjectRequestDto projectRequestDto);
}
