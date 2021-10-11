package com.bandtec.hyperxpress.hyperxpressproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Suporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @NotBlank
    private String assunto;

    @NotBlank
    private String email;

    private Boolean ChamadoAberto = true;

    @NotBlank
    @Size(max = 500)
    private String mensagem;

    public Boolean getChamadoAberto() {
        return ChamadoAberto;
    }

    public void setChamadoAberto(Boolean chamadoAberto) {
        ChamadoAberto = chamadoAberto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
