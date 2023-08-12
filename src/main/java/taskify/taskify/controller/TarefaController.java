package taskify.taskify.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // buscar tarefa pelo nome
    @GetMapping("/nome/{nome}")
    public Optional<Tarefa> buscarTarefaPeloNome(@PathVariable("nome") String nome) {
        return tarefaRepository.findByNome(nome);
    }

    // buscar tarefa pelo id do usuario e id do projeto
    @GetMapping("/usuario/{idUsuario}/projeto/{idProjeto}")
    public List<Tarefa> buscarTarefaPeloIdUsuarioEIdProjeto(@PathVariable("idUsuario") Long idUsuario,
            @PathVariable("idProjeto") Long idProjeto) {
        return tarefaRepository.findAllByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
    }

    // buscar tarefa pela data cria;ão
    @GetMapping("/dataDeCriacao/{dataDeCriacao}")
    public List<Tarefa> buscarTarefaPelaDataDeCriacao(@PathVariable("dataDeCriacao") LocalDate dataDeCriacao) {
        return tarefaRepository.findByDataDeCriacao(dataDeCriacao);
    }

    // buscar tarefa pela data de conclusão
    @GetMapping("/dataDeConclusao/{dataDeConclusao}")
    public List<Tarefa> buscarTarefaPelaDataDeConclusao(
            @PathVariable("dataDeConclusao") LocalDate dataDeConclusao) {
        return tarefaRepository.findByDataDeConclusao(dataDeConclusao);
    }

    // buscar tarefa pelo status
    @GetMapping("/status/{status}")
    public List<Tarefa> buscarTarefaPeloStatus(@PathVariable("status") String status) {
        return tarefaRepository.findByStatus(status);
    }

    // buscar tarefa pela prioridade
    @GetMapping("/prioridade/{prioridade}")
    public List<Tarefa> buscarTarefaPelaPrioridade(@PathVariable("prioridade") String prioridade) {
        return tarefaRepository.findByPrioridade(prioridade);
    }

    // listar tarefas pelo id do usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Tarefa> listarTarefasPeloIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return tarefaRepository.findAllByIdUsuario(idUsuario);
    }

    // listar tarefas pelo id do projeto
    @GetMapping("/projeto/{idProjeto}")
    public List<Tarefa> listarTarefasPeloIdProjeto(@PathVariable("idProjeto") Long idProjeto) {
        return tarefaRepository.findAllByIdProjeto(idProjeto);
    }

    // listar todas as tarefas
    @GetMapping
    public Page<Tarefa> listarTarefas(Pageable pageable) {
        return tarefaRepository.findAll(pageable);
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
