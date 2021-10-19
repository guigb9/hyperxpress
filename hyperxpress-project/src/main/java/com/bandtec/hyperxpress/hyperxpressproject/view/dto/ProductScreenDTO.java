package com.bandtec.hyperxpress.hyperxpressproject.view.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductScreenDTO {

    public ProductScreenDTO(Long id, String nome, Long idProduto, String nomeProduto, Double precoProduto, Boolean trocavel, Integer nivelDestaque) {
        this.id = id;
        this.nome = nome;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.trocavel = trocavel;
        this.nivelDestaque = nivelDestaque;
    }

    public ProductScreenDTO() {


    }

    private Long id;

    @NotBlank
    @Size(min = 5, max = 45)
    private String nome;

    private Long idProduto;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomeProduto;

    private Double precoProduto;

    @NotNull
    private Boolean trocavel;


    private Integer nivelDestaque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Boolean getTrocavel() {
        return trocavel;
    }

    public void setTrocavel(Boolean trocavel) {
        this.trocavel = trocavel;
    }

    public Integer getNivelDestaque() {
        return nivelDestaque;
    }

    public void setNivelDestaque(Integer nivelDestaque) {
        this.nivelDestaque = nivelDestaque;
    }
}
