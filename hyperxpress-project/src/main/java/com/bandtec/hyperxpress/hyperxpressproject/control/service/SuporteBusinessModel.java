package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Suporte;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.SuporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuporteBusinessModel {
    @Autowired
    private SuporteRepository repository;

    public List obterChamados(){
        List<Suporte> chamado = repository.findAll();
        return chamado;
    }

    public Optional<Suporte> obterChamadosPorId(Integer id){
        Optional<Suporte> chamado = repository.findById(id);
        return chamado;
    }

    public void salvarChamado(Suporte chamado){
        repository.save(chamado);
    }

    public Boolean deletarChamado(Integer id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
