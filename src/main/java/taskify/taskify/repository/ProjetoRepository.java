package taskify.taskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskify.taskify.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
