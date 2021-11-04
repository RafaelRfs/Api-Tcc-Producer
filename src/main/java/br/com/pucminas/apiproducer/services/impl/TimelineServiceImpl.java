package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.Status;
import br.com.pucminas.apiproducer.entities.Timeline;
import br.com.pucminas.apiproducer.exceptions.TimelineErrorException;
import br.com.pucminas.apiproducer.mappers.TimelineMapper;
import br.com.pucminas.apiproducer.repositories.TimelineRepository;
import br.com.pucminas.apiproducer.services.ProjectService;
import br.com.pucminas.apiproducer.services.StatusService;
import br.com.pucminas.apiproducer.services.TimelineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService {
    private final TimelineMapper timelineMapper;
    private final TimelineRepository timelineRepository;
    private final ProjectService projectService;
    private final StatusService statusService;
    public static final String MSG_ERROR_TIMELINE_NOT_FOUND = "Timeline nao encontrada";

    @Override
    public TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto) {
        Projeto project = projectService.findEntityById(timelineRequestDto.getProjetoId());
        Status status = statusService.findById(timelineRequestDto.getStatusId());
        Timeline timeline = timelineMapper.map(timelineRequestDto, project, status);
        return timelineMapper.mapToDto(
                timelineRepository.save(timeline)
        );
    }

    @Override
    public TimelineRequestDto findById(Long id) {
        return timelineMapper.mapToDto(
                timelineRepository.findById(id)
                        .orElseThrow(() -> new TimelineErrorException(MSG_ERROR_TIMELINE_NOT_FOUND))
        );
    }

    @Override
    public List<TimelineRequestDto> findTimelinesByProjectId(Long projectId) {
        return timelineMapper.mapListToDto(
                timelineRepository.findByProjectId(projectId)
        );
    }
}
