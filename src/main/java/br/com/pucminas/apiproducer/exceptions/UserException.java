package br.com.pucminas.apiproducer.exceptions;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private String uuid;
    public UserException(String msg, String uuid){
        super(msg);
        this.uuid = uuid;
    }
}
