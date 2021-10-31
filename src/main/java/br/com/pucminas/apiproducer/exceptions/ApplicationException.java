package br.com.pucminas.apiproducer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException{

    private String uuid;

    public ApplicationException(String msg, String uuid){
        super(msg);
        this.setUuid(uuid);
    }
}
