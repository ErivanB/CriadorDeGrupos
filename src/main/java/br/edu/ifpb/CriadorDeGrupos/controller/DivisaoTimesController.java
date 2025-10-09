package br.edu.ifpb.CriadorDeGrupos.controller;


import br.edu.ifpb.CriadorDeGrupos.model.Time;
import br.edu.ifpb.CriadorDeGrupos.service.CandidatoService;
import br.edu.ifpb.CriadorDeGrupos.service.DivisaoService;
import br.edu.ifpb.CriadorDeGrupos.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DivisaoTimesController {

    @Autowired
    private DivisaoService divisaoService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/dividir")
    public Map<String, Object> dividirTimes(@RequestBody DivisaoRequest request) {
        try {
            List<Time> times = divisaoService.dividirTimes(
                    request.getQuantidadeTimes(),
                    request.getNomesCandidatos()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("times", times);
            response.put("divisaoVisual", divisaoService.visualizarDivisao());

            return response;

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return response;
        }
    }

    @GetMapping("/times")
    public List<Time> listarTimes() {
        return timeService.listarTodos();
    }

    @GetMapping("/candidatos")
    public Object listarCandidatos() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/divisao/visual")
    public Map<String, String> visualizarDivisao() {
        Map<String, String> response = new HashMap<>();
        response.put("divisao", divisaoService.visualizarDivisao());
        return response;
    }

    @DeleteMapping("/limpar")
    public Map<String, String> limparDados() {
        candidatoService.limparCandidatos();
        timeService.limparTimes();

        Map<String, String> response = new HashMap<>();
        response.put("message", "Todos os dados foram limpos com sucesso!");
        return response;
    }

    // Classe interna para receber o request
    public static class DivisaoRequest {
        private int quantidadeTimes;
        private List<String> nomesCandidatos;

        // Getters e Setters
        public int getQuantidadeTimes() { return quantidadeTimes; }
        public void setQuantidadeTimes(int quantidadeTimes) { this.quantidadeTimes = quantidadeTimes; }

        public List<String> getNomesCandidatos() { return nomesCandidatos; }
        public void setNomesCandidatos(List<String> nomesCandidatos) { this.nomesCandidatos = nomesCandidatos; }
    }
}