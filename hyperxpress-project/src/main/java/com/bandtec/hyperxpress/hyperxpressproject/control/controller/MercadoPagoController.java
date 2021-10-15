package com.bandtec.hyperxpress.hyperxpressproject.control.controller;


import com.bandtec.hyperxpress.hyperxpressproject.control.service.MercadoPagoService;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.PedidoDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.RetornoMpDTO;
import com.mercadopago.resources.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
