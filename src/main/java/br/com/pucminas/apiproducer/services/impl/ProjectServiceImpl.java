package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.CountDataRequestDto;
import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.dtos.ProjectUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.User;
import br.com.pucminas.apiproducer.enums.AreasEnum;
import br.com.pucminas.apiproducer.enums.RequestStatusEnum;
import br.com.pucminas.apiproducer.exceptions.ProjectNotFoundException;
import br.com.pucminas.apiproducer.mappers.ProjectMapper;
import br.com.pucminas.apiproducer.producers.EmailProducerService;
import br.com.pucminas.apiproducer.repositories.ProjetoRepository;
import br.com.pucminas.apiproducer.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_PROJECT_NOT_FOUND;
import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_PROJECT_STARTED;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final AuthService authService;
    private final ProjectMapper projectMapper;
    private final ProjetoRepository projectRepository;
    private final TimelineService timelineService;
    private final EmailProducerService emailProducerService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public ProjectRequestDto createProject(ProjectRequestDto projectRequestDto) {
        projectRequestDto.setDataInicioProjeto(LocalDate.now());
        User user = authService.getCurrentUser();
        Projeto project = projectMapper.map(projectRequestDto, user);
        project = projectRepository.save(project);
        addNotification(user, project);
        createTimeline(user, project);
        sendEmail(user, project);
        project = projectRepository.getById(project.getId());
        return projectMapper.mapToDto(project);
    }

    @Override
    public void processProjectsByDeadlineDateBetween(LocalDate dateNow, LocalDate dateFuture) {

        List<Projeto> projectsWithDeadline = projectRepository.findByDataPrevisaoEntregaBetween(
                dateNow,
                dateFuture
        );

        projectsWithDeadline.stream()
                .peek(val -> log.info("Projeto perto do prazo de conclusão : " + val.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectRequestDto> findByArea(AreasEnum area) {
        return projectMapper.mapToListDto(
                projectRepository.findByArea(area)
        );
    }

    @Override
    public List<ProjectRequestDto> findByAreaAndStatus(AreasEnum area, RequestStatusEnum requestStatusEnum) {
        return projectMapper.mapToListDto(
                projectRepository.findByAreaAndStatusIn(area, requestStatusEnum.getStatus())
        );
    }

    @Override
    public List<CountDataRequestDto> findCountByArea() {
        return projectRepository.findByCount(
                Arrays.asList(
                        AreasEnum.values()
                )
        );
    }

    private void addNotification(User user, Projeto project) {
        notificationService.insertNotification(
                NotificationRequestDto.builder()
                        .uuid(UUID.randomUUID().toString())
                        .email(user.getEmail())
                        .nome(user.getNome())
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
                        project.getNome()
                ),
                Arrays.asList(user.getEmail())
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateProject(ProjectUpdateRequestDto projectUpdateRequestDto) {
        Projeto projeto = findEntityById(projectUpdateRequestDto.getId());
        if (projectUpdateRequestDto.getStatus() != null) {
            projeto.setStatus(projectUpdateRequestDto.getStatus());
        }
        projeto.setDataPrevisaoEntrega(projectUpdateRequestDto.getDataPrevisaoEntrega());
        projeto.setNome(projectUpdateRequestDto.getNome());
        projeto.setImg(projectUpdateRequestDto.getImg());
        projeto.setArea(projectUpdateRequestDto.getSegmento());

        projectRepository.save(projeto);
    }

    @Override
    public void updateProject(Projeto projeto) {
        projectRepository.save(projeto);
    }

    @Override
    public void deleteById(Long projectId) {
        deleteNotifications(projectId);
        deleteTimelines(projectId);
        deleteProject(projectId);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteUserAndStatusRelationship(Projeto project) {
        project.setStatus(null);
        project.setUser(null);
        projectRepository.saveAndFlush(project);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Long projectId) {
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
    public List<ProjectRequestDto> findProjectsByStatus(RequestStatusEnum status) {
        return projectMapper.mapToListDto(
                projectRepository.findByUserIdAndStatusIn(
                        authService.getCurrentUser().getId(),
                        status.getStatus()
                )
        );
    }

    @Override
    public List<ProjectRequestDto> findAllProjectsByStatus(RequestStatusEnum requestStatusEnum) {
        return projectMapper.mapToListDto(
                projectRepository.findByStatusIn(requestStatusEnum.getStatus())
        );
    }

    @Override
    public List<ProjectRequestDto> findAllProjects() {
        return projectMapper.mapToListDto(
                projectRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "id")

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
