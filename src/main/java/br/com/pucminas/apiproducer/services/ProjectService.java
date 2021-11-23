package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.CountDataRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.enums.AreasEnum;
import br.com.pucminas.apiproducer.enums.RequestStatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Projeto> findById(Long id);
    Projeto findEntityById(Long id);
    List<ProjectRequestDto> findProjectsByUser();
    List<ProjectRequestDto> findProjectsByStatus(RequestStatusEnum requestStatusEnum);
    List<ProjectRequestDto> findAllProjects();
    List<String> findEmailsByProject(Long id);
    ProjectRequestDto findProjectById(Long id);
    ProjectRequestDto createProject(ProjectRequestDto projectRequestDto);
    void processProjectsByDeadlineDateBetween(LocalDate dateNow, LocalDate dateFuture);
    List<ProjectRequestDto> findByArea(AreasEnum areas);
    List<ProjectRequestDto> findByAreaAndStatus(AreasEnum areas, RequestStatusEnum requestStatusEnum);
    List<CountDataRequestDto> findCountByArea();
    void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto);
    void updateProject(Projeto projeto);
    void deleteById(Long id);
}
