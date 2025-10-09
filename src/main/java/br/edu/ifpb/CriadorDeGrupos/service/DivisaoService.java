package br.edu.ifpb.CriadorDeGrupos.service;

import br.edu.ifpb.CriadorDeGrupos.model.Candidato;
import br.edu.ifpb.CriadorDeGrupos.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DivisaoService {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private TimeService timeService;

    public List<Time> dividirTimes(int quantidadeTimes, List<String> nomesCandidatos) {
        // Limpar dados anteriores
        limparDadosAnterios();

        // Validar entrada
        if (quantidadeTimes <= 0) {
            throw new IllegalArgumentException("Quantidade de times deve ser maior que zero");
        }

        if (nomesCandidatos == null || nomesCandidatos.isEmpty()) {
            throw new IllegalArgumentException("Lista de candidatos não pode estar vazia");
        }

        // Criar times
        List<Time> times = criarTimes(quantidadeTimes);

        // Criar candidatos
        List<Candidato> candidatos = criarCandidatos(nomesCandidatos);

        // Embaralhar candidatos
        List<Candidato> candidatosEmbaralhados = embaralharLista(candidatos);

        // Distribuir candidatos nos times
        distribuirCandidatos(candidatosEmbaralhados, times);

        return timeService.listarTodos();
    }

    private void limparDadosAnterios() {
        // Primeiro limpar os candidatos (devido à constraint de foreign key)
        candidatoService.limparCandidatos();
        // Depois limpar os times
        timeService.limparTimes();
    }

    private List<Time> criarTimes(int quantidade) {
        List<Time> times = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Time time = timeService.criarTime("Time " + i);
            times.add(time);
        }
        return times;
    }

    private List<Candidato> criarCandidatos(List<String> nomes) {
        List<Candidato> candidatos = new ArrayList<>();
        for (String nome : nomes) {
            Candidato candidato = candidatoService.criarCandidato(nome);
            candidatos.add(candidato);
        }
        return candidatos;
    }

    private <T> List<T> embaralharLista(List<T> lista) {
        List<T> copia = new ArrayList<>(lista);
        Collections.shuffle(copia);
        return copia;
    }

    private void distribuirCandidatos(List<Candidato> candidatos, List<Time> times) {
        int indexTime = 0;

        for (Candidato candidato : candidatos) {
            Time timeAtual = times.get(indexTime);
            candidato.setTime(timeAtual);
            candidatoService.atualizar(candidato);

            indexTime = (indexTime + 1) % times.size();
        }
    }

    public String visualizarDivisao() {
        List<Time> times = timeService.listarTodos();
        List<Candidato> candidatosSemTime = candidatoService.buscarSemTime();

        StringBuilder sb = new StringBuilder();
        sb.append("=== DIVISÃO DE TIMES ===\n\n");

        for (Time time : times) {
            List<Candidato> candidatosDoTime = candidatoService.buscarPorTime(time);
            sb.append(time.getNome()).append(" (").append(candidatosDoTime.size()).append(" jogadores):\n");

            for (Candidato candidato : candidatosDoTime) {
                sb.append("  - ").append(candidato.getNome()).append("\n");
            }
            sb.append("\n");
        }

        if (!candidatosSemTime.isEmpty()) {
            sb.append("Candidatos sem time (").append(candidatosSemTime.size()).append("):\n");
            for (Candidato candidato : candidatosSemTime) {
                sb.append("  - ").append(candidato.getNome()).append("\n");
            }
        }

        return sb.toString();
    }
}