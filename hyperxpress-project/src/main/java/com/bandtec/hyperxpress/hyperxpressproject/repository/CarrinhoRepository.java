package com.bandtec.hyperxpress.hyperxpressproject.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    List<Carrinho> findByUsuarioAdicionouId(Long id);
    List<Carrinho> findByProdutoAssociadoIdAndUsuarioAdicionouId(Long id, Long idUsuario);
    List<Carrinho> findByProdutoAssociadoId(Long idProduto);

    @Modifying
    void deleteByProdutoAssociadoIdAndUsuarioAdicionouId(Long id, Long idUsuario);
}
