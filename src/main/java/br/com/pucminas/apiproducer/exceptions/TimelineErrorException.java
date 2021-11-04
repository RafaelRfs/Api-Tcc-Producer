package br.com.pucminas.apiproducer.exceptions;

import java.util.UUID;

public class TimelineErrorException extends ApplicationException {
    public TimelineErrorException(String msg) {
        super(msg, UUID.randomUUID().toString());
    }
}
