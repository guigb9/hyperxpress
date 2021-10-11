package com.bandtec.hyperxpress.hyperxpressproject.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.ImagemUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemUsuarioRepository extends JpaRepository<ImagemUsuario,Long> {

    List<ImagemUsuario> findByUsuarioAssociadoId(Long idProduto);
}
