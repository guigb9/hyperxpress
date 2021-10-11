package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.FavoritosBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.view.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosBusinessModel favoritosBusinessModel;

    @PostMapping
    public ResponseEntity postFavorito(@RequestParam String email, @PathVariable Long idProduto){
         if(favoritosBusinessModel.salvarFavorito(email, idProduto)) return status(201).build();
        return status(404).body("Usuário ou produto inexistente");
    }

    @GetMapping
    public ResponseEntity getFavoritosUsuario(@RequestParam String email){
        List<ProdutoGeralDTO> favoritosUsuario = favoritosBusinessModel.favoritosPorUsuario(email);
        if(favoritosUsuario.isEmpty()){
            return status(204).build();
        }
        return status(200).body(favoritosUsuario);
    }



}
