package com.bandtec.hyperxpress.hyperxpressproject.businessmodel;

import com.bandtec.hyperxpress.hyperxpressproject.businessmodel.interfaces.UsuariosInterface;
import com.bandtec.hyperxpress.hyperxpressproject.model.ImagemUsuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.repository.UsuarioRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.CadastroUsuarioDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.UsuarioLoginDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioBusinessModel implements UsuariosInterface {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnderecoBusinessModel enderecoService;

    @Autowired
    private ImagensBusinessModel imagensBusinessModel;

    @Autowired
    private ModelMapper modelMapper;



    public Optional<Usuario> usuariosEspecifico(long id){
        return repository.findById(id);
    }

    public Usuario criarUsuario(CadastroUsuarioDTO cadastro){
        Usuario usuario = new Usuario();
        usuario = setInformacoesUsuario(cadastro, usuario);
        repository.save(usuario);
        return repository.findById(usuario.getId()).orElse(null);
    }

    public Usuario setInformacoesUsuario(CadastroUsuarioDTO cadastro, Usuario usuario){
        usuario.setNome(cadastro.getNome());
        usuario.setAvatar(cadastro.getAvatar());
        usuario.setCpf(cadastro.getCpf());
        usuario.setEmail(cadastro.getEmail());
        usuario.setSenha(cadastro.getSenha());
        usuario.setDataNasc(cadastro.getDataNasc());
        usuario.setEmailConfirmado(false);
        return usuario;
    }


    public List<Usuario> buscarUsuarioPorEmailESenha(String email, String senha){
        return repository.findByEmailAndSenha(email, senha);
    }

    public void salvarAlteracoesUsuario(Usuario user){
        repository.save(user);
    }

    public UsuarioLoginDTO toUsuarioLoginDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioLoginDTO.class);
    }

    @Override
    public void cadastrarimagemUsuario(long idUsuario, Optional<MultipartFile> file) throws IOException {

        Usuario usuario = usuariosEspecifico(idUsuario).get();

        byte[] imagemByte = imagensBusinessModel.pegarImagem(file.get());


        ImagemUsuario imgUser  = imagensBusinessModel.setarInfoUsuarioImagens(usuario, file.get(), imagemByte);

        imagensBusinessModel.salvarImagem(imgUser);

    }

    @Override
    public Boolean salvarUsuario(CadastroUsuarioDTO cadastro) {
        List<Usuario> usuarioExist = repository.findByEmail(cadastro.getEmail());
        if(usuarioExist.isEmpty()){
            Usuario usuario = criarUsuario(cadastro);
            enderecoService.criarEndereco(cadastro, usuario);
            return true;
        }
        return false;
    }


    @Override
    public UsuarioLoginDTO login(String login, String senha) {
        List<Usuario> user = buscarUsuarioPorEmailESenha(login, senha);
        if(user.isEmpty()){
            return null;
        }
        return toUsuarioLoginDTO(user.get(0));
    }

    @Override
    public byte[]  pegarImagemUsuario(long id) {

        List<ImagemUsuario> imgUsuario = imagensBusinessModel.imagemUsuarioAssociado(id);
        byte[] imagemUsuario;
        if (imgUsuario.isEmpty()) {
            imagemUsuario = new byte[0];
            return imagemUsuario;
        }
        imagemUsuario = imgUsuario.get(0).getConteudoImagem();
        return imagemUsuario;
    }

    public boolean verificarSeEmailExiste(String email){
        List<Usuario> usuario = repository.findByEmail(email);
        if(usuario.isEmpty()){
            return false;
        }
        return true;
    }

    public Usuario procurarUsuarioPeloId(Long idUsuario){
        return repository.findById(idUsuario).orElse(null);
    }

    public List<Usuario> procurarUsuarioPeloEmail(String email){
        return repository.findByEmail(email);
    }

    public List<Usuario> pegarTodosUsuarios(){
        return repository.findAll();
    }

}
