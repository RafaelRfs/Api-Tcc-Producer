package br.com.pucminas.apiproducer.mappers;

import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.entities.Notificacao;
import br.com.pucminas.apiproducer.entities.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "notificationRequest.nome")
    @Mapping(target = "email", source = "notificationRequest.email")
    @Mapping(target = "project", source = "project")
    Notificacao map(NotificationRequestDto notificationRequest, Projeto project);

    @Mapping(target = "id", expression = "java(notification.getId())")
    @Mapping(target = "projetoId", expression = "java(notification.getProject().getId())")
    @Mapping(target = "nome",source = "notification.nome")
    @Mapping(target = "email",source = "notification.email")
    NotificationRequestDto mapToDto(Notificacao notification);

    List<NotificationRequestDto> maptoListDto(List<Notificacao> notifications);

}
