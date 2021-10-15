package com.bandtec.hyperxpress.hyperxpressproject.model.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemUsuarioRepository extends JpaRepository<ImagemUsuario,Long> {

    List<ImagemUsuario> findByUsuarioAssociadoId(Long idProduto);
}
