package com.bandtec.hyperxpress.hyperxpressproject.model.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.AvaliacoesUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacoesUsuarioRepository extends JpaRepository<AvaliacoesUsuario, Long> {


    List<AvaliacoesUsuario> findByUsuarioAvaliadoId(Long idUsuario);

    @Query(value = "select AVG(Estrelas) from avaliacoes_usuario where usuario_avaliado_id = ?1", nativeQuery = true)
    Double calcMediaEstrelas(Usuario usuario);
}
