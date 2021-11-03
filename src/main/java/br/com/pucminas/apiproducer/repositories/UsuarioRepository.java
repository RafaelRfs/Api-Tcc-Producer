package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
