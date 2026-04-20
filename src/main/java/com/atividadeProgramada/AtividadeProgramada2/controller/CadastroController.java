package com.atividadeProgramada.AtividadeProgramada2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atividadeProgramada.AtividadeProgramada2.service.UsuarioService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CadastroController {

    private final UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String paginaCadastro() {
        return "cadastro"; // aponta para templates/cadastro.html
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@RequestParam String email,
                                    @RequestParam String senha,
                                    Model model) {
        // Verifica se o email já está cadastrado
        if (usuarioService.buscarPorEmail(email).isPresent()) {
            model.addAttribute("erro", "email cadastrado");
            return "cadastro";
        }

        // Cria e salva o novo usuário
        usuarioService.salvarUsuario(email, senha);
        return "redirect:/login";
    }
}
