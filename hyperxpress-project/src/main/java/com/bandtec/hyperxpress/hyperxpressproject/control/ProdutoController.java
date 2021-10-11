package com.bandtec.hyperxpress.hyperxpressproject.control;
import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.*;
import com.bandtec.hyperxpress.hyperxpressproject.model.*;
import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.ProdutoDestaque;
import com.bandtec.hyperxpress.hyperxpressproject.view.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoBusinessModel produtoBusinessModel;

    @Autowired
    private ImagensBusinessModel imagensBusinessModel;

    @Autowired
    private UsuarioBusinessModel usuarioBusinessModel;

    @Autowired
    private SubCategoriaBusinessModel subCategoriaBusinessModel;

    @Autowired
    private ImagensProdutoBusinessModel imagensProdutoBusinessModel;


    @GetMapping
    public ResponseEntity<List<ProdutoGeralDTO>> getProdutos(){
        try {
            List<ProdutoGeralDTO> produtos = produtoBusinessModel.getProdutosAtivos();
            if (produtos.isEmpty()) {
                return status(204).build();
            }
            return status(200).body(produtos);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }


    @GetMapping("/produto/{id}")
    public ResponseEntity getProduto(@PathVariable Long id){
        Produto produto = produtoBusinessModel.pesquisarUnicoProduto(id);
        if(produto != null){
            return status(200).body(produtoBusinessModel.toProdutoGeralDTO(produto));
        }else{
            return status(204).build();
        }
    }

    @PostMapping("/imagem/{idProduto}")
    public ResponseEntity anexarImagensAoProduto(@RequestParam MultipartFile file,
                                                 @PathVariable long idProduto) throws IOException {
        Produto produto = produtoBusinessModel.pesquisarUnicoProduto(idProduto);

        if (produto != null){
            boolean resultadoAnexoImagem = produtoBusinessModel.anexarImagensAoProduto(file,idProduto);
            if (!resultadoAnexoImagem){
                return status(204).build();
            }

            if (imagensProdutoBusinessModel.quantidadeImagensProduto(idProduto) > 4) {
                return status(400).body("Produto já possui 4 imagens.");
            }
            return status(201).build();
        }
        return status(404).body("Produto não encontrado");
    }

    @PostMapping
    public ResponseEntity postProduto(@Valid @RequestBody Produto produto) {
        produto.setNivelDestaque(1);
        Usuario usuario = usuarioBusinessModel.procurarUsuarioPeloId(produto.getCodigoUsuarioProd().getId());
        if(usuario != null && subCategoriaBusinessModel.verificarSubcategoriaExiste(produto.getSubCategoria().getId())){
            produtoBusinessModel.salvarProduto(produto);
            return status(201).body(produto.getId());
        }
        return status(204).body("Usuário ou subCategoria não existem");
    }

    @PutMapping("/destacar/{idProduto}")
    public ResponseEntity destacarProduto(@RequestBody ProdutoDestaque produtoDestaque,
                                          @PathVariable Long idProduto){
        Produto produto = produtoBusinessModel.verificarProdutoPresente(idProduto);
        if(produto != null){
            produtoBusinessModel.destacarProduto(produto, produtoDestaque);
            return status(200).build();
        }
        return status(204).body("Produto não existe!");
    }

    @DeleteMapping("/excluir/{idProduto}")
    public ResponseEntity removeProduto(@PathVariable Long idProduto, @RequestParam String email){

        String removeuProduto = produtoBusinessModel.removeProduto(idProduto,email);
        if (removeuProduto.equals("removeu")){
            return status(200).build();
        }
        else{
            if (removeuProduto.equals("nao-e-possível-excluir-um-produto-associado-a-um-pedido")){
                return status(400).body(removeuProduto);
            }
            else if (removeuProduto.equals("nao-e-possível-excluir-produtos-de-outro-usuario")){
                return status(401).body(removeuProduto);
            }
            else {
                return status(204).body(removeuProduto);
            }
        }
    }

    @GetMapping("/imagem/{id}/{imagemEspecifica}")
    public ResponseEntity getProdutoImagem(@PathVariable Long id, @PathVariable int imagemEspecifica) {
        ImagemProduto imagemOptional = produtoBusinessModel.pegarImagem(id,imagemEspecifica);

        if (imagemOptional != null) {

            byte[] imagem =imagemOptional.getImagem();

            return status(200).header("content-type", "image/jpeg").body(imagem);

        }
        return status(204).build();

    }

    @DeleteMapping("/imagem/{id}")
    public ResponseEntity deleteImgById(@PathVariable Long id){
        imagensBusinessModel.removerImagemPeloId(id);
        return status(200).build();
    }
}
