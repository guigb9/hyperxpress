package com.bandtec.hyperxpress.hyperxpressproject.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 30)
    private String nomeFp;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer id, String nomeFp) {
        this.id = id;
        this.nomeFp = nomeFp;
    }

    public static FormaPagamento criarFormaPagamento(Integer id, String nomeFp) {
        return new FormaPagamento(id, nomeFp);
    }

    //    Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFp() {
        return nomeFp;
    }

    public void setNomeFp(String nomeFp) {
        this.nomeFp = nomeFp;
    }

}
