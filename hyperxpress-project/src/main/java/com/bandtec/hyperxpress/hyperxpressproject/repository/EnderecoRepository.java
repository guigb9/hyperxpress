package com.bandtec.hyperxpress.hyperxpressproject.repository;

import com.bandtec.hyperxpress.hyperxpressproject.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByCepAndNumero(String cep, String numero);

   @Query(value = "Select E.* from Pedido P inner join Usuario U on P.USUARIO_ID = U.ID Inner join ENDERECO E on U.id = E.usuario_id where P.id = ?1", nativeQuery = true)
   List<Endereco> IdUsuarioDoPedido(Long id);


    List<Endereco> findByCodigoUsuarioId(Long id);
}
