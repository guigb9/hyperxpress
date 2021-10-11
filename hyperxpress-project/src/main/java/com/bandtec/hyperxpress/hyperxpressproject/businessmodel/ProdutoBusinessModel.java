package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces.ProdutosInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.repository.ProdutoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.responses.adapter.ProdutoDestaque;
import com.bandtec.hyperxpress.hyperxpressproject.view.ProdutoGeralDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoBusinessModel implements ProdutosInterface {
    private static final String STATUS_ATIVO = "ativo";
    private static final String STATUS_INATIVO = "inativo";
    private static final String STATUS_PEDIDO_EM_ANDAMENTO = "pedido-em-andamento";
    private static final String STATUS_VENDIDO = "vendido";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImagensBusinessModel imagensBusinessModel;

    @Autowired
    private ImagensProdutoBusinessModel imagensProdutoBusinessModel;

    @Autowired
    private CarrinhoBusinessModel carrinhoBusinessModel;

    public List<ProdutoGeralDTO> getProdutosAtivos() {
        return produtoRepository.findByStatusProduto(STATUS_ATIVO)
                .stream()
                .map(this::toProdutoGeralDTO)
                .collect(Collectors.toList());
    }

    public ProdutoGeralDTO toProdutoGeralDTO(Produto produto){
        return modelMapper.map(produto, ProdutoGeralDTO.class);
    }

    public Produto pesquisarUnicoProduto(Long idProduto) {
        return produtoRepository.findById(idProduto).orElse(null);
    }

    public void setarInfoProdutoParaExclusao(Produto p) {
        p.setCodigoUsuarioProd(null);
        p.setCodigoPedido(null);
        p.setSubCategoria(null);
    }

    public Optional<Produto> pegarProdutoEspecifico(long idProduto) {
        return produtoRepository.findById(idProduto);
    }

    public void deletarProdutoPeloId(long id) {
        produtoRepository.deleteById(id);
    }

    public void destacarProduto(Produto p, ProdutoDestaque produtoDestaque) {
        p.setNivelDestaque(produtoDestaque.getNivelDestaque());
        produtoRepository.save(p);
    }

    public List<Produto> findFavoritos(String email) {
        return produtoRepository.findByProdutoFavoritoEmail(email);
    }

    public List<ImagemProduto> primeiraImagem(long id) {
        return imagensBusinessModel.pegarImagensProdutoAssociado(id);
    }

    public List<Produto> produtosNoPedido(Long codigoPedido) {
        return produtoRepository.findByCodigoPedidoId(codigoPedido);
    }

    public Produto verificarProdutoPresente(Long idProduto) {
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        return produto.orElse(null);
    }

    public List<ProdutoGeralDTO> filtroProdutos(String nomeProduto,
                                                String marca,
                                                String tecido,
                                                String genero,
                                                Long subCategoria,
                                                Integer categoria,
                                                Double precoMinimo,
                                                Double precoMax,
                                                String tamanhoProduto,
                                                Boolean trocavel) {

        List<Optional> parametrosDePesquisa = transformarEmOptional(nomeProduto,
                marca,
                tecido,
                genero,
                subCategoria,
                categoria,
                precoMinimo,
                precoMax,
                tamanhoProduto,
                trocavel);

        List<Produto> produtos = produtoRepository.pesquisaComParametrosOpcionais(parametrosDePesquisa.get(0),
                parametrosDePesquisa.get(1),
                parametrosDePesquisa.get(2),
                parametrosDePesquisa.get(3),
                parametrosDePesquisa.get(4),
                parametrosDePesquisa.get(5),
                parametrosDePesquisa.get(6),
                parametrosDePesquisa.get(7),
                parametrosDePesquisa.get(8),
                parametrosDePesquisa.get(9));
        return produtos.stream().map(this::toProdutoGeralDTO).collect(Collectors.toList());
    }

    public List<Optional> transformarEmOptional(String nomeProduto,
                                                String marca,
                                                String tecido,
                                                String genero,
                                                Long subCategoria,
                                                Integer categoria,
                                                Double precoMinimo,
                                                Double precoMax,
                                                String tamanhoProduto,
                                                Boolean trocavel) {

        Optional<String> nomeProdutoParametro = Optional.ofNullable(nomeProduto);
        Optional<String> marcaParametro = Optional.ofNullable(marca);
        Optional<String> tecidoParametro = Optional.ofNullable(tecido);
        Optional<String> generoParametro = Optional.ofNullable(genero);
        Optional<Long> subCategoriaParametro = Optional.ofNullable(subCategoria);
        Optional<Integer> categoriaParametro = Optional.ofNullable(categoria);
        Optional<Double> precoMinimoParametro = Optional.ofNullable(precoMinimo);
        Optional<Double> precoMaxParametro = Optional.ofNullable(precoMax);
        Optional<String> tamanhoProdutoParametro = Optional.ofNullable(tamanhoProduto);
        Optional<Boolean> trocavelParametro = Optional.ofNullable(trocavel);
        return Arrays.asList(nomeProdutoParametro,
                marcaParametro,
                tecidoParametro,
                generoParametro,
                subCategoriaParametro,
                categoriaParametro,
                precoMinimoParametro,
                precoMaxParametro,
                tamanhoProdutoParametro,
                trocavelParametro);
    }

    public List<Produto> produtosParaVenda(){
        return produtoRepository.findByStatusProdutoAndTrocavel(STATUS_ATIVO, false);
    }

    public List<Produto> produtosParaTroca(){
        return produtoRepository.findByStatusProdutoAndTrocavel(STATUS_ATIVO, true);
    }


    public void salvarProduto(Produto p) {
        produtoRepository.save(p);
    }

    public Produto setarStatusProduto(Produto produto){
        produto.setStatusProduto(STATUS_ATIVO);
        return produto;
    }

    public void salvarImagemProduto(Produto produto, byte[] imagemByte){

        ImagemProduto imagemProduto = new ImagemProduto();
        imagemProduto.setProdutoAssociado(produto);
        imagemProduto.setImagem(imagemByte);
        imagemProduto.setDataInsercao(LocalDateTime.now());
        imagensProdutoBusinessModel.salvarImagens(imagemProduto);

    }

    @Override
    public List<ProdutoGeralDTO> produtosAtivos() {
        List<Produto> produtos = produtoRepository.findByStatusProduto(STATUS_ATIVO);
        return produtos.stream().map(this::toProdutoGeralDTO).collect(Collectors.toList());
    }

    @Override
    public Produto pesquisarUnicoProduto(long id) {
        return pegarProdutoEspecifico(id).orElse(null);
    }

    @Override
    public Boolean anexarImagensAoProduto(MultipartFile file, long id) throws IOException {
        Optional<Produto> produto = pegarProdutoEspecifico(id);
        if (produto.isPresent()) {
            salvarProduto(setarStatusProduto(produto.get()));
            produtoRepository.save(setarStatusProduto(produto.get()));
            salvarImagemProduto(produto.get(), imagensBusinessModel.pegarSomenteAImagem(file));
            return true;
        }
        return false;
    }


    @Override
    public void destacarProduto(ProdutoDestaque p) {
        Produto produto = produtoRepository.findById(p.getIdProduto()).orElse(null);
        if(produto != null){
            produto.setNivelDestaque(p.getNivelDestaque());
            produtoRepository.save(produto);
        }
    }

    @Override
    public String removeProduto(Long idProduto, String email) {
        List<ImagemProduto> imagens = imagensBusinessModel.pegarImagensProdutoAssociado(idProduto);
        Optional<Produto> p = pegarProdutoEspecifico(idProduto);
        if(p.isPresent()){
                carrinhoBusinessModel.setarInfoCarrinho(idProduto);
                if(p.get().getCodigoUsuarioProd().getEmail().equals(email)){
                    if(p.get().getCodigoPedido() != null){
                        return "nao-e-possível-excluir-um-produto-associado-a-um-pedido";
                    }
                    setarInfoProdutoParaExclusao(p.get());
                    imagensBusinessModel.atribuirImagensProduto(imagens, null);
                    salvarProduto(p.get());
                    deletarProdutoPeloId(p.get().getId());

                    return "removeu";
                }
                return "nao-e-possível-excluir-produtos-de-outro-usuario";
            }
            return "nao-foi-possivel-achar-imagens-associada-ao-produto";
    }


    @Override
    public ImagemProduto pegarImagem(long id, int imagemEspecifica) {
        List<ImagemProduto> listaImagem = imagensBusinessModel.pegarImagensProdutoAssociado(id);

        if (listaImagem.size() < imagemEspecifica) {
            return null;
        }

        return listaImagem.get(imagemEspecifica-1);
    }

    @Override
    public void deletarImagemPeloId(Long id) {
        imagensBusinessModel.deletarImagemPeloId(id);
    }

    public List<ProdutoGeralDTO> pegarProdutosInativos(Long idUsuario){
        return produtoRepository.
                findByStatusProdutoAndCodigoUsuarioProdId(STATUS_INATIVO, idUsuario)
                .stream()
                .map(this::toProdutoGeralDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoGeralDTO> pegarProdutosAtivosUsuario(Long idUsuario){
        return produtoRepository.
                findByStatusProdutoAndCodigoUsuarioProdId(STATUS_ATIVO, idUsuario)
                .stream()
                .map(this::toProdutoGeralDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoGeralDTO> pegarProdutosPedidoEmAndamento(Long idUsuario){
        return produtoRepository.
                findByStatusProdutoAndCodigoUsuarioProdId(STATUS_PEDIDO_EM_ANDAMENTO, idUsuario)
                .stream()
                .map(this::toProdutoGeralDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoGeralDTO> pegarProdutosVendidos(Long idUsuario){
        return produtoRepository.
                findByStatusProdutoAndCodigoUsuarioProdId(STATUS_VENDIDO, idUsuario)
                .stream()
                .map(this::toProdutoGeralDTO)
                .collect(Collectors.toList());
    }

    public boolean venderProduto(Long idProduto){
        Optional<Produto> produto = pegarProdutoEspecifico(idProduto);
        if(produto.isPresent()){
            produto.get().setStatusProduto(STATUS_VENDIDO);
            salvarProduto(produto.get());
            return true;
        }
        return false;
    }

    public boolean tornarAtivo(Long idProduto){
        Optional<Produto> produto = pegarProdutoEspecifico(idProduto);
        if(produto.isPresent()){
            produto.get().setStatusProduto(STATUS_ATIVO);
            salvarProduto(produto.get());
            return true;
        }
        return false;
    }

    public boolean tornarInativo(Long idProduto){
        Optional<Produto> produto = pegarProdutoEspecifico(idProduto);
        if(produto.isPresent()){
            produto.get().setStatusProduto(STATUS_INATIVO);
            salvarProduto(produto.get());
            return true;
        }
        return false;
    }

    public boolean colocarVendaEmAndamento(Long idProduto){
        Optional<Produto> produto = pegarProdutoEspecifico(idProduto);
        if(produto.isPresent()){
            produto.get().setStatusProduto(STATUS_PEDIDO_EM_ANDAMENTO);
            salvarProduto(produto.get());
            return true;
        }
        return false;
    }


}
