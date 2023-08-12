package taskify.taskify.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import taskify.taskify.constants.Genero;
import taskify.taskify.dto.EnderecoDto;
import taskify.taskify.model.Endereco;
import taskify.taskify.model.Usuario;
import taskify.taskify.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // cadastrar usuario
    @PostMapping
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    // buscar usuario pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    // buscar usuario pelo email
    @GetMapping("/email/{email}")
    public Optional<Usuario> buscarUsuarioPeloEmail(@PathVariable("email") String email) {
        return usuarioRepository.findByEmail(email);
    }

    // buscar usuario pelo nome
    @GetMapping("/nome/{nome}")
    public Optional<Usuario> buscarUsuarioPeloNome(@PathVariable("nome") String nome) {
        return usuarioRepository.findByNome(nome);
    }

    // buscar usuario pelo sobrenome
    @GetMapping("/sobrenome/{sobrenome}")
    public Optional<Usuario> buscarUsuarioPeloSobrenome(@PathVariable("sobrenome") String sobrenome) {
        return usuarioRepository.findBySobrenome(sobrenome);
    }

    // listar todos os usuarios
    @GetMapping
    public Page<Usuario> listarUsuarios(
            @PageableDefault(size = 5, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return usuarioRepository.findAll(paginacao);
    }

    // listar usuarios pelo genero
    @GetMapping("/genero/{genero}")
    public List<Usuario> listarUsuariosPeloGenero(@PathVariable("genero") Genero genero) {
        return usuarioRepository.findByGenero(genero);
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
            usuarioExistente.get().setGenero(usuario.getGenero());

            return usuarioRepository.save(usuarioExistente.get());
        }

        return null;
    }

    // deletar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
    }

    @GetMapping("/endereco/{cep}")
    public Endereco buscarEnderecoPeloCep(@PathVariable("cep") String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, Endereco.class);
    }

    // criar metodo para cadastrar o endereco do usuario que passará o cep e o
    // número pelo corpo e através do cep buscar o endereço completo
    @PostMapping("/endereco")
    public ResponseEntity<Usuario> cadastrarEndereco(@RequestBody EnderecoDto enderecoDto) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(enderecoDto.getIdUsuario());

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String url = "https://viacep.com.br/ws/" + enderecoDto.getCep() + "/json/";

        RestTemplate restTemplate = new RestTemplate();

        Endereco endereco = restTemplate.getForObject(url, Endereco.class);

        if (Objects.nonNull(endereco)) {
            endereco.setNumero(enderecoDto.getNumero());
        }

        usuarioExistente.get().setEndereco(endereco);

        return ResponseEntity.ok().body(usuarioRepository.save(usuarioExistente.get()));

    }

}
