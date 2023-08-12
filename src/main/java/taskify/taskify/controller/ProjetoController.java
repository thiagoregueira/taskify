package taskify.taskify.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taskify.taskify.model.Projeto;
import taskify.taskify.repository.ProjetoRepository;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    // cadastrar projeto
    @PostMapping
    public Projeto cadastrarNovoProjeto(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // buscar projeto pelo id
    @GetMapping("/{id}")
    public Optional<Projeto> buscarProjetoPeloId(@PathVariable("id") Long id) {
        return projetoRepository.findById(id);
    }

    // buscar projeto pelo nome
    @GetMapping("/nome/{nome}")
    public Optional<Projeto> buscarProjetoPeloNome(@PathVariable("nome") String nome) {
        return projetoRepository.findByNome(nome);
    }

    // listar todos os projetos
    @GetMapping
    public Page<Projeto> listarProjetos(Pageable pageable) {
        return projetoRepository.findAll(pageable);
    }

    // listar projetos pelo id do usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Projeto> listarProjetosPeloIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return projetoRepository.findByIdUsuario(idUsuario);
    }

    // atualizar projeto
    @PutMapping("/{id}")
    public Projeto atualizarProjeto(@PathVariable("id") Long id, @RequestBody Projeto projeto) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);

        if (projetoExistente.isPresent()) {
            projetoExistente.get().setNome(projeto.getNome());
            projetoExistente.get().setDescricao(projeto.getDescricao());
            projetoExistente.get().setUsuario(projeto.getUsuario());

            return projetoRepository.save(projetoExistente.get());
        }
        return null;
    }

    // deletar projeto
    @DeleteMapping("/{id}")
    public String deletarProjetoPeloId(@PathVariable("id") Long id) {
        projetoRepository.deleteById(id);

        return "Projeto deletado com sucesso";
    }

}
