package com.bandtec.hyperxpress.hyperxpressproject.tools.pagamento;

import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.ItemPedido;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

public class Pagamentos {

    public String postItem(ItemPedido p) throws MPException {

        MercadoPago.SDK.setAccessToken("APP_USR-8190375739950764-032518-7e6fa8e5694b6bebc6b633a22b96b529-516885354");

        try{
            // Cria um objeto de preferência
            Preference preference1 = new Preference();
            // RECEBER O ITEM DO FRONT
            // Cria um item na preferência

            Item item = new Item();
            item.setTitle(p.getTituloItem())
                    .setQuantity(p.getQuantidadeItem())
                    .setUnitPrice(p.getPrecoItem().floatValue());
            preference1.appendItem(item);
            preference1.save();

            BackUrls backUrls = new BackUrls(
                    "http://localhost:3000/success",
                    "http://localhost:3000/pending",
                    "http://localhost:3000/failure");


            preference1.setBackUrls(backUrls);
            preference1.setAutoReturn(Preference.AutoReturn.approved);

            return preference1.getInitPoint();
        }catch (Exception e){
            return "Error "+e.getMessage();
        }
    }
}
