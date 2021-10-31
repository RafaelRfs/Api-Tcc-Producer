package br.com.pucminas.apiproducer.services;

import br.com.pucminas.apiproducer.entities.Projeto;

import java.util.Optional;

public interface ProjectService {
    Optional<Projeto> findById(Long id);
}
