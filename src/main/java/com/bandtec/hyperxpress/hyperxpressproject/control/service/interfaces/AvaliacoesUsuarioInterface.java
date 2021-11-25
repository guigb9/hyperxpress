package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.AvaliacoesUsuario;
import java.util.List;
public interface AvaliacoesUsuarioInterface {
    public void salvarAvaliacao(AvaliacoesUsuario avaliacao);
    public List avaliacoesPorUsuario(Long idUsuario);
}
