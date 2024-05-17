package br.com.core.template.domain.vo;


import java.io.Serializable;
import java.time.LocalDate;

public record DadosUsuario(
    Long id,
    String nome,
    String cpf,
    LocalDate data,
    String email,
    String login,
    String senha) implements Serializable {

    public DadosUsuario(String nome, String cpf, LocalDate data, String email, String login, String senha) {
        this(null, nome, cpf, data, email, login,  senha);
    }

}
