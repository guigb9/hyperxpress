package com.bandtec.hyperxpress.hyperxpressproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 5_000_000)
    private byte[] conteudoImagem;

    @NotBlank
    private String nomeImagem;

    public ImagemUsuario() {
    }

    private ImagemUsuario(Long id, @NotNull byte[] conteudoImagem, String nomeImagem, Usuario usuarioAssociado) {
        this.id = id;
        this.conteudoImagem = conteudoImagem;
        this.nomeImagem = nomeImagem;
        this.usuarioAssociado = usuarioAssociado;
    }

    public static ImagemUsuario criarImagemUsuario(Long id, @NotNull byte[] conteudoImagem, String nomeImagem, Usuario usuarioAssociado) {
        return new ImagemUsuario(id, conteudoImagem, nomeImagem, usuarioAssociado);
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    @ManyToOne
    private Usuario usuarioAssociado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getConteudoImagem() {
        return conteudoImagem;
    }

    public void setConteudoImagem(byte[] conteudoImagem) {
        this.conteudoImagem = conteudoImagem;
    }

    public Usuario getUsuarioAssociado() {
        return usuarioAssociado;
    }

    public void setUsuarioAssociado(Usuario usuarioAssociado) {
        this.usuarioAssociado = usuarioAssociado;
    }
}
