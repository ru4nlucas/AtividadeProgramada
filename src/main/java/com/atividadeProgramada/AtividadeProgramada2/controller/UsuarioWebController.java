package com.atividadeProgramada.AtividadeProgramada2.controller;

import com.atividadeProgramada.AtividadeProgramada2.entity.Role;
import com.atividadeProgramada.AtividadeProgramada2.entity.Usuario;
import com.atividadeProgramada.AtividadeProgramada2.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioWebController {
    private final UsuarioService usuarioService;

    @GetMapping //funçao para listar usuarios
    public  String listar(HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuariologado");
        //testa se o usuario que esta logado ou se nao foi logado é adm, para poder
        //listar usuarios cadastrados
        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home.html";
        }
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariologado", logado);
        return "usuarios/listar";
    }

    //cria novo usuario
    @GetMapping("/novo")
    public String formularioCriar(HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuariologado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home.html";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuariologado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home.html";
        }
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/editar/{id}")
    public String formularoiEditar(@PathVariable String id, HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuariologado");

        if(logado == null || logado.getRole() !=Role.ADMIN){
            return "redirect:/home.html";
        }
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        model.addAttribute("usuario",usuario);
        return "usuarios/formulario";

    }

    @PostMapping
    public String atuallizar(@ModelAttribute Usuario usuario, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuariologado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home.html";
        }
        usuarioService.atualizar(usuario);
        return "redirect:/usuarios";

    }
}
