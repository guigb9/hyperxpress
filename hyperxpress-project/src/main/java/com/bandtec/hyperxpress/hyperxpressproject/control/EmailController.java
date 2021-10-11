package com.bandtec.hyperxpress.hyperxpressproject.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/enviar")
    public ResponseEntity enviar(@RequestParam String email){
        Integer codigo = ThreadLocalRandom.current().nextInt(100000, 999999);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(String.format("Olá, Bem vindo a HyperXpress \n Ficamos felizes com sua chegada, e esperamos " +
                        "lhe ajudar no que for preciso!\n Seu código de verificação: %d \n Qualquer duvida, é só entrar em contato conosco, tmj! <3", codigo));
        message.setSubject("HyperXpress");
        message.setTo(email);
        message.setFrom("help@hyperxpress.com.br");

        try {
            mailSender.send(message);
            return ResponseEntity.status(200).body(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Erro ao enviar email");

        }

    }
}
