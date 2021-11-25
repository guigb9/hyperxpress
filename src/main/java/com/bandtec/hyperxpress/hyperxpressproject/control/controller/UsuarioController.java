package com.bandtec.hyperxpress.hyperxpressproject.control.controller;

import com.bandtec.hyperxpress.hyperxpressproject.control.request.CadastroUsuarioRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.request.ImagemRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.UsuarioService;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.Login;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.UsuarioLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	private final UsuarioService usuarioBusinessModel;

	@GetMapping(produces = "application/json")
	public Page<Usuario> getUsuarios(@PageableDefault() Pageable pageable) {
		return usuarioBusinessModel.pegarTodosUsuarios(pageable);
	}

	@PostMapping("/imagem/{idUsuario}")
	@ResponseStatus(HttpStatus.CREATED)
	public void postUsuarioImg(@RequestBody(required = false) ImagemRequest imagem, @PathVariable Long idUsuario) throws IOException {
		usuarioBusinessModel.cadastrarImagemUsuario(idUsuario, imagem);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postUsuario(@Valid @RequestBody CadastroUsuarioRequest cadastro) {
		usuarioBusinessModel.salvarUsuario(cadastro);
	}

	@PostMapping("/login")
	public UsuarioLoginDTO login(@Valid @RequestBody Login login) {
		return usuarioBusinessModel.login(login.getEmail(), login.getSenha());
	}

	@GetMapping(value = "/perfil/{id}", produces = {"image/jpeg"})
	public byte[] getImagemUsuario(@PathVariable Long id) {
		return usuarioBusinessModel.pegarImagemUsuario(id);
	}

	@GetMapping(value = "/verificar/{email}", produces = "application/json")
	public void verficarEmailIgual(@PathVariable String email) {
		usuarioBusinessModel.verificarSeEmailExiste(email);
	}

	@PutMapping(value = "/{idUsuario}")
	@ResponseStatus(HttpStatus.OK)
	public void atualizaInformacoesUsuario(@PathVariable Long idUsuario, @RequestBody CadastroUsuarioRequest usuario){
		usuarioBusinessModel.atualizarInformacaoUsuario(idUsuario, usuario);
	}
}
