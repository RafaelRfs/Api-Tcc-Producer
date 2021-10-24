package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
}
