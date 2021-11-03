package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    Optional<Notificacao> findById(Long id);
    Optional<Notificacao> findFirstByEmail(String email);
    List<Notificacao> findByProjectId(Long projectId);
}
