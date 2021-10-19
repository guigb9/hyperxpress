package com.bandtec.hyperxpress.hyperxpressproject.model.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    List<Carrinho> findByUsuarioAdicionouId(Long id);
    List<Carrinho> findByProdutoAssociadoIdProdutoAndUsuarioAdicionouId(Long id, Long idUsuario);
    List<Carrinho> findByProdutoAssociadoIdProduto(Long idProduto);
    @Modifying
    void deleteByProdutoAssociadoIdProdutoAndUsuarioAdicionouId(Long id, Long idUsuario);
}
