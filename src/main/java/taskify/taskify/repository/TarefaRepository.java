package taskify.taskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskify.taskify.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
