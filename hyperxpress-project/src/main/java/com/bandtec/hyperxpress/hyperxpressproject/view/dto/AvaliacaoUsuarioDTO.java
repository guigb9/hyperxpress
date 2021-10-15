package com.bandtec.hyperxpress.hyperxpressproject.view.dto;

public class AvaliacaoUsuarioDTO {
    private Long id;

    private Double estrelas;

    private String comentario;

    private Long idUsuarioAvaliador;

    private Long idUsuarioAvaliado;

    private String nomeUsuarioAvaliador;

    private String nomeUsuarioAvaliado;

    private AvaliacaoUsuarioDTO() {
    }

    public Double getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNomeUsuarioAvaliador() {
        return nomeUsuarioAvaliador;
    }

    public void setNomeUsuarioAvaliador(String nomeUsuarioAvaliador) {
        this.nomeUsuarioAvaliador = nomeUsuarioAvaliador;
    }

    public String getNomeUsuarioAvaliado() {
        return nomeUsuarioAvaliado;
    }

    public void setNomeUsuarioAvaliado(String nomeUsuarioAvaliado) {
        this.nomeUsuarioAvaliado = nomeUsuarioAvaliado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuarioAvaliador() {
        return idUsuarioAvaliador;
    }

    public void setIdUsuarioAvaliador(Long idUsuarioAvaliador) {
        this.idUsuarioAvaliador = idUsuarioAvaliador;
    }

    public Long getIdUsuarioAvaliado() {
        return idUsuarioAvaliado;
    }

    public void setIdUsuarioAvaliado(Long idUsuarioAvaliado) {
        this.idUsuarioAvaliado = idUsuarioAvaliado;
    }
}
