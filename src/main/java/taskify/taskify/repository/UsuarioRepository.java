package taskify.taskify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import taskify.taskify.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
