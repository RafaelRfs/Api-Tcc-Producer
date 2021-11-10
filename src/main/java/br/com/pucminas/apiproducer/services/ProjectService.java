package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Projeto> findById(Long id);
    Projeto findEntityById(Long id);
    List<ProjectRequestDto> findProjectsByUserId(Long id);
    List<String> findEmailsByProject(Long id);
    ProjectRequestDto findProjectById(Long id);
    ProjectRequestDto createProject(ProjectRequestDto projectRequestDto);
    void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto);
    void deleteById(Long id);
}
