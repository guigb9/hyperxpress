package com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.AvaliacoesUsuario;
import java.util.List;
public interface AvaliacoesUsuarioInterface {
    public void salvarAvaliacao(AvaliacoesUsuario avaliacao);
    public List avaliacoesPorUsuario(Long idUsuario);
}
