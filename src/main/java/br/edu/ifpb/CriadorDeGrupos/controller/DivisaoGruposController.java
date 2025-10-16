package br.edu.ifpb.CriadorDeGrupos.controller;


import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import br.edu.ifpb.CriadorDeGrupos.service.AlunoService;
import br.edu.ifpb.CriadorDeGrupos.service.DivisaoService;
import br.edu.ifpb.CriadorDeGrupos.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DivisaoGruposController {

    @Autowired
    private DivisaoService divisaoService;

    @Autowired
    private GrupoService timeService;

    @Autowired
    private AlunoService candidatoService;

    @PostMapping("/dividir")
    public Map<String, Object> dividirTimes(@RequestBody DivisaoRequest request) {
        try {
            List<Grupo> times = divisaoService.dividirTimes(
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
    public List<Grupo> listarTimes() {
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