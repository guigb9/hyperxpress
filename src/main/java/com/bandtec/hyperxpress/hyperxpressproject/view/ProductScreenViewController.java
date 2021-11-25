package com.bandtec.hyperxpress.hyperxpressproject.view;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.ProdutoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Produto;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.ProductScreenDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/views/product-screen")
public class ProductScreenViewController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoBusinessModel productService;

    @GetMapping
    public List<ProductScreenDTO> get() {
        List<Produto> produtos = productService.getProdutosAtivosNoDTO();
        return produtos
                .stream()
                .map(p -> toProductScreenDTO(p, p.getCodigoUsuarioProd()))
                .collect(Collectors.toList());
    }

    public ProductScreenDTO toProductScreenDTO(Produto produto, Usuario usuario) {
        ProductScreenDTO productScreenDTOunconplete = modelMapper.map(produto, ProductScreenDTO.class);
        ProductScreenDTO productScreenDTOunconplete2 = modelMapper.map(usuario, ProductScreenDTO.class);
        ProductScreenDTO productScreenDTO = productScreenDTOunconplete;
        productScreenDTO.setNome(productScreenDTOunconplete2.getNome());
        productScreenDTO.setId(productScreenDTOunconplete2.getId());
        return productScreenDTO;
    }
}