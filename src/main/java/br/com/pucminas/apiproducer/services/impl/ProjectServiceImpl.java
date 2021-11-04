package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.ProjectRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.exceptions.ProjectNotFoundException;
import br.com.pucminas.apiproducer.mappers.ProjectMapper;
import br.com.pucminas.apiproducer.repositories.ProjetoRepository;
import br.com.pucminas.apiproducer.services.AuthService;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final AuthService authService;
    private final ProjectMapper projectMapper;
    private final ProjetoRepository projectRepository;

    public static final String MSG_ERROR_PROJECT_NOT_FOUND = "projeto nao encontrado";

    @Override
    public ProjectRequestDto createProject(ProjectRequestDto projectRequestDto) {
        Projeto project = projectMapper.map(projectRequestDto, authService.getCurrentUser());
        return projectMapper.mapToDto(projectRepository.save(project));
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Projeto findEntityById(Long projectId) {
        return this.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                                MSG_ERROR_PROJECT_NOT_FOUND,
                                projectId
                        )
                );
    }

    @Override
    public List<ProjectRequestDto> findProjectsByUserId(Long userId) {
        return projectMapper.mapToListDto(
                this.projectRepository.findByUserId(userId)
        );
    }

    @Override
    public ProjectRequestDto findProjectById(Long projectId) {
        return projectMapper.mapToDto(
                findEntityById(projectId)
        );
    }
}
