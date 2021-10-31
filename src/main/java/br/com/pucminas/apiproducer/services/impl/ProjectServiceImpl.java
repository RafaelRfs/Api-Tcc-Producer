package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.repositories.ProjetoRepository;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjetoRepository projetoRepository;

    @Override
    public Optional<Projeto> findById(Long id){
        return projetoRepository.findById(id);
    }
}
