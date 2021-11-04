package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;

import java.util.List;

public interface TimelineService {

    TimelineRequestDto createTimeline(TimelineRequestDto timelineRequestDto);
    TimelineRequestDto findById(Long id);
    List<TimelineRequestDto> findTimelinesByProjectId(Long projectId);
}
