package br.com.core.template.domain.entity;

import br.com.core.template.domain.vo.DadosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate data;

    private String email;

    private String login;

    private String senha;

    public Usuario(DadosUsuario dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.data = dados.data();
        this.email = dados.email();
        this.login = dados.login();
        this.senha = dados.senha();
    }

    public void atualizarDados(DadosUsuario dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.data = dados.data();
        this.email = dados.email();
        this.login = dados.login();
        this.senha = dados.senha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
