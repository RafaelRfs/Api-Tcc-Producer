package br.com.pucminas.apiproducer.entities;

import java.time.LocalDateTime;

public class Timeline {
    private Long id;
    private Long projetoId;
    private Status status;
    private String descricao;
    private String url;
    private LocalDateTime dataPostagem;
}
