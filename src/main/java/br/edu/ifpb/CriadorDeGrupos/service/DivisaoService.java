package br.edu.ifpb.CriadorDeGrupos.service;


import com.divisaotimes.model.Candidato;
import com.divisaotimes.model.Time;
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

        // Criar times
        List<Time> times = criarTimes(quantidadeTimes);

        // Criar candidatos
        List<Candidato> candidatos = criarCandidatos(nomesCandidatos);

        // Embaralhar candidatos
        List<Candidato> candidatosEmbaralhados = embaralharLista(candidatos);

        // Distribuir candidatos nos times
        distribuirCandidatos(candidatosEmbaralhados, times);

        return times;
    }

    private void limparDadosAnterios() {
        // Implementar se necessário limpar dados anteriores
    }

    private List<Time> criarTimes(int quantidade) {
        List<Time> times = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Time time = new Time("Time " + i);
            timeService.salvar(time);
            times.add(time);
        }
        return times;
    }

    private List<Candidato> criarCandidatos(List<String> nomes) {
        List<Candidato> candidatos = new ArrayList<>();
        for (String nome : nomes) {
            Candidato candidato = new Candidato(nome);
            candidatoService.salvar(candidato);
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
}