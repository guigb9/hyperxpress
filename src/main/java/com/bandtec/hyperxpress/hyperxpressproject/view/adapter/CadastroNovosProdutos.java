package com.bandtec.hyperxpress.hyperxpressproject.view.adapter;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;

public class CadastroNovosProdutos {
    private Integer protocolo;

    private Produto produto;

    public CadastroNovosProdutos(Integer protocolo, Produto produtos) {
        this.protocolo = protocolo;
        this.produto = produtos;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public Produto getProdutos() {
        return produto;
    }

    public void setProdutos(Produto produtos) {
        this.produto = produtos;
    }
}
