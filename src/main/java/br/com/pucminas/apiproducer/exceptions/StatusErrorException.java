package br.com.pucminas.apiproducer.exceptions;

import java.util.UUID;

public class StatusErrorException extends ApplicationException {
    public StatusErrorException(String msg) {
        super(msg, UUID.randomUUID().toString());
    }
}
