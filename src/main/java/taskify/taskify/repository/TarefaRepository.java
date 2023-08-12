package taskify.taskify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import taskify.taskify.model.Tarefa;

import java.time.LocalDate;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :idUsuario")
    List<Tarefa> findAllByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idProjeto")
    List<Tarefa> findAllByIdProjeto(@Param("idProjeto") Long idProjeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :idUsuario AND t.projeto.idProjeto = :idProjeto")
    List<Tarefa> findAllByIdUsuarioAndIdProjeto(@Param("idUsuario") Long idUsuario,
            @Param("idProjeto") Long idProjeto);

    Optional<Tarefa> findByNome(String nome);

    List<Tarefa> findByDataDeCriacao(LocalDate dataDeCriacao);

    List<Tarefa> findByDataDeConclusao(LocalDate dataDeConclusao);

    List<Tarefa> findByPrioridade(String prioridade);

    List<Tarefa> findByStatus(String status);

}
