package com.atividadeProgramada.AtividadeProgramada2.controller;

import com.atividadeProgramada.AtividadeProgramada2.entity.Role;
import com.atividadeProgramada.AtividadeProgramada2.entity.Usuario;
import com.atividadeProgramada.AtividadeProgramada2.service.AtividadeService;
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
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        //testa se o usuario que esta logado é adm, para poder
        //listar usuarios cadastrados
        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home";
        }
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioLogado", logado);
        return "usuarios/listar";
    }

    //cria novo usuario
    @GetMapping("/novo")
    public String formularioCriar(HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home";
        }
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if(logado == null || logado.getRole() !=Role.ADMIN){
            return "redirect:/home";
        }
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        model.addAttribute("usuario",usuario);
        return "usuarios/formulario";

    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Usuario usuario, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home";
        }
        usuarioService.atualizar(usuario);
        return "redirect:/usuarios";

    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable String id, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if(logado == null || logado.getRole() != Role.ADMIN){
            return "redirect:/home";
        }
        usuarioService.excluir(id);
        return "redirect:/usuarios";
    }

}
