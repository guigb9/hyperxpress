package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.ProdutoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/filtros")
public class FiltroController {
    @Autowired
    private ProdutoBusinessModel produtoBusinessModel;

    @GetMapping("/vendas")
    public ResponseEntity getProdutosParaVenda(){
        List<Produto> produtos = produtoBusinessModel.produtosParaVenda();
        if(produtos.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(produtos);
    }

    @GetMapping("/trocas")
    public ResponseEntity getProdutosParaTroca() {
        List<Produto> produtos = produtoBusinessModel.produtosParaTroca();
        if(produtos.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(produtos);
    }

    @GetMapping("/pesquisa-filtros")
    public ResponseEntity getProdutosMarcaTecido(@RequestParam(required = false) String nomeProduto,
                                                 @RequestParam(required = false) String marca,
                                                 @RequestParam(required = false) String tecido,
                                                 @RequestParam(required = false) String genero,
                                                 @RequestParam(required = false) Long subCategoria,
                                                 @RequestParam(required = false) Integer categoria,
                                                 @RequestParam(required = false) Double precoMinimo,
                                                 @RequestParam(required = false) Double precoMax,
                                                 @RequestParam(required = false) String tamanhoProduto,
                                                 @RequestParam(required = false) Boolean trocavel){

        List produtos = produtoBusinessModel.filtroProdutos(nomeProduto,
                marca,
                tecido,
                genero,
                subCategoria,
                categoria,
                precoMinimo,
                precoMax,
                tamanhoProduto,
                trocavel);

        if(produtos.isEmpty()){
            return status(204).build();
        }

        return ResponseEntity.ok(produtos);
    }

}
