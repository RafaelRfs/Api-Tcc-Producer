package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.Status;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.exceptions.ProjectNotFoundException;
import br.com.pucminas.apiproducer.mappers.ProjectMapper;
import br.com.pucminas.apiproducer.producers.EmailProducerService;
import br.com.pucminas.apiproducer.repositories.ProjetoRepository;
import br.com.pucminas.apiproducer.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_PROJECT_NOT_FOUND;
import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_PROJECT_STARTED;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final AuthService authService;
    private final StatusService statusService;
    private final ProjectMapper projectMapper;
    private final ProjetoRepository projectRepository;
    private final TimelineService timelineService;
    private final EmailProducerService emailProducerService;
    private final NotificationService notificationService;

    @Override
    public ProjectRequestDto createProject(ProjectRequestDto projectRequestDto) {
        User user = authService.getCurrentUser();
        Status status = statusService.findFirst(
                StatusEnum.EM_ANDAMENTO,
                EventsEnum.ANALISANDO_INFORMACOES
        );
        Projeto project = projectMapper.map(projectRequestDto, user, status);
        project = projectRepository.save(project);
        addNotification(user, project);
        createTimeline(user, project);
        sendEmail(user, project);
        return projectMapper.mapToDto(project);
    }

    private void addNotification(User user, Projeto project) {
        notificationService.insertNotification(
                NotificationRequestDto.builder()
                        .uuid(UUID.randomUUID().toString())
                        .email(user.getEmail())
                        .projetoId(project.getId())
                        .build()
        );
    }

    private void createTimeline(User user, Projeto project) {
        timelineService.createInitialTimeline(
                String.format(MSG_PROJECT_STARTED, user.getNome()), project);
    }

    private void sendEmail(User user, Projeto project) {
        emailProducerService.sendEmail(
                user.getUuid(),
                String.format(ApiConstants.MSG_SUBJECT_NEW_PROJECT, project.getNome()),
                String.format(
                        ApiConstants.MSG_BODY_PROJECT,
                        project.getNome(),
                        project.getCliente()
                ),
                Arrays.asList(user.getEmail())
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto) {
        Projeto projeto = findEntityById(projectUpdateRequestDto.getId());
        Status status = statusService.findFirst(
                projectUpdateRequestDto.getStatus(),
                projectUpdateRequestDto.getEvento()
        );
        projeto.setCliente(projectUpdateRequestDto.getCliente());
        projeto.setDataPrevisaoEntrega(projectUpdateRequestDto.getDataPrevisaoEntrega());
        projeto.setNome(projectUpdateRequestDto.getNome());
        projeto.setStatus(status);
        projectRepository.save(projeto);
    }

    @Override
    public void deleteById(Long projectId) {
        deleteNotifications(projectId);
        deleteTimelines(projectId);
        deleteProject(projectId);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteUserAndStatusRelationship(Projeto project){
        project.setStatus(null);
        project.setUser(null);
        projectRepository.saveAndFlush(project);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Long projectId){
        projectRepository.delete(
                findEntityById(projectId)
        );
    }

    private void deleteProject(Long projectId) {
        deleteUserAndStatusRelationship(
                findEntityById(projectId)
        );
        delete(projectId);
    }

    private void deleteTimelines(Long projectId) {
        timelineService.findTimelinesByProjectId(projectId)
                .forEach(timelineRequestDto -> timelineService.deleteTimeline(
                        timelineRequestDto.getId()
                ));
    }

    private void deleteNotifications(Long projectId) {
        notificationService.findNotificationByProjectId(projectId)
                .forEach(notificationRequest -> notificationService.deleteNotification(
                        notificationRequest.getId()
                ));
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Projeto findEntityById(Long projectId) {
        return this.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                                MSG_ERROR_PROJECT_NOT_FOUND,
                                projectId
                        )
                );
    }

    @Override
    public List<ProjectRequestDto> findProjectsByUser() {
        return projectMapper.mapToListDto(
                this.projectRepository.findByUserId(
                        authService
                        .getCurrentUser()
                        .getId()
                )
        );
    }

    @Override
    public List<ProjectRequestDto> findProjectsByStatus(StatusEnum status) {
        return projectMapper.mapToListDto(
                projectRepository.findByUserIdAndStatusStatus(
                        authService.getCurrentUser().getId(),
                        status
                )
        );
    }

    @Override
    public List<ProjectRequestDto> findProjectsByStatusEvent(StatusEnum status, EventsEnum event) {
        return projectMapper.mapToListDto(
                projectRepository.findByUserIdAndStatusStatusAndStatusEvento(
                        authService.getCurrentUser().getId(),
                        status,
                        event
                )
        );
    }

    @Override
    public List<String> findEmailsByProject(Long id) {
        return notificationService.findEmailsByProject(id);
    }

    @Override
    public ProjectRequestDto findProjectById(Long projectId) {
        return projectMapper.mapToDto(
                findEntityById(projectId)
        );
    }
}
