package br.com.pucminas.apiproducer.mappers;

import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "projectRequestDto.nome")
    @Mapping(target = "cliente", source = "projectRequestDto.cliente")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "dataPrevisaoEntrega", source = "projectRequestDto.dataPrevisaoEntrega")
    Projeto map(ProjectRequestDto projectRequestDto, User user);

    @Mapping(target = "id", expression = "java(project.getId())")
    @Mapping(target = "nome",source = "project.nome")
    @Mapping(target = "cliente",source = "project.cliente")
    @Mapping(target = "usuarioId", expression = "java(project.getUser().getId())")
    @Mapping(target = "dataPrevisaoEntrega",source = "project.dataPrevisaoEntrega")
    ProjectRequestDto mapToDto(Projeto project);

    List<ProjectRequestDto> mapToListDto(List<Projeto> projects);


}
