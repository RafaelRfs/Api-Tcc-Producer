package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.dtos.NotificationRequestDto;
import br.com.pucminas.apiproducer.dtos.NotificationUpdateRequestDto;
import java.util.List;

public interface NotificationService {
    NotificationRequestDto findNotificationById(Long id);
    List<NotificationRequestDto> findNotificationByProjectId(Long projectId);
    List<String> findEmailsByProject(Long projectId);
    Boolean insertNotification(NotificationRequestDto notificationRequest);
    void updateNotification(NotificationUpdateRequestDto notificationUpdateRequestDto);
    void deleteNotification(Long id);
}
