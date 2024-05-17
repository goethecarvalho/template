package br.com.core.template.domain.vo;

import br.com.core.template.domain.entity.Usuario;

import java.io.Serializable;
import java.time.LocalDate;

public record DadosDetalheUsuario (
    Long id,
    String nome,
    String cpf,
    LocalDate data,
    String email,
    String login,
    String senha) implements Serializable {

    public DadosDetalheUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getData(), usuario.getEmail(), usuario.getLogin(), usuario.getSenha());
    }

}
