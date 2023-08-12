package taskify.taskify.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taskify.taskify.model.Usuario;
import taskify.taskify.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // cadastrar usuario
    @PostMapping
    public Usuario cadastrarNovoUsuario(@RequestBody Usuario usurio) {
        return usuarioRepository.save(usurio);
    }

    // buscar usuario pelo id
    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<Usuario> buscarUsuarioPeloEmail(@PathVariable("email") String email) {
        return usuarioRepository.findByEmail(email);
    }

    // listar todos os usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // atualizar usuario
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            usuarioExistente.get().setNome(usuario.getNome());
            usuarioExistente.get().setSobrenome(usuario.getSobrenome());
            usuarioExistente.get().setEmail(usuario.getEmail());
            usuarioExistente.get().setSenha(usuario.getSenha());

            return usuarioRepository.save(usuarioExistente.get());
        }

        return null;
    }

    // deletar usuario
    @DeleteMapping("/{id}")
    public String deletarUsuarioPeloId(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);

        return "Usuario deletado com sucesso";
    }

}
