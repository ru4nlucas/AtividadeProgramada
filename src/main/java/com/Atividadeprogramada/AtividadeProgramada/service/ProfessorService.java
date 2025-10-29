package com.Atividadeprogramada.AtividadeProgramada.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Atividadeprogramada.AtividadeProgramada.entity.Professor;
import com.Atividadeprogramada.AtividadeProgramada.repository.ProfessorRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    //listar professor
    public List<Professor> listarProfessor() {
        return professorRepository.findAll();
    }
    //buscar professor pelo id
    public Optional<Professor> buscarIdProfessor(int id) {
        return professorRepository.findById(id);
    }

    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);

    }

    public void excluirProfessor(int id) {
        professorRepository.deleteById(id);
    }

    public Professor atualizarProfessor(Professor professor) {
        return professorRepository.findById(professor.getId())
                .map(p -> {
                    p.setNome(professor.getNome());
                    p.setEmail(professor.getEmail());
                    p.setTelefone(professor.getTelefone());
                    return professorRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));


    }

    public Optional<Professor> buscarProfessorNome(String nome) {
        return professorRepository.findByNome(nome);

    }
}
