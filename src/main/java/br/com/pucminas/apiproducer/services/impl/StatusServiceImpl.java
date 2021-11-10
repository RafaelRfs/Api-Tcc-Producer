package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.dtos.StatusUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Status;
import br.com.pucminas.apiproducer.exceptions.StatusErrorException;
import br.com.pucminas.apiproducer.mappers.StatusMapper;
import br.com.pucminas.apiproducer.repositories.StatusRepository;
import br.com.pucminas.apiproducer.services.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static br.com.pucminas.apiproducer.constants.ApiConstants.MS_ERROR_STATUS_NOT_FOUND;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusServiceImpl implements StatusService {

    private final StatusMapper statusMapper;
    private final StatusRepository statusRepository;

    @Override
    public StatusRequestDto createStatus(StatusRequestDto statusRequestDto) {
        Status status = statusMapper.map(statusRequestDto);
        return statusMapper.mapToDto(
                statusRepository.save(status)
        );
    }

    @Override
    public void updateStatus(StatusUpdateRequestDto statusUpdateRequestDto) {
        Status status =  findById(statusUpdateRequestDto.getId());
        status.setCor(statusUpdateRequestDto.getCor());
        status.setIcone(statusUpdateRequestDto.getIcone());
        status.setNome(statusUpdateRequestDto.getNome());
        statusRepository.save(status);
    }

    @Override
    public void deleteById(Long statusId) {
        statusRepository.delete(
                findById(statusId)
        );
    }

    @Override
    public Status findById(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(
                ()-> new StatusErrorException(MS_ERROR_STATUS_NOT_FOUND)
        );
    }

    @Override
    public StatusRequestDto findStatusById(Long id) {
        return statusMapper.mapToDto(
                this.findById(id)
        );
    }

    @Override
    public List<StatusRequestDto> findAllStatuses() {
        return statusMapper.mapToListDto(
                statusRepository.findAll()
        );
    }
}
