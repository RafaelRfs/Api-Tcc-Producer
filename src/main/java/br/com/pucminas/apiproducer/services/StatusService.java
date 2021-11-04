package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.entities.Status;

import java.util.List;

public interface StatusService {

    StatusRequestDto createStatus(StatusRequestDto statusRequestDto);
    Status findById(Long statusId);
    StatusRequestDto findStatusById(Long id);
    List<StatusRequestDto> findAllStatuses();

}
