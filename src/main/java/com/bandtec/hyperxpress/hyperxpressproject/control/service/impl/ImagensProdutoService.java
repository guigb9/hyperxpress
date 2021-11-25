package com.bandtec.hyperxpress.hyperxpressproject.control.service.impl;

import com.bandtec.hyperxpress.hyperxpressproject.configuration.exception.ProdutoException;
import com.bandtec.hyperxpress.hyperxpressproject.control.request.ImagemRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.ProdutoService;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.ImagemProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImagensProdutoService {

	private final ImagemProdutoRepository imagemProdutoRepository;
	private final ProdutoService produtoServiceImpl;
	private final ImagensService imagensService;

	public ImagensProdutoService(ImagemProdutoRepository imagemProdutoRepository, ProdutoService produtoServiceImpl, ImagensService imagensService) {
		this.imagemProdutoRepository = imagemProdutoRepository;
		this.produtoServiceImpl = produtoServiceImpl;
		this.imagensService = imagensService;
	}


	public void salvarImagens(ImagemProduto imagemProduto) {
		imagemProdutoRepository.save(imagemProduto);
	}

	public void anexarImagensAoProduto(Long idProduto, ImagemRequest imagem) throws IOException {
		produtoServiceImpl.procurarProdutoPeloid(idProduto);
		if (quantidadeImagensProduto(idProduto) >= 4) {
			throw new ProdutoException("O limite de imagens do produto ja foi ultrapassado (4).");
		}
		salvarImagensAoProduto(imagem, idProduto);
	}

	public Integer quantidadeImagensProduto(Long idProduto) {
		return imagemProdutoRepository.countByProdutoAssociadoIdProduto(idProduto);
	}

	public void salvarImagemProduto(Produto produto, ImagemRequest imagem) {
		ImagemProduto imagemProduto = new ImagemProduto();
		imagemProduto.setProdutoAssociado(produto);
		imagemProduto.setImagem(imagem.getImage());
		imagemProduto.setDataInsercao(LocalDateTime.now());
		salvarImagens(imagemProduto);
	}

	@Transactional
	public void salvarImagensAoProduto(ImagemRequest imagem, Long id) throws IOException {
		Produto produto = produtoServiceImpl.procurarProdutoPeloid(id);
		produtoServiceImpl.salvarProduto(produtoServiceImpl.setarStatusProduto(produto));
		salvarImagemProduto(produto, imagem);
	}
}
