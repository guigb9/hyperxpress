package com.bandtec.hyperxpress.hyperxpressproject.view.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDetalheVendedorDTO {
	private Long idProduto;

	private String nomeProduto;

	private Double precoProduto;

	private Long idUsuario;

	private String nomeUsuario;
}
