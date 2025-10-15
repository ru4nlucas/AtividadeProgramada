package com.Atividadeprogramada.AtividadeProgramada.controller;

import com.Atividadeprogramada.AtividadeProgramada.entity.Professor;
import com.Atividadeprogramada.AtividadeProgramada.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping
    public List<Professor> ListarProfessor() {
        return professorService.listarProfessor();
    }



}
