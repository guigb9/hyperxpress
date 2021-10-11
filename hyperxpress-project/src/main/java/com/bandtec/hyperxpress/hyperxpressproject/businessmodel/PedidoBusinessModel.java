package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces.PedidosInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.*;
import com.bandtec.hyperxpress.hyperxpressproject.repository.*;
import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.ItemPedido;
import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.PedidoReturn;
import com.bandtec.hyperxpress.hyperxpressproject.view.PedidoDTO;
import com.bandtec.hyperxpress.hyperxpressproject.tools.frete.Frete;
import com.bandtec.hyperxpress.hyperxpressproject.tools.pagamento.Pagamentos;
import com.mercadopago.exceptions.MPException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class PedidoBusinessModel implements PedidosInterface {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoBusinessModel produtoService;

    @Autowired
    private CarrinhoBusinessModel carrinhoService;

    @Autowired
    private UsuarioBusinessModel usuarioService;

    @Autowired
    private EnderecoBusinessModel enderecoService;

    @Autowired
    private FormaPagamentoBusinessModel formaPagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    public List<PedidoDTO> pedidoPorUsuario(Long idUsuario){
        return pedidoRepository.findByCodigoUsuarioId(idUsuario).stream().map(this::toPedidoDTO).collect(Collectors.toList());
    }

    public PedidoDTO toPedidoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public boolean alterarStatusPedidoPago(Long idPedido){
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            pedido.get().setStatus("Pago");
            pedidoRepository.save(pedido.get());
            return true;
        }
        return false;
    }

    public boolean alterarStatusPedidoEmAndamento(Long idPedido){
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            pedido.get().setStatus("Aguardando Pagamento");
            return true;
        }
        return false;
    }

    public boolean alterarStatusPedidoFalha(Long idPedido){
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            pedido.get().setStatus("Falha");
            return true;
        }
        return false;
    }

    public Pedido verificarPedidoPresente(Long idPedido){
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        return pedido.orElse(null);
    }

    public List<Pedido> todosPedidos(){
        return pedidoRepository.findAll();
    }

    public PedidoDTO criarPedido(Long idUsuario){
        Pedido pedido = new Pedido();
        setandoInformacoesPedido(idUsuario, pedido);
        return toPedidoDTO(pedido);
    }

    public void setandoInformacoesPedido(Long idUsuario,Pedido pedido){
        pedido.setStatus("em andamento");
        pedido.setFormaPagamento(formaPagamentoService.pegarFormaPagamento(1));
        pedido.setDataPedido(LocalDateTime.now());
        Optional<Usuario> usuario = usuarioService.usuariosEspecifico(idUsuario);
        pedidoRepository.save(pedido);
        if(usuario.isPresent()){
            pedido.setCodigoUsuario(usuario.get());
            pedido.setValorTotal(passarProdutoDoCarrinhoParaPedido(pedido, idUsuario));
            pedidoRepository.save(pedido);
        }
    }

    public Double passarProdutoDoCarrinhoParaPedido(Pedido pedido, Long idUsuario){
        List<Carrinho> produtosNoCarrinho = carrinhoService.produtosCarrinhoUsuarioEspecifico(idUsuario);
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        produtosNoCarrinho.forEach(c ->{
            Produto produto = c.getProdutoAssociado();
            produto.setCodigoPedido(pedido);
            valorTotal.updateAndGet(v -> v + produto.getPrecoProduto());
            c.setProdutoAssociado(null);
            c.setUsuarioAdicionou(null);
            carrinhoService.salvarCarrinho(c);
            carrinhoService.excluirCarrinho(c.getId());
            produtoService.salvarProduto(produto);
        });
        pedido.setValorTotal(valorTotal.get());
        return valorTotal.get();
    }

    public void somarFretePorProduto(String cep,
                                     List<Produto> listaCarrinho,
                                     AtomicReference<Double> valorFrete,
                                     AtomicReference<Integer> cont){
        listaCarrinho.forEach(p -> {
            Frete frete = new Frete(cep, enderecoService.procurarEnderecoPeloCodigoUsuario(p.
                    getCodigoUsuarioProd().
                    getId()).
                    get(0).
                    getCEP());

            valorFrete.updateAndGet(v -> v + Double.parseDouble(frete
                    .getValorFrete()
                    .replace(",", ".")));

            cont.getAndSet(cont.get() + 1);
        });
    }

    public PedidoReturn setarInformacoesFinais(PedidoReturn returnPedido, Pedido pedido, String initPoint, AtomicReference<Double> valorFrete, Double valorTotal){
        pedido.setValorFrete(valorFrete.get());
        pedidoRepository.save(pedido);
        returnPedido.setPreferenceInitPoint(initPoint);
        returnPedido.setValorFrete(valorFrete.get());
        returnPedido.setValorTotalPedido(valorTotal);
        return returnPedido;
    }


    public PedidoReturn pagarPedido(String cep, Long codigoPedido){

        if(verificarPedidoPresente(codigoPedido) != null){
            Pedido pedido = verificarPedidoPresente(codigoPedido);
            List<Produto> listaCarrinho = produtoService.produtosNoPedido(codigoPedido);
            double valorTotal;
            double somaProduto;

            AtomicReference<Double> valorFrete = new AtomicReference<>(0.0);
            String initPoint;
            AtomicReference<Integer> cont = new AtomicReference<>(0);

            somaProduto = pedido.getValorTotal();

            somarFretePorProduto(cep, listaCarrinho, valorFrete, cont);

            valorTotal = somaProduto + valorFrete.get();

            PedidoReturn returnPedido = new PedidoReturn();
            try {
                ItemPedido itemPedido = new ItemPedido("HyperXpress Produtos",
                        valorTotal,1);

                Pagamentos pagamento = new Pagamentos();
                initPoint = pagamento.postItem(itemPedido);

            }catch (MPException e){
                System.err.println(e.getMessage());
                return null;
            }

            return setarInformacoesFinais(returnPedido, pedido, initPoint, valorFrete, valorTotal);
        }
        return null;
    }
}
