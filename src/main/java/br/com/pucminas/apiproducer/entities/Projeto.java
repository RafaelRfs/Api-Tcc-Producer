package br.com.pucminas.apiproducer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_projeto")
public class Projeto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "projeto_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private User user;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "data_previsao_entrega")
    private LocalDate dataPrevisaoEntrega;

}
