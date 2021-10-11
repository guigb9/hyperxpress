package com.bandtec.hyperxpress.hyperxpressproject.responses.adapter;

public class PedidoReturn {

    private String preferenceInitPoint;
    private Double valorFrete;
    private Double valorTotalPedido;
    private Double somaProdutos;

    public Double getSomaProdutos() {
        return somaProdutos;
    }

    public void setSomaProdutos(Double somaProdutos) {
        this.somaProdutos = somaProdutos;
    }

    public String getPreferenceInitPoint() {
        return preferenceInitPoint;
    }

    public void setPreferenceInitPoint(String preferenceInitPoint) {
        this.preferenceInitPoint = preferenceInitPoint;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }
}
