package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    Optional<Projeto> findById(Long id);

    List<Projeto> findByUserId(Long userId);
}
