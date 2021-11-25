package com.bandtec.hyperxpress.hyperxpressproject.control.service.impl;

import com.bandtec.hyperxpress.hyperxpressproject.control.request.ImagemRequest;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.ImagemProdutoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.ImagemUsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ImagensService {


	private ImagemProdutoRepository repositoryImagemProduto;
	private ImagemUsuarioRepository imagemUsuarioRepository;

	public ImagensService(ImagemProdutoRepository repositoryImagemProduto, ImagemUsuarioRepository imagemUsuarioRepository) {
		this.repositoryImagemProduto = repositoryImagemProduto;
		this.imagemUsuarioRepository = imagemUsuarioRepository;
	}


	public ImagemUsuario setarInfoUsuarioImagens(Usuario usuario, ImagemRequest imagem) {
		ImagemUsuario imagemUsuario = new ImagemUsuario();
		imagemUsuario.setUsuarioAssociado(usuario);
		imagemUsuario.setNomeImagem("profile");
		imagemUsuario.setConteudoImagem(imagem.getImage());
		return imagemUsuario;
	}

	public ImagemProduto setarInfoImagemProduto(Produto produto, ImagemRequest imagem) {

		ImagemProduto imagemProduto = new ImagemProduto();
		imagemProduto.setProdutoAssociado(produto);
		imagemProduto.setImagem(imagem.getImage());
		imagemProduto.setDataInsercao(LocalDateTime.now());
		return imagemProduto;
	}

	public List<ImagemProduto> postarImagensProduto(List<ImagemProduto> imagens, int qtdImagens) {

		List<ImagemProduto> imagensProduto = new ArrayList<>();
		for (int i = 0; i < qtdImagens; i++) {
			imagensProduto.add(imagens.get(i));
		}
		return imagensProduto;
	}

	public void atribuirImagensProduto(List<ImagemProduto> imagens, Produto p) {
		List<ImagemProduto> imagensProduto = postarImagensProduto(imagens, imagens.size());
		Produto produto = p;
		if (produto != null) {
			imagensProduto.forEach(imagem -> {
				imagem.setProdutoAssociado(produto);
				repositoryImagemProduto.save(imagem);
				repositoryImagemProduto.deleteById(imagem.getId());
			});
		}
	}

	public List<ImagemProduto> pegarImagensProdutoAssociado(long idProduto) {
		return repositoryImagemProduto.findByProdutoAssociadoIdProduto(idProduto);
	}

	public void salvarImagem(ImagemUsuario imagemProduto) {
		imagemUsuarioRepository.save(imagemProduto);
	}


	public void deletarImagemPeloId(long id) {
		repositoryImagemProduto.deleteById(id);
	}

	public int quantidadeImagensAssociadaAoProduto(long idProduto) {
		return repositoryImagemProduto.countByProdutoAssociadoIdProduto(idProduto);
	}

	public Optional<ImagemUsuario> imagemUsuarioAssociado(long id) {
		return imagemUsuarioRepository.findByUsuarioAssociadoId(id);
	}

	public void removerTodasImagensDoProduto(Long idProduto) {
		List<ImagemProduto> imagem1 = repositoryImagemProduto.findByProdutoAssociadoIdProduto(idProduto);
		imagem1.forEach(i -> {
			setandoprodutoAssociadoComoNulo(i);
			repositoryImagemProduto.deleteById(i.getId());
		});
	}

	public void setandoprodutoAssociadoComoNulo(ImagemProduto imagem) {
		imagem.setProdutoAssociado(null);
		repositoryImagemProduto.save(imagem);
	}

	public void removerImagemPeloId(Long idImagem) {
		repositoryImagemProduto.findById(idImagem).ifPresent(i -> {
			setandoprodutoAssociadoComoNulo(i);
			repositoryImagemProduto.deleteById(idImagem);
		});
	}

	public boolean contarSePossuiQuatroImagens(Long idProduto) {
		return repositoryImagemProduto.countByProdutoAssociadoIdProduto(idProduto) >= 4;
	}

}
