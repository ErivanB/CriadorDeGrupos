package br.edu.ifpb.CriadorDeGrupos.controller;


import com.divisaotimes.model.Time;
import com.divisaotimes.service.DivisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisao")
public class DivisaoTimesController {

    @Autowired
    private DivisaoService divisaoService;

    @PostMapping("/dividir")
    public List<Time> dividirTimes(
            @RequestParam int quantidadeTimes,
            @RequestBody List<String> nomesCandidatos) {

        return divisaoService.dividirTimes(quantidadeTimes, nomesCandidatos);
    }
}