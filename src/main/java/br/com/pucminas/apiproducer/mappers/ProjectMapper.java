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
    @Mapping(target = "status", source = "projectRequestDto.status")
    @Mapping(target = "evento", source = "projectRequestDto.evento")
    Projeto map(ProjectRequestDto projectRequestDto, User user);

    @Mapping(target = "id", expression = "java(project.getId())")
    @Mapping(target = "nome",source = "project.nome")
    @Mapping(target = "cliente",source = "project.cliente")
    @Mapping(target = "usuarioId", expression = "java(project.getUser().getId())")
    @Mapping(target = "dataPrevisaoEntrega",source = "project.dataPrevisaoEntrega")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "evento", source = "evento")
    @Mapping(target = "statusData.id", expression ="java(projeto.getStatus()!= null ? projeto.getStatus().getId() : null)")
    @Mapping(target = "statusData.nome", expression ="java(projeto.getStatus()!= null ? projeto.getStatus().getNome() : null )")
    @Mapping(target = "statusData.icone", expression ="java(projeto.getStatus()!= null ? projeto.getStatus().getIcone() : null)")
    @Mapping(target = "statusData.cor", expression ="java(projeto.getStatus()!= null ? projeto.getStatus().getCor() : null)")
    ProjectRequestDto mapToDto(Projeto project);

    List<ProjectRequestDto> mapToListDto(List<Projeto> projects);


}
