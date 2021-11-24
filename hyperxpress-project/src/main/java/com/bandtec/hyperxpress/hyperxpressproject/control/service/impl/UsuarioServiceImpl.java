package com.bandtec.hyperxpress.hyperxpressproject.control.service.impl;

import com.bandtec.hyperxpress.hyperxpressproject.configuration.exception.EmptyException;
import com.bandtec.hyperxpress.hyperxpressproject.control.request.CadastroUsuarioRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.request.ImagemRequest;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.EnderecoService;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.UsuarioService;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.ImagemUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.UsuarioRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.UsuarioLoginDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository repository;
	private final EnderecoService enderecoService;
	private final ImagensService imagensService;
	private final ModelMapper modelMapper;

	public UsuarioServiceImpl(UsuarioRepository repository, EnderecoServiceImpl enderecoService, ImagensService imagensService, ModelMapper modelMapper) {
		this.repository = repository;
		this.enderecoService = enderecoService;
		this.imagensService = imagensService;
		this.modelMapper = modelMapper;
	}

	public Optional<Usuario> usuariosEspecifico(long id) {
		return repository.findById(id);
	}

	public Usuario criarUsuario(CadastroUsuarioRequest cadastro) {
		Usuario usuario = new Usuario();
		usuario = setInformacoesUsuario(cadastro, usuario);
		repository.save(usuario);
		return repository.findById(usuario.getId()).orElse(null);
	}

	public Usuario setInformacoesUsuario(CadastroUsuarioRequest cadastro, Usuario usuario) {
		usuario.setNome(cadastro.getNome());
		usuario.setAvatar(cadastro.getAvatar());
		usuario.setCpf(cadastro.getCpf());
		usuario.setEmail(cadastro.getEmail());
		usuario.setSenha(cadastro.getSenha());
		usuario.setDataNasc(cadastro.getDataNasc());
		usuario.setEmailConfirmado(false);
		return usuario;
	}


	public Optional<Usuario> buscarUsuarioPorEmailESenha(String email, String senha) {
		return repository.findByEmailAndSenha(email, senha);
	}

	public UsuarioLoginDTO toUsuarioLoginDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioLoginDTO.class);
	}

	public void cadastrarImagemUsuario(Long idUsuario, ImagemRequest imagem) throws IOException {
		Usuario usuario = usuariosEspecifico(idUsuario).orElseThrow(() -> new EmptyException("Usuário"));
		ImagemUsuario imgUser = imagensService.setarInfoUsuarioImagens(usuario, imagem);
		imagensService.salvarImagem(imgUser);

	}

    @Override
    public void atualizarInformacaoUsuario(Long idUsuario, CadastroUsuarioRequest usuarioNovo) {
		Usuario user = repository.findById(idUsuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario não encontrado"));
		Usuario usuario = setInformacoesUsuario(usuarioNovo, user);
		repository.save(usuario);
		enderecoService.atualizarEnderecoPeloIdUsuario(usuarioNovo, idUsuario);
    }

    public void salvarUsuario(CadastroUsuarioRequest cadastro) {
		repository.findByEmail(cadastro.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario já cadastrado!"));
		Usuario usuario = criarUsuario(cadastro);
		enderecoService.criarEndereco(cadastro, usuario);
	}


	public UsuarioLoginDTO login(String login, String senha) {
		Usuario user = buscarUsuarioPorEmailESenha(login, senha).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou senha incorreto"));
		return toUsuarioLoginDTO(user);
	}

	public byte[] pegarImagemUsuario(Long id) {
		ImagemUsuario imgUsuario = imagensService.imagemUsuarioAssociado(id).orElseThrow(() -> new EmptyException("Imagem usuario"));
		return Base64.getDecoder().decode(imgUsuario.getConteudoImagem());
	}

	public void verificarSeEmailExiste(String email) {
		repository.findByEmail(email).orElseThrow(() -> new EmptyException("Email do usuário"));
	}

	public Usuario procurarUsuarioPeloId(Long idUsuario) {
		return repository.findById(idUsuario).orElseThrow(() -> new EmptyException("Usuário"));
	}

	public Page<Usuario> pegarTodosUsuarios(Pageable pageable) {
		return repository.findAll(pageable);
	}


}
