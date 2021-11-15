package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Timeline;
import br.com.pucminas.apiproducer.enums.EventsEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {

    Optional<Timeline> findById(Long id);
    List<Timeline> findByProjectId(Long projectId);
    List<Timeline> findByProjectIdAndStatus(Long projectId, StatusEnum status);
    List<Timeline> findByProjectIdAndStatusAndEvento(Long projectId, StatusEnum status, EventsEnum evento);
}
