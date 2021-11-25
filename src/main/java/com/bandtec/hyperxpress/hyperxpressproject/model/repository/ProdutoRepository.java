package com.bandtec.hyperxpress.hyperxpressproject.model.repository;

import com.bandtec.hyperxpress.hyperxpressproject.control.enums.StatusPedido;
import com.bandtec.hyperxpress.hyperxpressproject.control.enums.StatusProduto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findByStatusProdutoAndTrocavel(String statusProduto, Boolean trocavel);

	List<Produto> findByCodigoPedidoId(Long idPedido);

	List<Produto> findByUsuarioProdutoIdAndCodigoPedidoStatus(Long idUsuario, StatusPedido statusPedido);

	List<Produto> findByStatusProdutoAndUsuarioProdutoId(StatusProduto statusProduto, Long idUsuario);

	@Query("SELECT p FROM Produto p INNER JOIN SubCategoria s ON s.id = p.subCategoria.id INNER JOIN Categoria c " +
			"ON c.id = s.codigoCategoria.id WHERE (:nomeProduto is null or p.nomeProduto like %:nomeProduto%) and " +
			"(:marca is null or p.marca = :marca) and (:tecido is null"
			+ " or p.tecido = :tecido) and (:genero is null or p.genero = :genero)" +
			" and (:subCategoria is null or s.id = :subCategoria) and " +
			"(:categoria is null or c.id = :categoria) and " +
			"(:precoMinimo is null or p.precoProduto >= :precoMinimo) " +
			"and (:precoMax is null or p.precoProduto <= :precoMax) and " +
			"(:tamanhoProduto is null or p.tamanhoProduto = :tamanhoProduto) and (:trocavel is null or p.trocavel = :trocavel)")
	Page<Produto> pesquisaComParametrosOpcionais(@Param("nomeProduto") Optional<String> nomeProduto, @Param("marca") Optional<String> marca,
	                                             @Param("tecido") Optional<String> tecido, @Param("subCategoria") Optional<Long> subCategoria,
	                                             @Param("precoMinimo") Optional<Double> precoMinimo, @Param("precoMax") Optional<Double> precoMax,
	                                             @Param("categoria") Optional<Integer> categoria, @Param("genero") Optional<String> genero,
	                                             @Param("tamanhoProduto") Optional<String> tamanhoProduto,
	                                             @Param("trocavel") Optional<Boolean> trocavel, Pageable pageable);

	@Query("SELECT p FROM Produto p INNER JOIN SubCategoria s ON s.id = p.subCategoria.id INNER JOIN Categoria c " +
			"ON c.id = s.codigoCategoria.id WHERE (:nomeProduto is null or p.nomeProduto like %:nomeProduto%) or " +
			"(:marca is null or p.marca = :marca) or (:tecido is null"
			+ " or p.tecido = :tecido) or (:genero is null or p.genero = :genero)" +
			" or (:subCategoria is null or s.nome = :subCategoria) or " +
			"(:categoria is null or c.nomeCategoria = :categoria) or " +
			"(:precoMinimo is null or p.precoProduto >= :precoMinimo) " +
			"or (:precoMax is null or p.precoProduto <= :precoMax) or " +
			"(:tamanhoProduto is null or p.tamanhoProduto = :tamanhoProduto) or (:trocavel is null or p.trocavel = :trocavel)")
	List<Produto> pesquisaComParametrosOpcionaisTelaPesquisa(@Param("nomeProduto") Optional<String> nomeProduto, @Param("marca") Optional<String> marca,
	                                                         @Param("tecido") Optional<String> tecido, @Param("subCategoria") Optional<String> subCategoria,
	                                                         @Param("precoMinimo") Optional<Double> precoMinimo, @Param("precoMax") Optional<Double> precoMax,
	                                                         @Param("categoria") Optional<String> categoria, @Param("genero") Optional<String> genero,
	                                                         @Param("tamanhoProduto") Optional<String> tamanhoProduto,
	                                                         @Param("trocavel") Optional<Boolean> trocavel);

	List<Produto> findByUsuarioProduto_IdAndStatusProduto(Long idUsuario, StatusProduto statusProduto);


	Integer countByUsuarioProduto_IdAndStatusProduto(Long idUsuario, StatusProduto vendido);
}