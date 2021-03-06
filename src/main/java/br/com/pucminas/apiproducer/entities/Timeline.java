package br.com.pucminas.apiproducer.entities;

import br.com.pucminas.apiproducer.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_timeline")
public class Timeline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "timeline_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "projeto_id", referencedColumnName = "projeto_id")
    private Projeto project;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "url")
    private String url;

    @Column(name = "data_postagem")
    private LocalDateTime dataPostagem;

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "legenda")
    private String legenda;

    @Column(name = "img")
    private String img;
}
