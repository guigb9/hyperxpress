package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.model.AvaliacoesUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.AvaliacoesUsuarioBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.view.AvaliacaoUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvalicoesUsuarioController {
    @Autowired
    private AvaliacoesUsuarioBusinessModel avaliacoesUsuarioBusinessModel;

    @PostMapping
    public ResponseEntity postAvaliacao(@Valid @RequestBody AvaliacoesUsuario avaliacao){
        try {
            avaliacoesUsuarioBusinessModel.salvarAvaliacao(avaliacao);
            return status(201).build();
        }catch (Exception e){
            System.out.println(e);
            return status(400).build();
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<AvaliacaoUsuarioDTO>> getAvaliacoesPorUsuario(@Valid @PathVariable Long idUsuario){
        try {
            List<AvaliacaoUsuarioDTO> avaliacoesUsuario = avaliacoesUsuarioBusinessModel.avaliacoesPorUsuario(idUsuario);
            if (avaliacoesUsuario.isEmpty()) {
                return status(204).build();
            }
            return status(200).body(avaliacoesUsuario);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }

    @DeleteMapping("/{idAvaliacao}")
    public ResponseEntity removerAvaliacao(@PathVariable Long idAvaliacao){
        try {
            boolean resultadoRemocao = avaliacoesUsuarioBusinessModel.removerAvaliacao(idAvaliacao);
            if (resultadoRemocao) {
                return status(200).build();
            }
            return status(204).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }
}
