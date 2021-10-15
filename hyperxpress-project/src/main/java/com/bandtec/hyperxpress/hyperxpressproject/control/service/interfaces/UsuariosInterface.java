package com.bandtec.hyperxpress.hyperxpressproject.control.service.interfaces;

import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.CadastroUsuarioDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.UsuarioLoginDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UsuariosInterface {
    public void cadastrarimagemUsuario(long idUsuario, Optional<MultipartFile> file) throws IOException;
    public Boolean salvarUsuario(CadastroUsuarioDTO user);
    public UsuarioLoginDTO login(String login, String senha);
    public byte[]  pegarImagemUsuario(long id);
    public Usuario procurarUsuarioPeloId(Long idUsuario);
}
