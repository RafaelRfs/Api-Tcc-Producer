package br.com.pucminas.apiproducer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_timeline")
public class Timeline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "timeline_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "projeto_id", referencedColumnName = "projeto_id")
    private Projeto project;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "url")
    private String url;

    @Column(name = "data_postagem")
    private LocalDateTime dataPostagem;
}
