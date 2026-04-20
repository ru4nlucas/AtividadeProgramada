package com.atividadeProgramada.AtividadeProgramada2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/teste")
    public String teste() {
        return "redirect:/teste.html";
    }
}
