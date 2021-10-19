package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;



public interface MappingToProdutoGeralDTO {
    public ProdutoGeralDTO toProdutoGeralDTO(Produto produto);
}
