package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.model.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.repository.ImagemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagensProdutoBusinessModel {

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;


    public void salvarImagens(ImagemProduto imagemProduto){
        imagemProdutoRepository.save(imagemProduto);
    }

    public int quantidadeImagensProduto(long id){
        return imagemProdutoRepository.countByProdutoAssociadoId(id);
    }
}
