package br.com.pucminas.apiproducer.entities;

import br.com.pucminas.apiproducer.enums.AreasEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private User user;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_inicio_projeto")
    private LocalDate dataInicioProjeto;

    @Column(name = "data_previsao_entrega")
    private LocalDate dataPrevisaoEntrega;

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "area")
    private AreasEnum area;

    @Column(name = "img")
    private String img;

}
