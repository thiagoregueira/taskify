package taskify.taskify.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taskify.taskify.model.Tarefa;
import taskify.taskify.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    // cadastrar tarefa
    @PostMapping
    public Tarefa cadastrarNovaTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // buscar tarefa pelo id
    @GetMapping("/{id}")
    public Optional<Tarefa> buscarTarefaPeloId(@PathVariable("id") Long id) {
        return tarefaRepository.findById(1L);
    }

    // listar todas as tarefas
    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // atualizar tarefa
    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            tarefaExistente.get().setNome(tarefa.getNome());
            tarefaExistente.get().setDescricao(tarefa.getDescricao());
            tarefaExistente.get().setProjeto(tarefa.getProjeto());

            return tarefaRepository.save(tarefaExistente.get());
        }
        return null;
    }

}
