package br.com.core.template.domain.service;

import br.com.core.template.domain.RegraDeNegocioException;
import br.com.core.template.domain.entity.Usuario;
import br.com.core.template.domain.repository.UsuarioRepository;
import br.com.core.template.domain.vo.DadosDetalheUsuario;
import br.com.core.template.domain.vo.DadosUsuario;
import br.com.core.template.infra.ValidarCpf;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaUsuarios")
    public Page<DadosDetalheUsuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheUsuario::new);
    }


    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public DadosDetalheUsuario cadastrarUsuario(DadosUsuario dados) {
        if (dados.nome() == null || dados.nome().isEmpty()) {
            throw new RegraDeNegocioException("O nome deve estar preenchido!");
        }

        ValidarCpf validador = new ValidarCpf();

        if(validador.valida(dados.cpf())) {
            if (dados.cpf() == null || dados.cpf().isEmpty()) {
                throw new RegraDeNegocioException("O CPF do usuário deve estar preenchido.");
            }
        }else{
            throw new RegraDeNegocioException("O CPF do usuário não é válido.");
        }

        var usuario = new Usuario(dados);

        repository.save(usuario);

        return new DadosDetalheUsuario(usuario);
    }

    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public DadosDetalheUsuario atualizarUsuario(Long id, DadosUsuario dados) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DadosUsuario usuarioAtualizado = new DadosUsuario(
                dados.id(),
                dados.nome(),
                dados.cpf(),
                dados.data(),
                dados.email(),
                dados.login(),
                dados.senha()
        );

        usuario.atualizarDados(usuarioAtualizado);

        repository.save(usuario);

        return new DadosDetalheUsuario(usuario);
    }

    public DadosDetalheUsuario detalhar(Long id) {
        var usuario = repository.findById(id).get();
        return new DadosDetalheUsuario(usuario);
    }

    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public void deletarUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }
}
