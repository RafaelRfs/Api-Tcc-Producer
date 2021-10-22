package br.com.pucminas.apiproducer.consumers;

import br.com.pucminas.apiproducer.constants.ApiConstants;
import br.com.pucminas.apiproducer.dtos.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {

    @RabbitListener(queues = ApiConstants.RABBIT_QUEUE_NAME)
    public void listen(@Payload EmailDto emailDto){
        log.info("Do anything ");

    }

}
