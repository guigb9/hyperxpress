package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.model.Categoria;
import com.bandtec.hyperxpress.hyperxpressproject.model.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.SubCategoria;
import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CarrinhoControllerTest {

    @Autowired
    CarrinhoController carrinhoController;

    private SubCategoria subCategoria;

    private Categoria categoria;

    private Usuario usuario;

    Usuario criarUsuario(){
        return Usuario.criarUsuario(1L,
                "Dario Lima",
                "dlima99","758.331.640-52",
                "sergio@hotmail.com",
                "darioLima112", LocalDate.parse("1997-08-09"),
                "",
                true);
    }

    Categoria criarCategoria(){
        return Categoria.criarCategoria(1, "Blusas");
    }

    SubCategoria criarSubCategoria(){
        return SubCategoria.criarSubCategoria(1L, "Moletom", categoria);
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



}