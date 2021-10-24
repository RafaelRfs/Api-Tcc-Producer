package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
}
