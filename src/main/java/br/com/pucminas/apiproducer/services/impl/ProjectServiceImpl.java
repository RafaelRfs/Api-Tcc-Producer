package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.exceptions.ProjectNotFoundException;
import br.com.pucminas.apiproducer.mappers.ProjectMapper;
import br.com.pucminas.apiproducer.producers.EmailProducerService;
import br.com.pucminas.apiproducer.repositories.ProjetoRepository;
import br.com.pucminas.apiproducer.services.AuthService;
import br.com.pucminas.apiproducer.services.NotificationService;
import br.com.pucminas.apiproducer.services.ProjectService;
import br.com.pucminas.apiproducer.services.TimelineService;
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
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final AuthService authService;
    private final ProjectMapper projectMapper;
    private final ProjetoRepository projectRepository;
    private final TimelineService timelineService;
    private final EmailProducerService emailProducerService;
    private final NotificationService notificationService;

    @Override
    public ProjectRequestDto createProject(ProjectRequestDto projectRequestDto) {
        User user = authService.getCurrentUser();
        Projeto project = projectMapper.map(projectRequestDto, user);
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
    public void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto) {
        Projeto projeto = findEntityById(projectUpdateRequestDto.getId());
        projeto.setCliente(projectUpdateRequestDto.getCliente());
        projeto.setDataPrevisaoEntrega(projectUpdateRequestDto.getDataPrevisaoEntrega());
        projeto.setNome(projectUpdateRequestDto.getNome());
        projectRepository.save(projeto);
    }

    @Override
    public void deleteById(Long projectId) {
        deleteNotifications(projectId);
        deleteTimelines(projectId);
        deleteProject(projectId);
    }

    private void deleteProject(Long projectId) {
        projectRepository.delete(
                findEntityById(projectId)
        );
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
    public List<ProjectRequestDto> findProjectsByUserId(Long userId) {
        return projectMapper.mapToListDto(
                this.projectRepository.findByUserId(userId)
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
