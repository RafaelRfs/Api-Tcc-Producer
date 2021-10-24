package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}
