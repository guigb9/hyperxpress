package com.bandtec.hyperxpress.hyperxpressproject.control.controller;

import com.bandtec.hyperxpress.hyperxpressproject.control.service.EnderecoBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Endereco;
import com.bandtec.hyperxpress.hyperxpressproject.model.entity.Usuario;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.CadastroUsuarioDTO;
import com.bandtec.hyperxpress.hyperxpressproject.view.adapter.Login;
import com.bandtec.hyperxpress.hyperxpressproject.control.service.UsuarioBusinessModel;
import com.bandtec.hyperxpress.hyperxpressproject.view.dto.UsuarioLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioBusinessModel usuarioBusinessModel;

    @Autowired
    private EnderecoBusinessModel enderecoService;

    @GetMapping
    public ResponseEntity getUsuarios(){
        List<Usuario> usuarios = usuarioBusinessModel.pegarTodosUsuarios();
        if(usuarios.isEmpty()){
            return status(204).build();
        }
        return status(200).body(usuarios.stream()
                .map(u -> usuarioBusinessModel.toUsuarioLoginDTO(u))
                .collect(Collectors.toList()));
    }

    @GetMapping("/enderecos")
    public ResponseEntity getEnderecos(){
        List<Endereco> enderecos = enderecoService.enderecos();
        if(enderecos.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(enderecos);
    }

    @GetMapping("/endereco/{cep}/{numero}")
    public ResponseEntity getEnderecoCepNumero(@PathVariable String cep, @PathVariable String numero){
        List<Endereco> enderecos = enderecoService.enderecoNumeroCep(cep, numero);
        if(enderecos.isEmpty()){
            return status(204).build();
        }
        return status(200).body(enderecos);

    }

    @PostMapping("/imagem/{idUsuario}")
    public ResponseEntity postUsuarioImg(@RequestParam(required = false)
                                                     Optional<MultipartFile> file,
                                         @PathVariable Long idUsuario) throws IOException{

        if(file.isPresent()){

            usuarioBusinessModel.cadastrarimagemUsuario(idUsuario, file);

            return ResponseEntity.status(201).build();
        }
             return ResponseEntity.status(204).body("Enviar arquivo");

    }


    @PostMapping
    public ResponseEntity postUsuario(@Valid @RequestBody CadastroUsuarioDTO cadastro){
        try {
            boolean result = usuarioBusinessModel.salvarUsuario(cadastro);
            if (result) {
                return status(201).build();
            }
            return status(400).body("O email já está cadastrado no sistema!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }



    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDTO> login(@Valid @RequestBody Login login){
        try {
            UsuarioLoginDTO user = usuarioBusinessModel.login(login.getEmail(), login.getSenha());
            if (user != null) {
                return ResponseEntity.status(200).body(user);
            }
            return status(204).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return status(400).build();
        }
    }



    @GetMapping("/perfil/{id}")
    public ResponseEntity getImagemUsuario(@PathVariable Long id){

        if(usuarioBusinessModel.pegarImagemUsuario(id).length <= 0){
            return status(204).build();
        }
        return status(200).header("content-type", "image/jpeg").body(usuarioBusinessModel.pegarImagemUsuario(id));

    }

    @GetMapping("/endereco/{idUsuario}")
    public ResponseEntity getEnderecoIdUsuario(@PathVariable Long idUsuario){
        List<Endereco> endereco = enderecoService.procurarEnderecoPeloCodigoUsuario(idUsuario);
        if(endereco.isEmpty()){
            return status(204).build();
        }
        return status(200).body(endereco);
    }

    @GetMapping("/verificar/{email}")
    public ResponseEntity verficarEmailIgual(@PathVariable String email){
        boolean verificacao = usuarioBusinessModel.verificarSeEmailExiste(email);
        if(verificacao){
            return status(200).build();
        }
        return status(400).build();
    }
}
