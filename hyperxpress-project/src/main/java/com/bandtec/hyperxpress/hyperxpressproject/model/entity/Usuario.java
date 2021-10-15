package com.bandtec.hyperxpress.hyperxpressproject.model.entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 45)
    private String nome;

    @NotBlank
    @Size(min = 5, max = 45)
    private String avatar;

    @CPF
    private String cpf;

    @Email
    @Column(length = 45)
    private String email;

    @NotBlank
    @Size(min = 8, max = 15)
    private String senha;

    @Past
    private LocalDate dataNasc;

    private String googleId;

    @NotNull
    private Boolean emailConfirmado;

    public Usuario() {
    }

    private Usuario(Long id, String nome, String avatar, String cpf, String email, String senha, LocalDate dataNasc, String googleId, Boolean emailConfirmado) {
        this.id = id;
        this.nome = nome;
        this.avatar = avatar;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.googleId = googleId;
        this.emailConfirmado = emailConfirmado;
    }

    public static Usuario criarUsuario(Long id, String nome, String avatar, String cpf, String email, String senha, LocalDate dataNasc, String googleId, Boolean emailConfirmado) {
        return new Usuario(id, nome, avatar, cpf, email, senha, dataNasc, googleId, emailConfirmado);
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Boolean getEmailConfirmado() {
        return emailConfirmado;
    }

    public void setEmailConfirmado(Boolean emailConfirmado) {
        this.emailConfirmado = emailConfirmado;
    }
}
