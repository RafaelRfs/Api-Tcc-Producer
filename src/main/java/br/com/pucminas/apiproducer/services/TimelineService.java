package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import java.util.List;

public interface TimelineService {
    TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto, Projeto project);
    TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto);
    void createInitialTimeline(String description, Projeto project);
    TimelineRequestDto findById(Long id);
    List<TimelineRequestDto> findTimelinesByProjectId(Long projectId);
}
