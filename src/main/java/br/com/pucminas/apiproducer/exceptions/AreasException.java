package br.com.pucminas.apiproducer.exceptions;

import java.util.UUID;

public class AreasException extends ApplicationException{

    public AreasException(String msg){
        super(msg, UUID.randomUUID().toString());
    }
}
