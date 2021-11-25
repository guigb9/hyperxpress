package com.bandtec.hyperxpress.hyperxpressproject.model.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.AvaliacoesUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AvaliacoesUsuarioRepository extends PagingAndSortingRepository<AvaliacoesUsuario, Long> {


	List<AvaliacoesUsuario> findByUsuarioAvaliadoId(Long idUsuario);

	@Transactional
	void deleteAvaliacoesUsuarioByIdAndUsuarioAvaliadorId(Long idAvaliacao, Long idUsuario);

	@Query(value = "select AVG(Estrelas) from avaliacoes_usuario where usuario_avaliado_id = ?1", nativeQuery = true)
	Double calcMediaEstrelas(Usuario usuario);
}
