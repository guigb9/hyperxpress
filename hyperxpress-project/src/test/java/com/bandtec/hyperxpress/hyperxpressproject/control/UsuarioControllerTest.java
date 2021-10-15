package com.bandtec.hyperxpress.hyperxpressproject.control;

import com.bandtec.hyperxpress.hyperxpressproject.control.controller.UsuarioController;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.EnderecoRepository;
import com.bandtec.hyperxpress.hyperxpressproject.model.repository.UsuarioRepository;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.Login;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.CadastroUsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UsuarioControllerTest {
    @Autowired
    UsuarioController usuarioController;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    Usuario usuario;
    Endereco endereco;

    @BeforeEach
    void criarPremissas(){
        usuario = this.criarUsuario();
        endereco = this.criarEndereco();;
    }

    Usuario criarUsuario(){
        return Usuario.criarUsuario(1L,
                "Dario Lima",
                "dlima99","758.331.640-52",
                "sergio@hotmail.com",
                "darioLima112", LocalDate.parse("1997-08-09"),
                "",
                true);
    }

    Endereco criarEndereco(){
        return  Endereco.criarEndereco(1,
                "SP",
                "05093-060",
                "Vila Anastácio",
                "Rua Conselheiro Ribas",
                "151", "São Paulo",
                "Casa Verde",
                usuario);
    }

    CadastroUsuarioDTO criarCadastroUsuarioDTO(){
        return new CadastroUsuarioDTO(usuario.getNome(),
                usuario.getAvatar(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataNasc(),
                usuario.getGoogleId(),
                usuario.getEmailConfirmado(),
                endereco.getEstadoUf(),
                endereco.getCEP(),
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getComplemento());
    }

    @Test
    void listaVaziaUsuarios(){
        ResponseEntity resposta = usuarioController.getUsuarios();
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void verificarSeInsereUsuario(){
        CadastroUsuarioDTO cadastroUsuarioDTO = criarCadastroUsuarioDTO();
        ResponseEntity reposta = usuarioController.postUsuario(cadastroUsuarioDTO);
        assertEquals(201, reposta.getStatusCodeValue());
    }

    @Test
    void listaDeEnderecosVazia(){
        ResponseEntity resposta = usuarioController.getEnderecos();
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void inserirUsuarioRepetido(){
        CadastroUsuarioDTO cadastroUsuarioDTO = criarCadastroUsuarioDTO();
        usuarioController.postUsuario(cadastroUsuarioDTO);
        ResponseEntity resposta = usuarioController.postUsuario(cadastroUsuarioDTO);
        assertEquals(400, resposta.getStatusCodeValue());
    }

    @Test
    void pegarUsuarios(){
        repository.save(usuario);
        ResponseEntity resposta = usuarioController.getUsuarios();
        assertEquals(200, resposta.getStatusCodeValue());
    }



    @Test
    void pegarEnderecos(){
        repository.save(usuario);
        enderecoRepository.save(endereco);
        ResponseEntity resposta = usuarioController.getEnderecos();
        assertEquals(200, resposta.getStatusCodeValue());
    }



    @Test
    void listaEnderecoCepNumero(){
        repository.save(usuario);
        enderecoRepository.save(endereco);
        ResponseEntity resposta = usuarioController.getEnderecoCepNumero(endereco.getCEP(), endereco.getNumero());
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void listaEnderecoCepNumeroVazia(){
        ResponseEntity resposta = usuarioController.getEnderecoCepNumero("09929-819", "1501");
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void loginNormal(){
        usuarioController.postUsuario(criarCadastroUsuarioDTO());
        Login login = new Login();
        login.setEmail("sergio@hotmail.com");
        login.setSenha("darioLima112");
        ResponseEntity reposta = usuarioController.login(login);
        assertEquals(200, reposta.getStatusCodeValue());
    }

    @Test
    void loginComSenhaIncorreta(){
        usuarioController.postUsuario(criarCadastroUsuarioDTO());
        Login login = new Login();
        login.setEmail("sergio@hotmail.com");
        login.setSenha("123123");
        ResponseEntity reposta = usuarioController.login(login);
        assertEquals(204, reposta.getStatusCodeValue());
    }

    @Test
    void pegarEnderecoIdUsuario(){
        repository.save(usuario);
        enderecoRepository.save(endereco);
        ResponseEntity resposta = usuarioController.getEnderecoIdUsuario(usuario.getId());
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void pegarEnderecoUsuarioInexistente(){
        ResponseEntity resposta = usuarioController.getEnderecoIdUsuario(50L);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void verificarEmailExiste(){
        repository.save(usuario);
        ResponseEntity resposta = usuarioController.verficarEmailIgual(usuario.getEmail());
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void verificarEmailInexistente(){
        ResponseEntity resposta = usuarioController.verficarEmailIgual("gabriel@gmail.com");
        assertEquals(400, resposta.getStatusCodeValue());
    }
}