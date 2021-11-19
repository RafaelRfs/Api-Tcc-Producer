package br.com.pucminas.apiproducer.exceptions;

import java.util.UUID;

public class EventsErrorException extends ApplicationException {

    public EventsErrorException(String msg){
        super(msg, UUID.randomUUID().toString());
    }

}
