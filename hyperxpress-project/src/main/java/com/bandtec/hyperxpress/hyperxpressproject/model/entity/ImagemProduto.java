package com.bandtec.hyperxpress.hyperxpressproject.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 1_000_000_000)
    private byte[] imagem;

    @ManyToOne
    private Produto produtoAssociado;

    @NotNull
    private LocalDateTime dataInsercao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Produto getProdutoAssociado() {
        return produtoAssociado;
    }

    public void setProdutoAssociado(Produto produtoAssociado) {
        this.produtoAssociado = produtoAssociado;
    }

    public LocalDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(LocalDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}
