package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.CadastroUsuarioDTO;

import java.util.List;
public interface EnderecoInterface {
    public List<Endereco> procurarEnderecoPeloCodigoUsuario(Long idUsuario);
    public void criarEndereco(CadastroUsuarioDTO cadastro, Usuario usuario);
    public List<Endereco> enderecos();
    public List<Endereco> enderecoNumeroCep(String cep, String numero);
}
