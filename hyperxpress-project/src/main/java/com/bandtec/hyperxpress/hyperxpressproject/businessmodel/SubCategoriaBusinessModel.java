package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoriaBusinessModel {
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    public boolean verificarSubcategoriaExiste(Long idSubCategoria){
        return subCategoriaRepository.existsById(idSubCategoria);
    }
}
