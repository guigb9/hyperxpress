package com.bandtec.hyperxpress.hyperxpressproject.model;

import javax.persistence.*;

@Entity
public class FavoritosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorito;

    @ManyToOne
    private Produto produtoFavorito;

    @ManyToOne
    private Usuario usuarioFavoritou;

    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Produto getProdutoFavorito() {
        return produtoFavorito;
    }

    public void setProdutoFavorito(Produto produtoFavorito) {
        this.produtoFavorito = produtoFavorito;
    }

    public Usuario getUsuarioFavoritou() {
        return usuarioFavoritou;
    }

    public void setUsuarioFavoritou(Usuario usuarioFavoritou) {
        this.usuarioFavoritou = usuarioFavoritou;
    }
}
