package com.bandtec.hyperxpress.hyperxpressproject.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {

List<SubCategoria> findByNome(String subcategoria);
}
