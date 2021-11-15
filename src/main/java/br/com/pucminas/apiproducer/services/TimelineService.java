package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.dtos.TimelineUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;

import java.util.List;

public interface TimelineService {

    void createInitialTimeline(String description, Projeto project);
    TimelineRequestDto findById(Long id);
    List<TimelineRequestDto> findTimelinesByProjectId(Long projectId);

    List<TimelineRequestDto> findTimelinesByStatus(Long projectId, StatusEnum status);
    List<TimelineRequestDto> findTimelinesByStatusEvent(Long projectId, StatusEnum status, EventsEnum evento);

    TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto, Projeto project);
    TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto);
    void updateTimeline(TimelineUpdateRequestDto timelineUpdateRequestDto);
    void deleteTimeline(Long id);
}
