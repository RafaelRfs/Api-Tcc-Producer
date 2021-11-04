package br.com.pucminas.apiproducer.mappers;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.entities.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "statusRequest.nome")
    @Mapping(target = "cor", source = "statusRequest.cor")
    @Mapping(target = "icone", source = "statusRequest.icone")
    Status map(StatusRequestDto statusRequest);

    @Mapping(target = "id", source = "status.id")
    @Mapping(target = "nome", source = "status.nome")
    @Mapping(target = "cor", source = "status.cor")
    @Mapping(target = "icone", source = "status.icone")
    StatusRequestDto mapToDto(Status status);

    List<StatusRequestDto> mapToListDto(List<Status> statuses);

}
