package com.bandtec.hyperxpress.hyperxpressproject.model;

import javax.persistence.*;

@Entity
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria codigoCategoria;

    public SubCategoria() {
    }

    private SubCategoria(Long id, String nome, Categoria codigoCategoria) {
        this.id = id;
        this.nome = nome;
        this.codigoCategoria = codigoCategoria;
    }

    public static SubCategoria criarSubCategoria(Long id, String nome, Categoria codigoCategoria){
        return new SubCategoria(id, nome, codigoCategoria);
    }

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

    public Categoria getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Categoria codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }
}
