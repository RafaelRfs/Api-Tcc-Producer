package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import java.util.List;

public interface NotificationService {
    Boolean insertNotification(NotificationRequestDto notificationRequest);
    NotificationRequestDto findNotificationById(Long id);
    List<NotificationRequestDto> findNotificationByProjectId(Long projectId);
}
