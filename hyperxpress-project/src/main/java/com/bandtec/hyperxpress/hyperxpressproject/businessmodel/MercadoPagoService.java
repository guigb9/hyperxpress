package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.view.PedidoDTO;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {
    private final String ACESS_TOKEN = "TEST-8099014547724788-090223-0570f323f435ddcb629ed6f612aa3952-434716941";

    public void setarTokenDeAcesso(){
        try {
            MercadoPago.SDK.setAccessToken(ACESS_TOKEN);
        } catch (MPConfException e) {
            e.printStackTrace();
        }
    }

    public Item criarItem(PedidoDTO pedido){
        Item itemPedido = new Item();
        setarInformacoesItemPedido(itemPedido, pedido);
        return itemPedido;
    }

    public void setarInformacoesItemPedido(Item itemPedido, PedidoDTO pedido){
        itemPedido.setTitle(pedido.getId().toString())
                .setQuantity(1)
                .setUnitPrice(pedido.getValorTotal()
                        .floatValue());
    }

    public Preference criarPreferencia(PedidoDTO pedido){
        Preference preference = new Preference();
        preference.setAutoReturn(Preference.AutoReturn.approved);
        preference.setBackUrls(criarBackUrls(pedido));
        setarInformacoesPreferencia(preference, pedido);
        return  preference;
    }

    public BackUrls criarBackUrls(PedidoDTO pedido){
        BackUrls backUrls = new BackUrls();
        backUrls.setSuccess("http://54.144.215.240/pedidos/sucesso/"+pedido.getId());
        backUrls.setFailure("http://54.144.215.240/pedidos/falha/"+pedido.getId());
        backUrls.setPending("http://54.144.215.240/pedidos/pendente/"+pedido.getId());
        return backUrls;
    }

    public void setarInformacoesPreferencia(Preference preference,PedidoDTO pedido){
        preference.appendItem(criarItem(pedido));
       salvarPreferencia(preference);
    }

    public void salvarPreferencia(Preference preference){
        try {
            preference.save();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
