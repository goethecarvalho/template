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
}
