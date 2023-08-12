package taskify.taskify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import taskify.taskify.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM tb_projetos p WHERE p.usuario.idUsuario = :idUsuario")
    List<Projeto> findByIdUsuario(@Param("idUsuario") Long idUsuario);

    Optional<Projeto> findByNome(String nome);

}
