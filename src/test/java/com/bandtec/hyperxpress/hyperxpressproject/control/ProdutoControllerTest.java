package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.control.controller.ProdutoController;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.*;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProdutoControllerTest {

    @Autowired
    ProdutoController produtoController;

    @Autowired
    ProdutoRepository repository;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SubCategoriaRepository subCategoriaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    Categoria categoria;

    SubCategoria subCategoria;

    Usuario usuario;

    Produto produto;

    @BeforeEach
    void premissasDeTeste(){
        categoria = criarCategoria();
        subCategoria = criarSubCategoria();
        usuario = criarUsuario();
        produto = criarProduto();
        categoriaRepository.save(categoria);
        subCategoriaRepository.save(subCategoria);
        usuarioRepository.save(usuario);
    }

    Pedido criarPedido(){
        return Pedido.criarPedido(1L,
                produto.getPrecoProduto(),
                5.0,
                "em-andamento",
                LocalDateTime.now(),
                null,
                criarFormaPagamento(),
                usuario);
    }

    FormaPagamento criarFormaPagamento(){
        return FormaPagamento.criarFormaPagamento(1, "mercado-pago");
    }

    Categoria criarCategoria(){
        return Categoria.criarCategoria(1, "Blusas");
    }

    SubCategoria criarSubCategoria(){
        return SubCategoria.criarSubCategoria(1L, "Moletom", categoria);
    }

    Usuario criarUsuario(){
        return Usuario.criarUsuario(1L,
                "Dario Lima",
                "dlima99","758.331.640-52",
                "sergio@hotmail.com",
                "darioLima112", LocalDate.parse("1997-08-09"),
                "",
                true);
    }

    Produto criarProduto(){
        return Produto.criarProduto(1L,
                "Moletom Nike",
                "Moletom Nike conservado estou querendo trocar por tênis também da mesma marca.",
                120.0, "GG",
                "ativo", "masculino",
                "(11) 9 9999-1123",
                false,
                1,
                subCategoria,
                usuario,
                null,
                "Algodão",
                "Nike");
    }


    @Test
    void postarProduto(){
        ResponseEntity responseEntity = produtoController.postProduto(produto);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    void removerProduto(){
        produtoController.postProduto(produto);
        ResponseEntity responseEntity = produtoController.removeProduto(1L, "sergio@hotmail.com");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    //samuca corrigir método
    @Test
    void removerProdutoAssociadoAPedido(){
        produtoController.postProduto(produto);
        ResponseEntity responseEntity = produtoController.removeProduto(1L, "sergio@hotmail.com");
        formaPagamentoRepository.save(criarFormaPagamento());
        Pedido pedido = criarPedido();
        pedidoRepository.save(criarPedido());
        produto.setCodigoPedido(pedido);
        repository.save(produto);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}