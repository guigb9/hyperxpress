package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.model.FormaPagamento;
import com.bandtec.hyperxpress.hyperxpressproject.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoBusinessModel {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento pegarFormaPagamento(Integer idFormaPagamento){
        return formaPagamentoRepository.findById(idFormaPagamento).orElse(null);
    }
}
