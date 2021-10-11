package com.bandtec.hyperxpress.hyperxpressproject.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomeProduto;

    @NotBlank
    @Size(min = 20, max = 255)
    private String descricaoProduto;

    @Positive
    private Double precoProduto;

    @NotBlank
    private String tamanhoProduto;

    private String statusProduto;

    @NotBlank
    private String genero;

    @NotBlank
    private String telefone;

    @NotNull
    private Boolean trocavel;

    private Integer nivelDestaque;

    public Produto() {
    }

    private Produto(Long id,
                    String nomeProduto,
                    String descricaoProduto,
                    Double precoProduto,
                    String tamanhoProduto,
                    String statusProduto,
                    String genero,
                    String telefone,
                    Boolean trocavel,
                    Integer nivelDestaque,
                    SubCategoria subCategoria,
                    Usuario codigoUsuarioProd,
                    Pedido codigoPedido,
                    String tecido,
                    String marca) {

        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.tamanhoProduto = tamanhoProduto;
        this.statusProduto = statusProduto;
        this.genero = genero;
        this.telefone = telefone;
        this.trocavel = trocavel;
        this.nivelDestaque = nivelDestaque;
        this.subCategoria = subCategoria;
        this.codigoUsuarioProd = codigoUsuarioProd;
        this.codigoPedido = codigoPedido;
        this.tecido = tecido;
        this.marca = marca;
    }

    public static Produto criarProduto(Long id,
                                       String nomeProduto,
                                       String descricaoProduto,
                                       Double precoProduto,
                                       String tamanhoProduto,
                                       String statusProduto,
                                       String genero, String telefone,
                                       Boolean trocavel, Integer nivelDestaque,
                                       SubCategoria subCategoria,
                                       Usuario codigoUsuarioProd,
                                       Pedido codigoPedido,
                                       String tecido,
                                       String marca) {
        return new Produto(id,
                nomeProduto,
                descricaoProduto,
                precoProduto,
                tamanhoProduto,
                statusProduto,
                genero,
                telefone,
                trocavel,
                nivelDestaque,
                subCategoria,
                codigoUsuarioProd,
                codigoPedido,
                tecido,
                marca);
    }

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private SubCategoria subCategoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario codigoUsuarioProd;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido codigoPedido;

    @NotBlank
    @Length(min = 1, max = 50)
    private String tecido;

    @NotBlank
    @Length(min = 1, max = 35)
    private String marca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
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

    public String getStatusProduto() {
        return statusProduto;
    }

    public void setStatusProduto(String statusProduto) {
        this.statusProduto = statusProduto;
    }

    public Boolean getTrocavel() {
        return trocavel;
    }

    public void setTrocavel(Boolean trocavel) {
        this.trocavel = trocavel;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Usuario getCodigoUsuarioProd() {
        return codigoUsuarioProd;
    }

    public void setCodigoUsuarioProd(Usuario codigoUsuarioProd) {
        this.codigoUsuarioProd = codigoUsuarioProd;
    }

    public Pedido getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Pedido codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Integer getNivelDestaque() {
        return nivelDestaque;
    }

    public void setNivelDestaque(Integer nivelDestaque) {
        this.nivelDestaque = nivelDestaque;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
