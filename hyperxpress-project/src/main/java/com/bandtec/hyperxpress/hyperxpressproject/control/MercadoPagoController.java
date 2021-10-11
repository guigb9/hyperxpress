package com.bandtec.hyperxpress.hyperxpressproject.control;


import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.MercadoPagoService;
import com.bandtec.hyperxpress.hyperxpressproject.model.Pedido;
import com.bandtec.hyperxpress.hyperxpressproject.model.PedidoMp;
import com.bandtec.hyperxpress.hyperxpressproject.view.PedidoDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.RetornoMpDTO;
import com.mercadopago.resources.Preference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mercado-pago")
public class MercadoPagoController {
    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/pagar-pedido")
    public ResponseEntity pagarPedido(@RequestBody PedidoDTO pedido){
        try {
            mercadoPagoService.setarTokenDeAcesso();
            Preference preference = mercadoPagoService.criarPreferencia(pedido);
            return ResponseEntity.status(201).body(new RetornoMpDTO(preference));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }
}
