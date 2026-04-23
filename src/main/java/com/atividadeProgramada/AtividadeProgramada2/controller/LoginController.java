package com.atividadeProgramada.AtividadeProgramada2.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atividadeProgramada.AtividadeProgramada2.entity.Usuario;
import com.atividadeProgramada.AtividadeProgramada2.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping()
@AllArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

    // Abre a página de login no navegador
    @GetMapping("/login")
    public String paginaLogin() {
        return "login"; // aponta para templates/login.html
    }

    // Processa o formulário quando o usuário clica em "Entrar"
    @PostMapping("/login")
    public String processarLogin(@RequestParam String email,
                                 @RequestParam String senha,
                                 HttpSession session,
                                 Model model) {

        Optional<Usuario> resultado = usuarioService.login(email, senha);

        if (resultado.isPresent()) {
            // Login correto: salva o usuário na sessão e redireciona para /home
            session.setAttribute("usuarioLogado", resultado.get());
            return "redirect:/home";
        }

        // Login errado: volta para login.html com mensagem de erro
        model.addAttribute("erro", "Email ou senha inválidos");
        return "login";
    }

    // Abre a página home apenas se o usuário estiver logado na sessão
    @GetMapping("/home")
    public String paginaHome(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            // Sessão vazia = não logado, manda de volta pro login
            return "redirect:/login";
        }

        // Passa o usuário para o html via model
        model.addAttribute("usuarioLogado", usuarioLogado);
        return "home"; // aponta para templates/home.html
    }

    // Destroi a sessão e volta para o login
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
