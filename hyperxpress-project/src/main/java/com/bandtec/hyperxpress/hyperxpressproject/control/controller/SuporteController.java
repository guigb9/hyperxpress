package com.bandtec.hyperxpress.hyperxpressproject.control.controller;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Suporte;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.SuporteBusinessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/report")
public class SuporteController {

    @Autowired
    private SuporteBusinessModel service;

    @GetMapping
    public ResponseEntity getChamados(){
        if(!service.obterChamados().isEmpty()){
            return status(200).body(service.obterChamados());
        }
        return status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getChamadoId(@PathVariable Integer id){
        if(service.obterChamadosPorId(id).isPresent()){
            return status(200).body(service.obterChamadosPorId(id));
        }
        return status(204).build();
    }

    @PostMapping
    public ResponseEntity criarChamado(@RequestBody Suporte chamado){
        service.salvarChamado(chamado);
        return status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarChamado(@PathVariable Integer id){
        if(service.deletarChamado(id)){
            return status(200).build();
        }
        return status(404).build();
    }
}
