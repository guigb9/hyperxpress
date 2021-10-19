package com.bandtec.hyperxpress.hyperxpressproject.control.controller;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.ProdutoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.UsuarioBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Carrinho;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.CarrinhoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController{

    @Autowired
    private CarrinhoBusinessModel carrinhoService;

    @Autowired
    private UsuarioBusinessModel serviceUsuario;

    @Autowired
    private ProdutoBusinessModel serviceProduto;

    @PostMapping("/carrinho/adicionar/{idProduto}/{idUsuario}")
    public ResponseEntity adicionarCarrinho(@PathVariable Long idProduto,
                                            @PathVariable Long idUsuario){
        Produto produto = serviceProduto.pesquisarUnicoProduto(idProduto);
        Usuario usuario = serviceUsuario.procurarUsuarioPeloId(idUsuario);

        if (produto != null && usuario != null) {
            if(idUsuario.equals(produto.getCodigoUsuarioProd().getId())){
                return status(400).body("Você não pode comprar o próprio produto!");
            }

            if(carrinhoService.verificarSeEstaNoCarrinho(idProduto, idUsuario)){
                carrinhoService.adicionarAoCarrinho(produto, usuario);
                return status(201).build();
            }
            return status(400).body("Produto já está adicionado ao " +
                    "carrinho do usuário");

        }
        return status(404).body("Usuário ou produto não encontrados");

    }

    @GetMapping(value = "/carrinho/todos/{idUsuario}")
    public ResponseEntity<List<ProdutoGeralDTO>> getProdutosPorUsuario(@PathVariable Long idUsuario){
        List<Carrinho> produtos = carrinhoService.produtosCarrinhoUsuarioEspecifico(idUsuario);
        if(produtos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(carrinhoService.pegarProdutosCarrinho(produtos));
    }

    @DeleteMapping("/carrinho/remover/{idProduto}/{idUsuario}")
    public ResponseEntity deleteProdutoCarrinho(@PathVariable Long idProduto, @PathVariable Long idUsuario){
        try {
            Carrinho produto = carrinhoService.pegarProduto(idProduto, idUsuario);
            carrinhoService.exclusaoDoProduto(produto);
            return status(200).build();
        }catch (Exception ex){
            System.err.println(ex);
            return status(400).body(ex);
        }
    }
}
