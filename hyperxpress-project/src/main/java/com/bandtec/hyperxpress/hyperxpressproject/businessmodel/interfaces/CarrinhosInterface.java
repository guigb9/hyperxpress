package com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.Carrinho;
import com.bandtec.hyperxpress.hyperxpressproject.model.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.ProdutoGeralDTO;
import java.util.List;

public interface CarrinhosInterface {
    public List<Carrinho> produtosCarrinhoUsuarioEspecifico(Long idUsuario);
    public boolean verificarSeEstaNoCarrinho(Long idProduto, Long idUsuario);
    public void adicionarAoCarrinho(Produto produto, Usuario usuario);
    public List<ProdutoGeralDTO> pegarProdutosCarrinho(List<Carrinho> produtos);
    public Carrinho pegarProduto(Long idProduto, Long idUsuario);
    public void exclusaoDoProduto(Carrinho produto);
    public void setarInfoCarrinho(Long idProduto);
    public void salvarCarrinho(Carrinho c);
    public void excluirCarrinho(Long idCarrinho);
}
