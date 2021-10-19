package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.ProdutoDestaque;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProdutosInterface {
    public Produto pesquisarUnicoProduto(Long idProduto);
    public List produtosNoPedido(Long codigoPedido);
    public Produto verificarProdutoPresente(Long idProduto);
    public List produtosAtivos();
    public Produto pesquisarUnicoProduto(long id);
    public Boolean anexarImagensAoProduto(MultipartFile file, long id) throws IOException;
    public void salvarProduto(Produto p);
    public void destacarProduto(ProdutoDestaque p);
    public String removeProduto(Long idProduto,String email);
    public ImagemProduto pegarImagem(long id, int imagemEspecifica);
    public void deletarImagemPeloId(Long id);
}
