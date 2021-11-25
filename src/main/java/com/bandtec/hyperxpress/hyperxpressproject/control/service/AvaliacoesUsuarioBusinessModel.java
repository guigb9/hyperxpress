package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces.AvaliacoesUsuarioInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.AvaliacoesUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.AvaliacoesUsuarioRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.AvaliacaoUsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacoesUsuarioBusinessModel implements AvaliacoesUsuarioInterface {
    @Autowired
    private AvaliacoesUsuarioRepository repositoryAvaliacao;

    @Autowired
    private ModelMapper modelMapper;

    public void salvarAvaliacao(AvaliacoesUsuario avaliacao) {
        repositoryAvaliacao.save(avaliacao);
    }

    public List<AvaliacaoUsuarioDTO> avaliacoesPorUsuario(Long idUsuario) {
        return repositoryAvaliacao.
                findByUsuarioAvaliadoId(idUsuario).
                stream().map(this::toUsuarioDTO).
                collect(Collectors.toList());
    }

    public AvaliacaoUsuarioDTO toUsuarioDTO(AvaliacoesUsuario avaliacoesUsuario){
        return modelMapper.map(avaliacoesUsuario, AvaliacaoUsuarioDTO.class);
    }

    public boolean removerAvaliacao(Long idAvaliacao){
        Optional<AvaliacoesUsuario> avaliacao = repositoryAvaliacao.findById(idAvaliacao);
        if(avaliacao.isPresent()){
            removerChavesEstrangeiras(avaliacao.get());
            salvarAvaliacao(avaliacao.get());
            return true;
        }
        return false;
    }

    public void removerChavesEstrangeiras(AvaliacoesUsuario avaliacao){
        avaliacao.setUsuarioAvaliado(null);
        avaliacao.setUsuarioAvaliador(null);
    }
}
