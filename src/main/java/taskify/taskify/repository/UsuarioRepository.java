package taskify.taskify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import taskify.taskify.constants.Genero;
import taskify.taskify.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findBySobrenome(String sobrenome);

    List<Usuario> findByGenero(Genero genero);

}
