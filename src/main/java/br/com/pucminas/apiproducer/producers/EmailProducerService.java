package br.com.pucminas.apiproducer.producers;

import java.util.List;

public interface EmailProducerService {
    void sendEmail(String uuid, String subject, String body, List<String> emails);
}
