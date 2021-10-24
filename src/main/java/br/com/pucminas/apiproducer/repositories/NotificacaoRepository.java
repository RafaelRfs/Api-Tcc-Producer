package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

}
