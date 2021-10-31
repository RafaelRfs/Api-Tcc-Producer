package br.com.pucminas.apiproducer.controllers;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbsController {
    @InitBinder
    public void populateCustomerRequest(WebDataBinder binder){
        binder.setDisallowedFields(new String[2]);
    }
}
