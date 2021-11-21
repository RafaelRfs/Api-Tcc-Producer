package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.enums.RequestStatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Projeto> findById(Long id);
    Projeto findEntityById(Long id);
    List<ProjectRequestDto> findProjectsByUser();
    List<ProjectRequestDto> findProjectsByStatus(RequestStatusEnum requestStatusEnum);
    List<String> findEmailsByProject(Long id);
    ProjectRequestDto findProjectById(Long id);
    ProjectRequestDto createProject(ProjectRequestDto projectRequestDto);
    List<ProjectRequestDto> findProjectByDateBetween(LocalDate dateNow, LocalDate dateFuture);
    void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto);
    void updateProject(Projeto projeto);
    void deleteById(Long id);
}
