package br.edu.ifpb.CriadorDeGrupos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaDivisaoTimesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaDivisaoTimesApplication.class, args);
        System.out.println("🚀 Sistema de Divisão de Times iniciado com sucesso!");
        System.out.println("📊 Acesse: http://localhost:8080/api");
    }
}