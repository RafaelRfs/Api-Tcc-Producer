package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.StatusRequestDto;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import br.com.pucminas.apiproducer.mappers.StatusMapper;
import br.com.pucminas.apiproducer.services.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusServiceImpl implements StatusService {

    private final StatusMapper statusMapper;

    @Override
    public StatusRequestDto findStatusById(Integer id) {
        return statusMapper.mapToDto(
                StatusEnum.fromId(id)
                .orElse(null)
        );
    }

    @Override
    public List<StatusRequestDto> findAllStatuses() {
        return statusMapper.mapToListDto(
                Arrays.stream(StatusEnum.values())
                .collect(Collectors.toList())
        );
    }
}
