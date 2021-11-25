package com.bandtec.hyperxpress.hyperxpressproject.control.service;

import com.bandtec.hyperxpress.hyperxpressproject.control.request.CadastroUsuarioRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.request.ImagemRequest;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.UsuarioLoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UsuarioService {
	UsuarioLoginDTO toUsuarioLoginDTO(Usuario u);

	Usuario procurarUsuarioPeloId(Long idUsuario);

	Page<Usuario> pegarTodosUsuarios(Pageable pageable);

	void salvarUsuario(CadastroUsuarioRequest cadastro);

	UsuarioLoginDTO login(String email, String senha);

	byte[] pegarImagemUsuario(Long id);

	void verificarSeEmailExiste(String email);

	void cadastrarImagemUsuario(Long idUsuario, ImagemRequest imagem) throws IOException;

	void atualizarInformacaoUsuario(Long idUsuario, CadastroUsuarioRequest usuario);
}
