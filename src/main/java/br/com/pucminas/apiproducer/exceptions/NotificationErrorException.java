package br.com.pucminas.apiproducer.exceptions;

public class NotificationErrorException extends ApplicationException{

    public NotificationErrorException(String msg, String uuid){
        super(msg,uuid);
    }

}
