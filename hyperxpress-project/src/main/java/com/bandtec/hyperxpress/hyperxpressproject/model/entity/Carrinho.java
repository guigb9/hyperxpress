package com.bandtec.hyperxpress.hyperxpressproject.model.entity;

import javax.persistence.*;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produtoAssociado;

    @OneToOne
    private Usuario usuarioAdicionou;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProdutoAssociado() {
        return produtoAssociado;
    }

    public void setProdutoAssociado(Produto produtoAssociado) {
        this.produtoAssociado = produtoAssociado;
    }

    public Usuario getUsuarioAdicionou() {
        return usuarioAdicionou;
    }

    public void setUsuarioAdicionou(Usuario usuarioAdicionou) {
        this.usuarioAdicionou = usuarioAdicionou;
    }
}
