package br.com.pucminas.apiproducer.exceptions;

import lombok.Getter;

@Getter
public class ProjectNotFoundException extends RuntimeException{

    private final Long projectID;

    public ProjectNotFoundException(String msg, Long projectID){
        super(msg);
        this.projectID = projectID;
    }
}
