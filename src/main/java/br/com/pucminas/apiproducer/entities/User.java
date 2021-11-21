package br.com.pucminas.apiproducer.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuarios")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;

    @Column(name = "email")
    @NotBlank(message = "Email deve ser preenchido")
    private String email;

    @Column(name = "senha")
    @NotBlank(message = "Senha deve ser preenchida")
    private String senha;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dataCriacao;

    @Column(name = "uuid")
    @NotBlank(message = "UUID deve ser preenchido")
    private String uuid;

}
