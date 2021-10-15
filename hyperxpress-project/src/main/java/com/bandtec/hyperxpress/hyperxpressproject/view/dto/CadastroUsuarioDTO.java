package com.bandtec.hyperxpress.hyperxpressproject.view.dto;


import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CadastroUsuarioDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String nome;

    @NotBlank
    @Size(min = 5, max = 45)
    private String avatar;

    @CPF
    private String cpf;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 45)
    private String senha;

    @Past
    private LocalDate dataNasc;

    private String googleId;

    private Boolean emailConfirmado;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estadoUf;

    @NotBlank
    @Size(min = 8, max = 10)
    private String cep;

    @NotBlank
    @Size(min = 3, max = 30)
    private String bairro;

    @NotBlank
    @Size(min = 3, max = 80)
    private String logradouro;

    @NotBlank
    @Size(min = 1, max = 5)
    private String numero;

    @NotBlank
    @Size(min = 3, max = 30)
    private String cidade;

    private String complemento;

    public CadastroUsuarioDTO(String nome, String avatar, String cpf, String email, String senha, LocalDate dataNasc, String googleId, Boolean emailConfirmado, String estadoUf, String cep, String bairro, String logradouro, String numero, String cidade, String complemento) {
        this.nome = nome;
        this.avatar = avatar;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.googleId = googleId;
        this.emailConfirmado = emailConfirmado;
        this.estadoUf = estadoUf;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.complemento = complemento;
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

    public String getEstadoUf() {
        return estadoUf;
    }

    public void setEstadoUf(String estadoUf) {
        this.estadoUf = estadoUf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


}
