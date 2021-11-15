package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Status;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    Optional<Status> findById(Long id);
    List<Status> findByStatus(StatusEnum status);
    List<Status> findByStatusAndEvento(StatusEnum status,  EventsEnum evento);
    Optional<Status> findFirstByStatusAndEvento(StatusEnum status,  EventsEnum evento);
}
