package com.bandtec.hyperxpress.hyperxpressproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estadoUf;

    @NotBlank
    @Size(min = 8, max = 10)
    private String cep;

    @NotBlank
    @Size(min = 3, max = 30)
    private String bairro;

    @NotBlank
    @Size(min = 3, max = 50)
    private String logradouro;

    @NotBlank
    @Size(min = 1, max = 5)
    private String numero;

    @NotBlank
    @Size(min = 3, max = 30)
    private String cidade;


    private String complemento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario codigoUsuario;

    public Endereco() {
    }

    private Endereco(Integer id, String estadoUf, String cep, String bairro, String logradouro, String numero, String cidade, String complemento, Usuario codigoUsuario) {
        this.id = id;
        this.estadoUf = estadoUf;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.complemento = complemento;
        this.codigoUsuario = codigoUsuario;
    }

    public static Endereco criarEndereco(Integer id, String estadoUf, String cep, String bairro, String logradouro, String numero, String cidade, String complemento, Usuario codigoUsuario) {
        return new Endereco(id, estadoUf, cep, bairro, logradouro, numero, cidade, complemento, codigoUsuario);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstadoUf() {
        return estadoUf;
    }

    public void setEstadoUf(String estadoUf) {
        this.estadoUf = estadoUf;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String CEP) {
        this.cep = CEP;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
