package com.bandtec.hyperxpress.hyperxpressproject.view.dto;

public class ProdutoGeralDTO {

    private Long idProduto;

    private String nomeProduto;

    private Double precoProduto;

    private String tamanhoProduto;

    private String nomeUsuarioProduto;

    private String emailUsuarioProduto;

    private Boolean trocavel;

    private String marca;

    private String descricaoProduto;

    private String codigoUsuarioProdCpf;

    private Long codigoPedidoId;

    private String telefone;

    private String status;

    private ProdutoGeralDTO() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(String tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public String getNomeUsuarioProduto() {
        return nomeUsuarioProduto;
    }

    public void setNomeUsuarioProduto(String nomeUsuarioProduto) {
        this.nomeUsuarioProduto = nomeUsuarioProduto;
    }

    public String getEmailUsuarioProduto() {
        return emailUsuarioProduto;
    }

    public void setEmailUsuarioProduto(String emailUsuarioProduto) {
        this.emailUsuarioProduto = emailUsuarioProduto;
    }

    public Boolean getTrocavel() {
        return trocavel;
    }

    public void setTrocavel(Boolean trocavel) {
        this.trocavel = trocavel;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Long getCodigoPedidoId() {
        return codigoPedidoId;
    }

    public void setCodigoPedidoId(Long codigoPedidoId) {
        this.codigoPedidoId = codigoPedidoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigoUsuarioProdCpf() {
        return codigoUsuarioProdCpf;
    }

    public void setCodigoUsuarioProdCpf(String codigoUsuarioProdCpf) {
        this.codigoUsuarioProdCpf = codigoUsuarioProdCpf;
    }
}
