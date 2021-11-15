package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.dtos.StatusUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Status;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import java.util.List;

public interface StatusService {
    Status findById(Long statusId);
    Status findFirst(StatusEnum status, EventsEnum event);
    StatusRequestDto findStatusById(Long id);
    List<StatusRequestDto> findStatus(StatusEnum status);
    List<StatusRequestDto> findStatusEvent(StatusEnum status, EventsEnum event);
    List<StatusRequestDto> findAllStatuses();
    StatusRequestDto createStatus(StatusRequestDto statusRequestDto);
    void updateStatus(StatusUpdateRequestDto statusRequestDto);
    void deleteById(Long statusId);
}
