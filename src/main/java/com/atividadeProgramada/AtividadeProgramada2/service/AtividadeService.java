package com.atividadeProgramada.AtividadeProgramada2.service;

import com.atividadeProgramada.AtividadeProgramada2.entity.Usuario;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

import com.atividadeProgramada.AtividadeProgramada2.repository.AtividadeRepository;
import com.atividadeProgramada.AtividadeProgramada2.entity.Atividade;

@Service
@AllArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;

    public List<Atividade> ListarAtividade() {
        return atividadeRepository.findAll();
    }

    //trecho para procurar atividade relacionada a usuario
    // Busca só as atividades do usuário logado (USER)
    public List<Atividade> listarPorUsuario(Usuario usuario) {
        return atividadeRepository.findByUsuario(usuario);
    }
    //trecho para procurar atividade relacionada a usuario
    // Busca uma atividade pelo ID (usado na edição)
    public Atividade buscarPorId(String id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }

    public Atividade salvar(Atividade atividade) {
        Atividade validAtividade = Objects.requireNonNull(atividade, "atividade e obrigatoria");
        return atividadeRepository.save(validAtividade);
    }
    public void deletar(String nome) {
        atividadeRepository.deleteByNome(nome);
    }

    public Atividade atualizar(Atividade atividade) {
        Atividade validAtividade = Objects.requireNonNull(atividade, "atividade e obrigatoria");
        String id = Objects.requireNonNull(validAtividade.getId(), "id e obrigatorio para atualizar");

        return atividadeRepository.findById(id)
                .map(a -> {
                    a.setNome(validAtividade.getNome());
                    a.setData(validAtividade.getData());
                    a.setHora(validAtividade.getHora());
                    a.setDescricao(validAtividade.getDescricao());
                    a.setUsuario(validAtividade.getUsuario()); //para atribuir a atividade ao usuairo
                    return atividadeRepository.save(a);
                })
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }
}