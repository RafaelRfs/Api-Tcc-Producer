package br.com.pucminas.apiproducer.exceptions;

public class UserNotAuthorizedException extends RuntimeException{

    public UserNotAuthorizedException(String msg){
        super(msg);
    }

}
