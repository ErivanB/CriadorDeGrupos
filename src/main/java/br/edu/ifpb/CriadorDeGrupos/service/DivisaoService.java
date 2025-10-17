package br.edu.ifpb.CriadorDeGrupos.service;

import br.edu.ifpb.CriadorDeGrupos.model.Aluno;
import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DivisaoService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private GrupoService grupoService;

    public List<Grupo> dividirTimes(int quantidadeGrupos, List<String> nomesAlunos) {
        // Limpar dados anteriores
        limparDadosAnterios();

        // Validar entrada
        if (quantidadeGrupos <= 0) {
            throw new IllegalArgumentException("Quantidade de grupos deve ser maior que zero");
        }

        if (nomesAlunos == null || nomesAlunos.isEmpty()) {
            throw new IllegalArgumentException("Lista de alunos não pode estar vazia");
        }

        // Criar times
        List<Grupo> grupos = criarGrupos(quantidadeGrupos);

        // Criar candidatos
        List<Aluno> alunos = criarAlunos(nomesAlunos);

        // Embaralhar candidatos
        List<Aluno> alunosEmbaralhados = embaralharLista(alunos);

        // Distribuir candidatos nos times
        distribuirAlunos(alunosEmbaralhados, grupos);

        return grupoService.listarTodos();
    }

    private void limparDadosAnterios() {
        // Primeiro limpar os candidatos (devido à constraint de foreign key)
        alunoService.limparCandidatos();
        // Depois limpar os times
        grupoService.limparTimes();
    }

    private List<Grupo> criarGrupos(int quantidade) {
        List<Grupo> times = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Grupo time = grupoService.criarTime("Time " + i);
            times.add(time);
        }
        return times;
    }


    private List<Aluno> criarAlunos(List<String> nomes) {
        List<Aluno> alunos = new ArrayList<>();
        for (String nome : nomes) {
            Aluno aluno = alunoService.criarCandidato(nome);
            alunos.add(aluno);
        }
        return alunos;
    }

    private <T> List<T> embaralharLista(List<T> lista) {
        List<T> copia = new ArrayList<>(lista);
        Collections.shuffle(copia);
        return copia;
    }

    private void distribuirAlunos(List<Aluno> alunos, List<Grupo> grupos) {
        int indexTime = 0;

        for (Aluno candidato : alunos) {
            Grupo timeAtual = grupos.get(indexTime);
            candidato.setGrupo(timeAtual);
            alunoService.atualizar(candidato);

            indexTime = (indexTime + 1) % grupos.size();
        }
    }

    public String visualizarDivisao() {
        List<Grupo> times = grupoService.listarTodos();
        List<Aluno> candidatosSemTime = alunoService.buscarSemTime();

        StringBuilder sb = new StringBuilder();
        sb.append("=== DIVISÃO DE TIMES ===\n\n");

        for (Grupo time : times) {
            List<Aluno> candidatosDoTime = alunoService.buscarPorTime(time);
            sb.append(time.getNome()).append(" (").append(candidatosDoTime.size()).append(" jogadores):\n");

            for (Aluno candidato : candidatosDoTime) {
                sb.append("  - ").append(candidato.getNome()).append("\n");
            }
            sb.append("\n");
        }

        if (!candidatosSemTime.isEmpty()) {
            sb.append("Candidatos sem time (").append(candidatosSemTime.size()).append("):\n");
            for (Aluno candidato : candidatosSemTime) {
                sb.append("  - ").append(candidato.getNome()).append("\n");
            }
        }

        return sb.toString();
    }
}