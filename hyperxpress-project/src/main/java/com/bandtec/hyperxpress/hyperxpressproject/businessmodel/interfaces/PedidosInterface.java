package com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.Pedido;
import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.PedidoReturn;
import com.bandtec.hyperxpress.hyperxpressproject.view.PedidoDTO;

import java.util.List;

public interface PedidosInterface {
    public List pedidoPorUsuario(Long idUsuario);
    public Pedido verificarPedidoPresente(Long idPedido);
    public List<Pedido> todosPedidos();
    public PedidoDTO criarPedido(Long idUsuario);
    public PedidoReturn pagarPedido(String cep, Long codigoPedido);
}
