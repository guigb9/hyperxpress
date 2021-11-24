package com.bandtec.hyperxpress.hyperxpressproject.model.repository;


import com.bandtec.hyperxpress.hyperxpressproject.model.entity.FavoritosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritosUsuarioRepository extends JpaRepository<FavoritosUsuario, Long> {

	List<FavoritosUsuario> findByProdutoFavoritoUsuarioProduto(Long idUsuario);

	List<FavoritosUsuario> findByUsuarioFavoritou_IdIs(Long idUsuario);


}
