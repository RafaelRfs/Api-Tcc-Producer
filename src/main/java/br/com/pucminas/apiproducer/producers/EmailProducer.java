package br.com.pucminas.apiproducer.producers;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    @Autowired
    private RabbitTemplate template;

    @Value(ApiConstants.RABBIT_QUEUE_NAME)
    private String queue;


    public void sendDataQueue(EmailDto emailDto){
        template.convertSendAndReceive(queue,emailDto);
    }


}
