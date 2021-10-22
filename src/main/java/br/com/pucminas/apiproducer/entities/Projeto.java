package br.com.pucminas.apiproducer.entities;

import java.time.LocalDateTime;

public class Projeto {
    private Long id;
    private Long usuarioId;
    private String nome;
    private String cliente;
    private LocalDateTime dataPrevisaoEntrega;
}
