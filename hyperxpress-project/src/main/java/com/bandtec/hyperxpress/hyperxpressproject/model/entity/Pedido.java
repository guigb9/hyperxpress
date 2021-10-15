package com.bandtec.hyperxpress.hyperxpressproject.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private Double valorTotal;

    @PositiveOrZero
    private Double valorFrete;

    @NotBlank
    @Size(min = 3, max = 20)
    private String status;

    @NotNull
    private LocalDateTime dataPedido;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "formapagamento_id")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario codigoUsuario;

    public Pedido() {
    }

    private Pedido(Long id, Double valorTotal, Double valorFrete, String status, LocalDateTime dataPedido, Entrega entrega, FormaPagamento formaPagamento, Usuario codigoUsuario) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.valorFrete = valorFrete;
        this.status = status;
        this.dataPedido = dataPedido;
        this.entrega = entrega;
        this.formaPagamento = formaPagamento;
        this.codigoUsuario = codigoUsuario;
    }

    public static Pedido criarPedido(Long id, Double valorTotal, Double valorFrete, String status, LocalDateTime dataPedido, Entrega entrega, FormaPagamento formaPagamento, Usuario codigoUsuario) {
        return new Pedido(id, valorTotal, valorFrete, status, dataPedido, entrega, formaPagamento, codigoUsuario);
    }


    //    Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
