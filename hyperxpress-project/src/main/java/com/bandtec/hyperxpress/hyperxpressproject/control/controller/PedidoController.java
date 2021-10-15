package com.bandtec.hyperxpress.hyperxpressproject.control.controller;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.*;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.*;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.PedidoReturn;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.PedidoDTO;
import com.bandtec.hyperxpress.hyperxpressproject.configuration.FreteConfig;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/pedidos")
public class PedidoController extends PedidoBusinessModel {

    @Autowired
    private ProdutoBusinessModel produtoService;

    @Autowired
    private CarrinhoBusinessModel carrinhoService;

    @Autowired
    private UsuarioBusinessModel usuarioService;

    @Autowired
    private EnderecoBusinessModel enderecoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<PedidoDTO>> getPedidosUsuario(@PathVariable Long idUsuario) {
        List<PedidoDTO> pedidosUsuario = pedidoPorUsuario(idUsuario);
        if (pedidosUsuario.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(pedidosUsuario);
    }

    @GetMapping("/pedido/info/{codigoPedido}")
    public ResponseEntity<PedidoDTO> pegarPedido(@PathVariable Long codigoPedido) {
        Pedido pedido = verificarPedidoPresente(codigoPedido);
        if (pedido != null) {
            return status(200).body(toPedidoDTO(pedido));
        }
        return status(204).build();
    }

    @GetMapping("/pendente/{idPedido}")
    public String statusPedidoPendente(@PathVariable Long idPedido){
        try {
            boolean resultado = alterarStatusPedidoEmAndamento(idPedido);

            if (resultado) {
                return "redirect:http://www.hyperxpress.com.br/follow-up";
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/sucesso/{idPedido}")
    public String statusPedidoPago(@PathVariable Long idPedido, RedirectAttributes redirectAttributes){
        try {
            boolean resultado = alterarStatusPedidoPago(idPedido);
            if (resultado) {
                return "redirect:http://www.hyperxpress.com.br/follow-up";
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/falha/{idPedido}")
    public String statusPedidoFalha(@PathVariable Long idPedido){
        try {
            boolean resultado = alterarStatusPedidoFalha(idPedido);
            if (resultado) {
                return "redirect:http://www.hyperxpress.com.br/follow-up";
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/pedido/{codigoPedido}")
    public ResponseEntity<List<ProdutoGeralDTO>> getProdutosPedido(@PathVariable Long codigoPedido) {
        try {
            List<Produto> produtosNoPedido = produtoService.produtosNoPedido(codigoPedido);

            if (produtosNoPedido.isEmpty()) {
                return status(204).build();
            }
            return status(200).body(produtosNoPedido
                    .stream()
                    .map(p -> produtoService.toProdutoGeralDTO(p))
                    .collect(Collectors.toList()));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> getPedidos() {
        List<Pedido> pedidos = todosPedidos();
        if (pedidos.isEmpty()) {
            return status(404).build();
        }
        return status(200).body(pedidos);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Object> postPedido(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.procurarUsuarioPeloId(idUsuario);
        if(usuario != null){
            List<Carrinho> produtosNoCarrinho = carrinhoService.produtosCarrinhoUsuarioEspecifico(idUsuario);
            if (produtosNoCarrinho.isEmpty()) return status(400)
                    .body("Não há produtos no carrinho do usuário, não é possível criar o pedido");

            return status(201).body(criarPedido(idUsuario));
        }
        return status(204).body("Usuário inexistente");
    }

    @GetMapping("/pagar/{codigoPedido}")
    public ResponseEntity<Object> pagamento(@RequestParam String cep, @PathVariable Long codigoPedido) {
        PedidoReturn retornoPedido = pagarPedido(cep, codigoPedido);
        if (retornoPedido != null) {
            return status(201).body(retornoPedido);
        } else {
            return status(400).build();
        }
    }

    @PostMapping("/frete/{cep}/{idProduto}")
    public ResponseEntity<Object> returnFrete(@PathVariable String cep, @PathVariable long idProduto) {
        Produto produto = produtoService.verificarProdutoPresente(idProduto);
        if (produto != null) {
            List<Endereco> enderecos = enderecoService.procurarEnderecoPeloCodigoUsuario(produto
                    .getCodigoUsuarioProd()
                    .getId());


            FreteConfig frete = new FreteConfig(enderecos
                    .get(0)
                    .getCEP()
                    .replace("-", ""), cep);


            return ResponseEntity.status(200).body(Double
                                    .parseDouble(frete
                                    .getValorFrete()
                                    .replace(",", ".")));
        }
        return ResponseEntity.status(204).build();
    }
}