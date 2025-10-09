package br.edu.ifpb.CriadorDeGrupos.config;



import br.edu.ifpb.CriadorDeGrupos.service.DivisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DivisaoService divisaoService;

    @Override
    public void run(String... args) throws Exception {



        System.out.println("🎯 Sistema de Divisão de Times Pronto!");
        System.out.println("💡 Use os endpoints REST para testar:");
        System.out.println("   POST /api/dividir");
        System.out.println("   GET  /api/times");
        System.out.println("   GET  /api/candidatos");
        System.out.println("   GET  /api/divisao/visual");
        System.out.println("   DELETE /api/limpar");


    }
}
