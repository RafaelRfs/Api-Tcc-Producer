package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.NotificationUpdateRequestDto;
import br.com.pucminas.apiproducer.entities.Notificacao;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.exceptions.NotificationErrorException;
import br.com.pucminas.apiproducer.mappers.NotificationMapper;
import br.com.pucminas.apiproducer.repositories.NotificacaoRepository;
import br.com.pucminas.apiproducer.services.NotificationService;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import static br.com.pucminas.apiproducer.constants.ApiConstants.*;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class NotificationServiceImpl implements NotificationService {

    private final ProjectService projectService;
    private final NotificationMapper notificationMapper;
    private final NotificacaoRepository notificacaoRepository;

    public NotificationServiceImpl(@Lazy ProjectService projectService,
                                   NotificationMapper notificationMapper,
                                   NotificacaoRepository notificacaoRepository) {
        this.projectService = projectService;
        this.notificationMapper = notificationMapper;
        this.notificacaoRepository = notificacaoRepository;
    }

    public Boolean insertNotification(NotificationRequestDto notificationRequest) {
        validaEmailCadastrado(notificationRequest.getEmail(), notificationRequest.getProjetoId());
        Notificacao notificacao = notificationMapper.map(notificationRequest, getProjectById(
                notificationRequest.getProjetoId(),
                notificationRequest.getUuid()
                )
        );
        return notificacaoRepository.save(notificacao) != null;
    }

    @Override
    public void updateNotification(NotificationUpdateRequestDto notificationUpdateRequestDto) {

        Notificacao notificacaoAntiga = getNotification(notificationUpdateRequestDto.getId());

        if(!notificacaoAntiga.getEmail().equals(notificationUpdateRequestDto.getEmail())){
            validaEmailCadastrado(notificationUpdateRequestDto.getEmail(), notificationUpdateRequestDto.getProjetoId());
        }

        Notificacao notificacao = notificationMapper.map(notificationUpdateRequestDto, getProjectById(
                notificationUpdateRequestDto.getProjetoId(),
                notificationUpdateRequestDto.getUuid()
                )
        );

        notificacaoRepository.save(notificacao);
    }

    @Override
    public void deleteNotification(Long id) {
        notificacaoRepository.delete(
                getNotification(id)
        );
    }

    public NotificationRequestDto findNotificationById(Long id) {
        return notificationMapper.mapToDto(getNotification(id)
        );
    }

    private Notificacao getNotification(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new NotificationErrorException(
                                MSG_ERROR_NOTIFICATION_NOT_FOUND,
                                UUID.randomUUID().toString()
                        )
                );
    }

    public List<NotificationRequestDto> findNotificationByProjectId(Long projectId) {
        return notificationMapper.maptoListDto(
                notificacaoRepository.findByProjectId(projectId)
        );
    }

    @Override
    public List<String> findEmailsByProject(Long projectId) {
        return findNotificationByProjectId(projectId)
                .stream()
                .map(notification -> notification.getEmail())
                .collect(Collectors.toList());
    }

    private void validaEmailCadastrado(String email,Long projectId) {
        Optional<Notificacao> optionalNotificacao = notificacaoRepository.findFirstByEmail(email);
        if (optionalNotificacao.isPresent() && optionalNotificacao.get().getProject().getId().equals(projectId)) {
            throw new NotificationErrorException(MSG_ERROR_EMAIL_NOTIFICATICATION, UUID.randomUUID().toString());
        }
    }

    private Projeto getProjectById(Long projectId, String uuid) {
        return projectService.findById(projectId)
                .orElseThrow(() -> new NotificationErrorException(MSG_ERROR_PROJECT_NOT_FOUND,
                                uuid
                        )
                );
    }

}
