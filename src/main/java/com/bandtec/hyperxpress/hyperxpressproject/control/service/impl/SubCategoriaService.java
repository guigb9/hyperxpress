package com.bandtec.hyperxpress.hyperxpressproject.control.service.impl;

import com.bandtec.hyperxpress.hyperxpressproject.configuration.exception.EmptyException;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.SubCategoria;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoriaService {
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	public boolean verificarSubcategoriaExiste(Long idSubCategoria) {
		return subCategoriaRepository.existsById(idSubCategoria);
	}

	public SubCategoria procurarSubcategoriaPeloId(Long idSubCategoria) {
		return subCategoriaRepository.findById(idSubCategoria).orElseThrow(() -> new EmptyException("SubCategoria"));
	}
}
