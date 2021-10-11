package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.ProdutoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.view.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private ProdutoBusinessModel produtoBusinessModel;

    @GetMapping("/inativos/{idUsuario}")
    public ResponseEntity getProdutosInativos(@PathVariable Long idUsuario){
        List<ProdutoGeralDTO> produtosInativos = produtoBusinessModel.pegarProdutosInativos(idUsuario);
        if(produtosInativos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(produtosInativos);
    }

    @GetMapping("/ativos/{idUsuario}")
    public ResponseEntity getProdutosVendedor(@PathVariable Long idUsuario){
        List<ProdutoGeralDTO> produtos = produtoBusinessModel.pegarProdutosAtivosUsuario(idUsuario);
        if(produtos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(produtos);
    }

    @GetMapping("/em-andamento/{idUsuario}")
    public ResponseEntity getProdutosVendedorVendidos(@PathVariable Long idUsuario){
        List<ProdutoGeralDTO> produtos = produtoBusinessModel.pegarProdutosPedidoEmAndamento(idUsuario);
        if(produtos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(produtos);
    }

    @GetMapping("/vendidos/{idUsuario}")
    public ResponseEntity getProdutosVendedorConcluidos(@PathVariable Long idUsuario){
        List<ProdutoGeralDTO> produtos = produtoBusinessModel.pegarProdutosVendidos(idUsuario);
        if(produtos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(produtos);
    }

    @PutMapping("/vender/{idProduto}")
    public ResponseEntity venderProduto(@PathVariable Long idProduto){
        boolean resultadoMudancaStatus = produtoBusinessModel.venderProduto(idProduto);
        if (resultadoMudancaStatus) {
            return status(200).build();
        }
        return status(400).build();
    }

    @PutMapping("/tornarAtivo/{idProduto}")
    public ResponseEntity tornarProdutoAtivo(@PathVariable Long idProduto){
        boolean resultadoMudancaStatus = produtoBusinessModel.tornarAtivo(idProduto);
        if (resultadoMudancaStatus) {
            return status(200).build();
        }
        return status(400).build();
    }

    @PutMapping("/tornarInativo/{idProduto}")
    public ResponseEntity tornarProdutoInativo(@PathVariable Long idProduto){
        boolean resultadoMudancaStatus = produtoBusinessModel.tornarInativo(idProduto);
        if (resultadoMudancaStatus) {
            return status(200).build();
        }
        return status(400).build();
    }

    @PutMapping("/em-andamento/{idProduto}")
    public ResponseEntity emAndamento(@PathVariable Long idProduto){
        boolean resultadoMudancaStatus = produtoBusinessModel.colocarVendaEmAndamento(idProduto);
        if (resultadoMudancaStatus) {
            return status(200).build();
        }
        return status(400).build();
    }
}
