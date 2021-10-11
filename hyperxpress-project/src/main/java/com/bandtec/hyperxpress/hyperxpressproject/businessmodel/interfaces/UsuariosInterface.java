package com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.CadastroUsuarioDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.UsuarioLoginDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UsuariosInterface {
    public void cadastrarimagemUsuario(long idUsuario, Optional<MultipartFile> file) throws IOException;
    public Boolean salvarUsuario(CadastroUsuarioDTO user);
    public UsuarioLoginDTO login(String login, String senha);
    public byte[]  pegarImagemUsuario(long id);
    public Usuario procurarUsuarioPeloId(Long idUsuario);
}
