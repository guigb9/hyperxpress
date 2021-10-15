package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.ImagemProdutoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.ImagemUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ImagensBusinessModel {

    @Autowired
    private ImagemProdutoRepository repositoryImagemProduto;

    @Autowired
    private ImagemUsuarioRepository imagemUsuarioRepository;

    @Autowired
    private ProdutoBusinessModel produtoBusinessModel;


    public byte[] pegarImagem(MultipartFile file)throws IOException {
        return file.getBytes();
    }

    public ImagemUsuario setarInfoUsuarioImagens(Usuario usuario, MultipartFile file,
                                        byte[] imagemByte){
        ImagemUsuario imagemUsuario = new ImagemUsuario();
        imagemUsuario.setUsuarioAssociado(usuario);
        imagemUsuario.setNomeImagem(file.getOriginalFilename());
        imagemUsuario.setConteudoImagem(imagemByte);
        return imagemUsuario;
    }


    public Produto setarStatusProduto(Produto produto){
        produto.setStatusProduto("Ativo");
        return produto;
    }

    public ImagemProduto setarInfoImagemProduto(Produto produto, byte[] imagemByte){

        ImagemProduto imagemProduto = new ImagemProduto();
        imagemProduto.setProdutoAssociado(produto);
        imagemProduto.setImagem(imagemByte);
        imagemProduto.setDataInsercao(LocalDateTime.now());
        return imagemProduto;
    }

    public byte[] pegarSomenteAImagem(MultipartFile file) throws IOException {
        return file.getBytes();
    }



    public List<ImagemProduto> postarImagensProduto(List<ImagemProduto> imagens, int qtdImagens){

        List<ImagemProduto> imagensProduto = new ArrayList<>();
        for (int i=0; i<qtdImagens; i++){
            imagensProduto.add(imagens.get(i));
        }
        return imagensProduto;
    }

    public void atribuirImagensProduto(List<ImagemProduto> imagens, Produto p){
        List<ImagemProduto> imagensProduto = postarImagensProduto(imagens, imagens.size());
        Produto produto = p;
        if(produto != null) {
            imagensProduto.forEach(imagem -> {
                imagem.setProdutoAssociado(produto);
                repositoryImagemProduto.save(imagem);
                repositoryImagemProduto.deleteById(imagem.getId());
            });
        }
    }

    public List<ImagemProduto> pegarImagensProdutoAssociado(long idProduto){
        return repositoryImagemProduto.findByProdutoAssociadoId(idProduto);
    }

    public void salvarImagem(ImagemUsuario imagemProduto){
        imagemUsuarioRepository.save(imagemProduto);
    }


    public void deletarImagemPeloId(long id){
        repositoryImagemProduto.deleteById(id);
    }

    public int quantidadeImagensAssociadaAoProduto(long idProduto){
        return repositoryImagemProduto.countByProdutoAssociadoId(idProduto);
    }

    public List<ImagemUsuario> imagemUsuarioAssociado(long id){
        return imagemUsuarioRepository.findByUsuarioAssociadoId(id);
    }

    public void removerTodasImagensDoProduto(Long idProduto){
        List<ImagemProduto> imagem1 = repositoryImagemProduto.findByProdutoAssociadoId(idProduto);
        imagem1.forEach(i -> {
            setandoprodutoAssociadoComoNulo(i);
            repositoryImagemProduto.deleteById(i.getId());
        });
    }

    public void setandoprodutoAssociadoComoNulo(ImagemProduto imagem){
        imagem.setProdutoAssociado(null);
        repositoryImagemProduto.save(imagem);
    }

    public void removerImagemPeloId(Long idImagem){
        repositoryImagemProduto.findById(idImagem).ifPresent(i ->{
            setandoprodutoAssociadoComoNulo(i);
            repositoryImagemProduto.deleteById(idImagem);
        });
    }

    public boolean contarSePossuiQuatroImagens(Long idProduto){
        return repositoryImagemProduto.countByProdutoAssociadoId(idProduto) >= 4;
    }

}
