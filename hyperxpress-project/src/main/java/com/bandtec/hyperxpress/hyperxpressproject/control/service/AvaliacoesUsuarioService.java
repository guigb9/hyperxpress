package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.request.AvaliacaoUsuarioRequest;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.AvaliacoesUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.AvaliacaoUsuarioDTO;

import java.util.List;

public interface AvaliacoesUsuarioService {

	AvaliacoesUsuario setarInformacoes(AvaliacaoUsuarioRequest request);

	AvaliacoesUsuario salvarAvaliacao(AvaliacoesUsuario avaliacao);

	List<AvaliacaoUsuarioDTO> pegarAvaliacoesPorUsuario(Long idUsuario);

	void remocaoDeAvaliacao(Long idAvaliacao);

	Double calcularMediaEstrelas(Usuario usuario);
}
