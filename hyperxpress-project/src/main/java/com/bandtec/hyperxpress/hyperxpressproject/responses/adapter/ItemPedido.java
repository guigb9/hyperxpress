package com.bandtec.hyperxpress.hyperxpressproject.responses.adapter;

public class ItemPedido {

    private String tituloItem;
    private Double precoItem;
    private Integer quantidadeItem;


    public ItemPedido(String tituloItem, Double precoItem, Integer quantidadeItem) {
        this.tituloItem = tituloItem;
        this.precoItem = precoItem;
        this.quantidadeItem = quantidadeItem;
    }


    public String getTituloItem() {
        return tituloItem;
    }

    public void setTituloItem(String tituloItem) {
        this.tituloItem = tituloItem;
    }

    public Double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(Double precoItem) {
        this.precoItem = precoItem;
    }

    public Integer getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "tituloItem='" + tituloItem + '\'' +
                ", precoItem=" + precoItem +
                ", quantidadeItem=" + quantidadeItem +
                '}';
    }
}
