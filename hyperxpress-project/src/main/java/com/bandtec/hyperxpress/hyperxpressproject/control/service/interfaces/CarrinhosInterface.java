package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Carrinho;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
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
