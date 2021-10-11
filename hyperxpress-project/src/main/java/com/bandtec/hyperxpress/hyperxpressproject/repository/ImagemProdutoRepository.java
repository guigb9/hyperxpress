package com.bandtec.hyperxpress.hyperxpressproject.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
    List<ImagemProduto> findByProdutoAssociadoId(Long idProduto);

    int countByProdutoAssociadoId(Long idProduto);
}
