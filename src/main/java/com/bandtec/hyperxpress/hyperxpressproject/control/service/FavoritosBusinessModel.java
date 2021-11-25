package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces.FavoritosInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.FavoritosUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.FavoritosUsuarioRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritosBusinessModel implements FavoritosInterface {

    @Autowired
    private FavoritosUsuarioRepository repositoryFavoritos;

    UsuarioBusinessModel usuarioService = new UsuarioBusinessModel();

    ProdutoBusinessModel produtoService = new ProdutoBusinessModel();

    ImagensBusinessModel imagemProdutoService = new ImagensBusinessModel();

    public boolean salvarFavorito(String email, Long idProduto) {
        Produto produto = produtoService.pesquisarUnicoProduto(idProduto);
        List<Usuario> usuario = usuarioService.procurarUsuarioPeloEmail(email);

        if (produto != null && !usuario.isEmpty()) {
            FavoritosUsuario favorito = new FavoritosUsuario();
            favorito.setProdutoFavorito(produto);
            favorito.setUsuarioFavoritou(usuario.get(0));
            repositoryFavoritos.save(favorito);
            return true;
        }
        return false;
    }

    public List<ProdutoGeralDTO> favoritosPorUsuario(String email){
        List<Produto> favoritos = produtoService.findFavoritos(email);
        return favoritos.stream().map(p -> produtoService.toProdutoGeralDTO(p)).collect(Collectors.toList());
    }
}
