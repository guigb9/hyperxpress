package com.bandtec.hyperxpress.hyperxpressproject.model.entity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class AvaliacoesUsuario {
    public AvaliacoesUsuario() {
    }

    private AvaliacoesUsuario(Long id, String comentario, Usuario usuarioAvaliador, Usuario usuarioAvaliado, Double estrelas) {
        this.id = id;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.usuarioAvaliado = usuarioAvaliado;
        this.estrelas = estrelas;
    }

    public static AvaliacoesUsuario novaAvaliacaoUsuario(Long id, String comentario, Usuario usuarioAvaliador, Usuario usuarioAvaliado, Double estrelas){
        return new AvaliacoesUsuario(id, comentario, usuarioAvaliador, usuarioAvaliado, estrelas);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 150)
    @Length(min = 5, max = 140)
    private String comentario;

    @ManyToOne
    private Usuario usuarioAvaliador;

    @ManyToOne
    private Usuario usuarioAvaliado;

    @Positive
    @NotNull
    private Double estrelas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuarioAvaliado() {
        return usuarioAvaliado;
    }

    public void setUsuarioAvaliado(Usuario usuarioAvaliado) {
        this.usuarioAvaliado = usuarioAvaliado;
    }

    public Double getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }

    public Usuario getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }
}
