package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import java.util.List;

public interface StatusService {
    StatusRequestDto findStatusById(Integer id);
    List<StatusRequestDto> findAllStatuses();
}
