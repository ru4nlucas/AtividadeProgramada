package com.Atividadeprogramada.AtividadeProgramada.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Atividadeprogramada.AtividadeProgramada.entity.Professor;
import com.Atividadeprogramada.AtividadeProgramada.service.ProfessorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/professor")
@AllArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    //mostra todos os professsores
    @GetMapping
    public List<Professor> ListarProfessor() {
        return professorService.listarProfessor();
    }

    //busca o professor pelo id
    @GetMapping("{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable int id) {
        return professorService.buscarIdProfessor(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //Adciona novos prfessores ao bd
    @PostMapping
    public ResponseEntity<Professor> adicionarProfessor(@RequestBody Professor professor) {
        Professor novoProfessor = professorService.salvarProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }
    //deletar professor por id
    @DeleteMapping("{/id}")
    public ResponseEntity<?> deletarProfessor(@PathVariable int id) {
        professorService.excluirProfessor(id);
        return ResponseEntity.ok().build();
    }
    //atualizar os dados, vulgo alterar dados
    @PutMapping
    public ResponseEntity<?> atualizarProfessor(@RequestBody Professor professor) {
        Professor alterarProfessor = professorService.atualizarProfessor(professor);
        return ResponseEntity.ok(alterarProfessor);
    }

    @GetMapping
    public ResponseEntity<?> buscarPorNome(@RequestParam String nome) {
        return professorService.buscarProfessorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }














}
