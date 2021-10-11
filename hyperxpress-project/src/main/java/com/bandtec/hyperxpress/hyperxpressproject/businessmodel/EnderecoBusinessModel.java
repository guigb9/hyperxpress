package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces.EnderecoInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.repository.EnderecoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.CadastroUsuarioDTO;
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
