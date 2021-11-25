package com.bandtec.hyperxpress.hyperxpressproject.view;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.impl.ProdutoServiceImpl;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.FiltroProdutosDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProdutoGeralDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/filtros")
@RequiredArgsConstructor
public class FiltroProdutosView {
	private final ProdutoServiceImpl produtoServiceImpl;

	@GetMapping("/pesquisa-filtros")
	public ResponseEntity<List<ProdutoGeralDTO>> getProdutosMarcaTecido(@RequestParam(required = false) String nomeProduto,
	                                                                    @RequestParam(required = false) String marca,
	                                                                    @RequestParam(required = false) String tecido,
	                                                                    @RequestParam(required = false) String genero,
	                                                                    @RequestParam(required = false) Long subCategoria,
	                                                                    @RequestParam(required = false) Integer categoria,
	                                                                    @RequestParam(required = false) Double precoMinimo,
	                                                                    @RequestParam(required = false) Double precoMax,
	                                                                    @RequestParam(required = false) String tamanhoProduto,
	                                                                    @RequestParam(required = false) Boolean trocavel,
	                                                                    Pageable pageable) {

		FiltroProdutosDTO filtroProdutosDTO = new FiltroProdutosDTO(nomeProduto,
				marca,
				tecido,
				genero,
				subCategoria,
				categoria,
				precoMinimo,
				precoMax,
				tamanhoProduto,
				trocavel,
				pageable);

		Page<Produto> produtos = produtoServiceImpl.filtroProdutos(filtroProdutosDTO);

		if (produtos.getContent().isEmpty()) {
			return status(204).build();
		}
		return status(OK).
				body(produtos
						.getContent()
						.stream()
						.map(p -> produtoServiceImpl.toProdutoGeralDTO(p))
						.collect(Collectors.toList()));
	}
}
