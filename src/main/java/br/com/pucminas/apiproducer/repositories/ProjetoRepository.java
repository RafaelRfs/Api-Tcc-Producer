package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    Optional<Projeto> findById(Long id);

    List<Projeto> findByUserId(Long userId);

    List<Projeto> findByUserIdAndStatus(Long userId, StatusEnum status);

    List<Projeto> findByUserIdAndStatusAndEvento(Long userId, StatusEnum status, EventsEnum evento);
}
