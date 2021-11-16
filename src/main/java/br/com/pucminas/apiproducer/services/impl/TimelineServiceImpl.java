package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.dtos.TimelineUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.Timeline;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.exceptions.TimelineErrorException;
import br.com.pucminas.apiproducer.mappers.TimelineMapper;
import br.com.pucminas.apiproducer.producers.EmailProducerService;
import br.com.pucminas.apiproducer.repositories.TimelineRepository;
import br.com.pucminas.apiproducer.services.ProjectService;
import br.com.pucminas.apiproducer.services.StatusService;
import br.com.pucminas.apiproducer.services.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_TIMELINE_NOT_FOUND;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TimelineServiceImpl implements TimelineService {

    private final TimelineMapper timelineMapper;
    private final TimelineRepository timelineRepository;
    private final ProjectService projectService;
    private final StatusService statusService;
    private final EmailProducerService emailProducerService;


    public TimelineServiceImpl(TimelineMapper timelineMapper,
                               TimelineRepository timelineRepository,
                               @Lazy ProjectService projectService,
                               StatusService statusService,
                               EmailProducerService emailProducerService) {
        this.timelineMapper = timelineMapper;
        this.timelineRepository = timelineRepository;
        this.projectService = projectService;
        this.statusService = statusService;
        this.emailProducerService = emailProducerService;
    }

    @Override
    public TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto, Projeto project) {
        project.setStatus(timelineRequestDto.getStatus());
        project.setEvento(timelineRequestDto.getEvento());
        return saveTimeline(timelineRequestDto, project);
    }

    @Override
    public TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto) {
        Projeto project = projectService.findEntityById(timelineRequestDto.getProjetoId());
        TimelineRequestDto response = saveTimeline(timelineRequestDto, project);
        project.setStatus(timelineRequestDto.getStatus());
        project.setEvento(timelineRequestDto.getEvento());
        projectService.updateProject(project);

        emailProducerService.sendEmail(
                UUID.randomUUID().toString(),
                ApiConstants.MSG_NEW_TIMELINE,
                String.format(ApiConstants.MSG_NEW_TIMELINE_CREATED, timelineRequestDto.getDescricao(), project.getNome()),
                projectService.findEmailsByProject(project.getId())
        );
        return response;
    }

    @Override
    public void updateTimeline(TimelineUpdateRequestDto timelineUpdateRequestDto) {
        Projeto project = projectService.findEntityById(timelineUpdateRequestDto.getProjetoId());
        Timeline timeline = getTimeline(timelineUpdateRequestDto.getId());
        timeline.setProject(project);
        timeline.setDescricao(timelineUpdateRequestDto.getDescricao());
        timeline.setUrl(timelineUpdateRequestDto.getUrl());
        timeline.setStatus(timelineUpdateRequestDto.getStatus());
        timeline.setEvento(timelineUpdateRequestDto.getEvento());
        timelineRepository.save(timeline);
    }

    @Override
    public void deleteTimeline(Long id) {
        Timeline timeline = getTimeline(id);
        timeline.setProject(null);
        timelineRepository.save(timeline);
        timelineRepository.delete(
                timeline
        );
    }

    @Override
    public void createInitialTimeline(String description, Projeto project) {
        this.createTimeline(
                TimelineRequestDto.builder()
                        .dataPostagem(LocalDateTime.now())
                        .descricao(description)
                        .status(StatusEnum.AGUARDANDO_PAGAMENTO)
                        .evento(EventsEnum.ANALISANDO_INFORMACOES)
                        .build(), project
        );
    }

    private TimelineRequestDto saveTimeline(TimelineRequestDto timelineRequestDto, Projeto project) {
        Timeline timeline = timelineMapper.map(timelineRequestDto, project);
        return timelineMapper.mapToDto(
                timelineRepository.save(timeline)
        );
    }

    @Override
    public TimelineRequestDto findById(Long id) {
        return timelineMapper.mapToDto(
                getTimeline(id)
        );
    }

    private Timeline getTimeline(Long id) {
        return timelineRepository.findById(id)
                .orElseThrow(() -> new TimelineErrorException(MSG_ERROR_TIMELINE_NOT_FOUND));
    }

    @Override
    public List<TimelineRequestDto> findTimelinesByProjectId(Long projectId) {
        return timelineMapper.mapListToDto(
                timelineRepository.findByProjectId(projectId)
        );
    }

    @Override
    public List<TimelineRequestDto> findTimelinesByStatus(Long projectId, StatusEnum status) {
        return timelineMapper.mapListToDto(
                timelineRepository.findByProjectIdAndStatus(projectId, status)
        );
    }

    @Override
    public List<TimelineRequestDto> findTimelinesByStatusEvent(Long projectId,StatusEnum status, EventsEnum evento) {
        return timelineMapper.mapListToDto(
                timelineRepository.findByProjectIdAndStatusAndEvento(
                        projectId,
                        status,
                        evento
                )
        );
    }
}
