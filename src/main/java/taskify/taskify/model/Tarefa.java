package taskify.taskify.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taskify.taskify.constants.Prioridade;
import taskify.taskify.constants.Status;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTarefa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, name = "dataDeCriacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeCriacao;

    @Column(name = "dataDeConclusao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeConclusao;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Prioridade prioridade;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProjeto")
    private Projeto projeto;

}
