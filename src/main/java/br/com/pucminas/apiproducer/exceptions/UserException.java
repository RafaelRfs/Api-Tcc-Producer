package br.com.pucminas.apiproducer.exceptions;

public class UserException extends ApplicationException{

    public UserException(String msg, String uuid){
        super(msg, uuid);
    }
}
