package br.com.pucminas.apiproducer.producers;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailProducerServiceImpl implements EmailProducerService{

    @Autowired
    private RabbitTemplate template;

    @Value(ApiConstants.RABBIT_QUEUE_NAME)
    private String queue;

    @Async
    public void sendDataQueue(EmailDto emailDto){
        template.convertSendAndReceive(queue,emailDto);
    }

    @Override
    public void sendEmail(String uuid, String subject, String body, List<String> emails,Long projetoId) {
        sendDataQueue(
                EmailDto.builder()
                        .uuid(uuid)
                        .de("site@teste.com")
                        .para("teste@teste.com")
                        .assunto(subject)
                        .corpo(body)
                        .emails(emails)
                        .projeto(projetoId)
                        .build()
        );
    }

}
