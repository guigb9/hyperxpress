package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Pedido;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.PedidoReturn;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.PedidoDTO;

import java.util.List;

public interface PedidosInterface {
    public List pedidoPorUsuario(Long idUsuario);
    public Pedido verificarPedidoPresente(Long idPedido);
    public List<Pedido> todosPedidos();
    public PedidoDTO criarPedido(Long idUsuario);
    public PedidoReturn pagarPedido(String cep, Long codigoPedido);
}
