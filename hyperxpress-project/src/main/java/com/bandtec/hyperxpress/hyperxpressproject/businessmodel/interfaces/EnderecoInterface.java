package com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.CadastroUsuarioDTO;

import java.util.List;
public interface EnderecoInterface {
    public List<Endereco> procurarEnderecoPeloCodigoUsuario(Long idUsuario);
    public void criarEndereco(CadastroUsuarioDTO cadastro, Usuario usuario);
    public List<Endereco> enderecos();
    public List<Endereco> enderecoNumeroCep(String cep, String numero);
}
