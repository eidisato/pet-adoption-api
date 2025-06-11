package com.petadoption.pet_adoption_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacaoAdocao(String email, String name, String nomeAdotante) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Confirmação de Solicitação de Adoção");
        mensagem.setText("Olá " + nomeAdotante + ",\n\n" +
                "Recebemos sua solicitação para adoção do pet \"" + name + "\".\n" +
                "Entraremos em contato em breve para dar continuidade ao processo.\n\n" +
                "Atenciosamente,\nEquipe Adote um Amigo! 🐾");
        mensagem.setFrom("viniciuseidisato@gmail.com");

        mailSender.send(mensagem);
    }
}