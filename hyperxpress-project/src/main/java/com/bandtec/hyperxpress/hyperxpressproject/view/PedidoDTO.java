package com.bandtec.hyperxpress.hyperxpressproject.view;

import java.time.LocalDateTime;

public class PedidoDTO {
    private Long id;

    private String nomeUsuarioPedido;

    private String nomeAvatarPedido;

    private String emailUsuarioPedido;

    private String cpfUsuarioPedido;

    private String formaPagamentoNomeFp;

    private String status;

    private LocalDateTime dataPedido;

    private Double valorTotal;

    private Double valorFrete;

    private PedidoDTO() {
    }

    public Long getCodigoPedido() {
        return id;
    }

    public String getNomeUsuarioPedido() {
        return nomeUsuarioPedido;
    }

    public String getNomeAvatarPedido() {
        return nomeAvatarPedido;
    }

    public String getEmailUsuarioPedido() {
        return emailUsuarioPedido;
    }

    public String getCpfUsuarioPedido() {
        return cpfUsuarioPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeUsuarioPedido(String nomeUsuarioPedido) {
        this.nomeUsuarioPedido = nomeUsuarioPedido;
    }

    public void setNomeAvatarPedido(String nomeAvatarPedido) {
        this.nomeAvatarPedido = nomeAvatarPedido;
    }

    public void setEmailUsuarioPedido(String emailUsuarioPedido) {
        this.emailUsuarioPedido = emailUsuarioPedido;
    }

    public void setCpfUsuarioPedido(String cpfUsuarioPedido) {
        this.cpfUsuarioPedido = cpfUsuarioPedido;
    }

    public String getFormaPagamentoNomeFp() {
        return formaPagamentoNomeFp;
    }

    public void setFormaPagamentoNomeFp(String formaPagamentoNomeFp) {
        this.formaPagamentoNomeFp = formaPagamentoNomeFp;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
