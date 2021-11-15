package br.com.pucminas.apiproducer.mappers;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cor", source = "cor")
    @Mapping(target = "icone", source = "icone")
    StatusRequestDto mapToDto(StatusEnum status);

    List<StatusRequestDto> mapToListDto(List<StatusEnum> statuses);

}
