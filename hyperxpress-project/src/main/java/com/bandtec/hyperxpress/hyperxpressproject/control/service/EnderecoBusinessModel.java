package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces.EnderecoInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.EnderecoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.CadastroUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoBusinessModel implements EnderecoInterface {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> procurarEnderecoPeloCodigoUsuario(Long idUsuario){
        return enderecoRepository.findByCodigoUsuarioId(idUsuario);
    }

    public List<Endereco> enderecos() {
        return enderecoRepository.findAll();
    }

    public List<Endereco> enderecoNumeroCep(String cep, String numero) {
        return enderecoRepository.findByCepAndNumero(cep, numero);
    }

    public void criarEndereco(CadastroUsuarioDTO cadastro, Usuario usuario){
        Endereco endereco = new Endereco();
        endereco = setInformacoesEndereco(cadastro, usuario, endereco);
        enderecoRepository.save(endereco);
    }

    public Endereco setInformacoesEndereco(CadastroUsuarioDTO cadastro, Usuario codigoUsuario, Endereco endereco){
        endereco.setEstadoUf(cadastro.getEstadoUf());
        endereco.setCidade(cadastro.getCidade());
        endereco.setCEP(cadastro.getCep());
        endereco.setBairro(cadastro.getBairro());
        if(cadastro.getComplemento() != null){
            endereco.setComplemento(cadastro.getComplemento())
        ;}
        endereco.setNumero(cadastro.getNumero());
        endereco.setLogradouro(cadastro.getLogradouro());
        endereco.setCodigoUsuario(codigoUsuario);
        return  endereco;
    }
}
