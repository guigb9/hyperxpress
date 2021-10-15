package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import java.util.List;

public interface FavoritosInterface {
    public boolean salvarFavorito(String email, Long idProduto);
    public List favoritosPorUsuario(String email);
}
