package br.com.pucminas.apiproducer.mappers;

import br.com.pucminas.apiproducer.dtos.TimelineRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.Timeline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TimelineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", source = "project")
    @Mapping(target = "descricao", source = "timelineRequestDto.descricao")
    @Mapping(target = "url", source = "timelineRequestDto.url")
    @Mapping(target = "dataPostagem", source = "timelineRequestDto.dataPostagem")
    @Mapping(target = "status", source = "timelineRequestDto.status")
    @Mapping(target = "evento", source = "timelineRequestDto.evento")
    Timeline map(TimelineRequestDto timelineRequestDto, Projeto project);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "projetoId", expression = "java(timeline.getProject().getId())")
    @Mapping(target = "descricao", source = "timeline.descricao")
    @Mapping(target = "url", source = "timeline.url")
    @Mapping(target = "dataPostagem", source = "timeline.dataPostagem")
    @Mapping(target = "status", source = "timeline.status")
    @Mapping(target = "evento", source = "timeline.evento")
    TimelineRequestDto mapToDto(Timeline timeline);

    List<TimelineRequestDto> mapListToDto(List<Timeline> timelines);



}
