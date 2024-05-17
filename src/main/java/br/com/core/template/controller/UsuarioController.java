package br.com.core.template.controller;

import br.com.core.template.domain.service.UsuarioService;
import br.com.core.template.domain.vo.DadosDetalheUsuario;
import br.com.core.template.domain.vo.DadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosUsuario dados, UriComponentsBuilder uriBuilder){

        var dadosUsuarioCadastrado = service.cadastrarUsuario(dados);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(dadosUsuarioCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosUsuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var usuario = service.listar(paginacao);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheUsuario> detalhar(@PathVariable Long id) {
        var usuario = service.detalhar(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosUsuario dados) {
        var usuarioAtualizado = service.atualizarUsuario(id, dados);
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
