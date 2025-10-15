package com.Atividadeprogramada.AtividadeProgramada.repository;

import com.Atividadeprogramada.AtividadeProgramada.entity.Professor;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByNome(String nome);
}
