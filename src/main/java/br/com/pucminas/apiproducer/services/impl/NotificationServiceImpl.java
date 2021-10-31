package br.com.pucminas.apiproducer.services.impl;

import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.entities.Notificacao;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.exceptions.NotificationErrorException;
import br.com.pucminas.apiproducer.mappers.NotificationMapper;
import br.com.pucminas.apiproducer.repositories.NotificacaoRepository;
import br.com.pucminas.apiproducer.services.NotificationService;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final ProjectService projectService;
    private final NotificationMapper notificationMapper;
    private final NotificacaoRepository notificacaoRepository;

    public static final String MSG_ERROR_EMAIL_NOTIFICATICATION = "Email ja cadastrado para o projeto informado";
    public static final String MSG_ERROR_NOTIFICATION_NOT_FOUND = "Notificação nao encontrada";
    public static final String MSG_ERROR_PROJECT_NOT_FOUND = "Projeto nao encontrado pelo Id passado";

    public Boolean insertNotification(NotificationRequestDto notificationRequest) {
        validaEmailCadastrado(notificationRequest);
        Notificacao notificacao = notificationMapper.map(notificationRequest, getProjectById(
                notificationRequest.getProjetoId(),
                notificationRequest.getUuid()
                )
        );
        return notificacaoRepository
                .save(notificacao) != null;
    }

    public NotificationRequestDto findNotificationById(Long id) {
        return notificationMapper.mapToDto(notificacaoRepository.findById(id)
                .orElseThrow(() -> new NotificationErrorException(
                                MSG_ERROR_NOTIFICATION_NOT_FOUND,
                                UUID.randomUUID().toString()
                        )
                )
        );
    }

    public List<NotificationRequestDto> findNotificationByProjectId(Long projectId) {
        return notificationMapper.maptoListDto(
                notificacaoRepository.findByProjectId(projectId)
        );
    }

    private void validaEmailCadastrado(NotificationRequestDto notificationRequest) {
        Optional<Notificacao> optionalNotificacao = notificacaoRepository.findFirstByEmail(notificationRequest.getEmail());
        if (optionalNotificacao.isPresent() && optionalNotificacao.get().getProject().getId().equals(notificationRequest.getProjetoId()) ){
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
