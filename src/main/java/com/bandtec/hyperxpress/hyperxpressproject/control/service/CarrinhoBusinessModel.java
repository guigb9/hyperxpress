package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces.CarrinhosInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Carrinho;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.CarrinhoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoBusinessModel implements CarrinhosInterface{

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoBusinessModel produtoBusinessModel;

    public void setarInfoCarrinho(Long idProduto){
        List<Carrinho> produtosCarrinho = pegarItensCarrinho(idProduto);
        if(!produtosCarrinho.isEmpty()) {
            for (Carrinho c : produtosCarrinho) {
                c.setProdutoAssociado(null);
                carrinhoRepository.save(c);
                carrinhoRepository.deleteById(c.getId());
            }
        }
    }

    public boolean verificarSeEstaNoCarrinho(Long idProduto, Long idUsuario){
        return carrinhoRepository.findByProdutoAssociadoIdProdutoAndUsuarioAdicionouId(idProduto, idUsuario).isEmpty();
    }

    public void adicionarAoCarrinho(Produto produto, Usuario usuario){
        Carrinho c = new Carrinho();
        c.setProdutoAssociado(produto);
        c.setUsuarioAdicionou(usuario);
        carrinhoRepository.save(c);
    }

    public List<Carrinho> pegarItensCarrinho(long idProduto){
        return carrinhoRepository.findByProdutoAssociadoIdProduto(idProduto);
    }

    public void salvarCarrinho(Carrinho c){
        carrinhoRepository.save(c);
    }

    public void excluirCarrinho(Long idCarrinho){
        carrinhoRepository.deleteById(idCarrinho);
    }


    public List<ProdutoGeralDTO> pegarProdutosCarrinho(List<Carrinho> produtos){
        List<ProdutoGeralDTO> listaProdutos = new ArrayList<>();
        produtos.forEach(p -> listaProdutos.add(produtoBusinessModel.toProdutoGeralDTO(p.getProdutoAssociado())));
        return listaProdutos;
    }

    public List<Carrinho> produtosCarrinhoUsuarioEspecifico(Long idUsuario){
        return carrinhoRepository.findByUsuarioAdicionouId(idUsuario);
    }

    public Carrinho pegarProduto(Long idProduto, Long idUsuario){
        return carrinhoRepository.findByProdutoAssociadoIdProdutoAndUsuarioAdicionouId(idProduto, idUsuario).get(0);
    }

    public void desvincularProdutoDoCarrinho(Carrinho produto){
        produto.setProdutoAssociado(null);
        produto.setUsuarioAdicionou(null);
        carrinhoRepository.save(produto);
    }

    public void exclusaoDoProduto(Carrinho produto){
        desvincularProdutoDoCarrinho(produto);
        carrinhoRepository.deleteById(produto.getId());
    }
}
