# 🏆 Sistema de Divisão de Times
Um sistema CRUD desenvolvido em Java com Spring Boot para divisão aleatória de candidatos em times.

## 📋 Sobre o Projeto
### Sistema que permite aos usuários:

+ Cadastrar candidatos com nomes

+ Definir quantidade de times

+ Realizar divisão aleatória e equilibrada dos candidatos entre os times

+ Gerenciar completamente os dados sem uso do Spring Data JPA

## 🛠 Tecnologias Utilizadas

+ Java 17

+ Spring Boot 3.2.0

+ JPA/Hibernate (sem Spring Data JPA)

+ MySQL 8.0

+ Maven

+ Jakarta Persistence

## 🚀 Funcionalidades
✅ CRUD Completo de Candidatos e Times

✅ Divisão Aleatória de candidatos entre times

✅ API REST para integração

✅ Persistência com JPA/Hibernate puro

✅ Validações de entrada

✅ Transações gerenciadas manualmente

## 📦 Estrutura do Projeto

<pre><code>src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── divisaotimes/
│   │           ├── config/
│   │           │   └── DatabaseConfig.java
│   │           ├── model/
│   │           │   ├── Candidato.java
│   │           │   └── Time.java
│   │           ├── dao/
│   │           │   ├── CandidatoDAO.java
│   │           │   └── TimeDAO.java
│   │           ├── service/
│   │           │   ├── CandidatoService.java
│   │           │   ├── TimeService.java
│   │           │   └── DivisaoService.java
│   │           ├── controller/
│   │           │   └── DivisaoTimesController.java
│   │           └── SistemaDivisaoTimesApplication.java
│   └── resources/
│       └── application.properties</code></pre>

## ⚙️ Configuração e Instalação

*Pré-requisitos*

+ Java 17 ou superior

+ MySQL 8.0 ou superior

+ Maven 3.6+

## 1. Clone o repositório

bash
git clone https://github.com/seu-usuario/sistema-divisao-times.git
cd sistema-divisao-times

## 2. Configure o banco de dados
sql
CREATE DATABASE divisao_times;

## 3. Configure a aplicação
Edite o arquivo src/main/resources/application.properties:

## properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/divisao_times?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

## JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

## 4. Execute a aplicação

bash
mvn spring-boot:run
A aplicação estará disponível em: http://localhost:8080

## 📡 API Endpoints
+ Divisão de Times
+ POST /api/divisao/dividir

json
{
  "quantidadeTimes": 3,
  "nomesCandidatos": ["João", "Maria", "Pedro", "Ana", "Carlos", "Julia"]
}
Resposta:

json
[
  {
    "id": 1,
    "nome": "Time 1",
    "candidatos": [
      {"id": 1, "nome": "João", "time": {"id": 1, "nome": "Time 1"}},
      {"id": 4, "nome": "Ana", "time": {"id": 1, "nome": "Time 1"}}
    ]
  },
  {
    "id": 2,
    "nome": "Time 2", 
    "candidatos": [
      {"id": 2, "nome": "Maria", "time": {"id": 2, "nome": "Time 2"}},
      {"id": 5, "nome": "Carlos", "time": {"id": 2, "nome": "Time 2"}}
    ]
  }
]

## 🎯 Como Usar
Inicie a aplicação

Envie uma requisição POST para /api/divisao/dividir com:

quantidadeTimes: Número de times desejados

nomesCandidatos: Lista com nomes dos participantes

Receba a divisão aleatória dos times

Os dados são persistidos automaticamente no banco MySQL

## 🔧 Características Técnicas

+ Implementação sem Spring Data JPA
+ Este projeto utiliza JPA/Hibernate puro com:

+ EntityManager para operações de persistência

+ @PersistenceContext para injeção de dependência

+ Transações gerenciadas manualmente com @Transactional

+ Queries JPQL customizadas

*Exemplo de DAO Implementation*
java
@Repository
public class CandidatoDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public Candidato save(Candidato candidato) {
        if (candidato.getId() == null) {
            entityManager.persist(candidato);
            return candidato;
        } else {
            return entityManager.merge(candidato);
        }
    }
}
## 🗃 Modelo de Dados
Tabela: candidatos
Campo	Tipo	Descrição
id	BIGINT	Chave primária auto-incremento
nome	VARCHAR(100)	Nome do candidato
time_id	BIGINT	Chave estrangeira para times
Tabela: times
Campo	Tipo	Descrição
id	BIGINT	Chave primária auto-incremento
nome	VARCHAR(50)	Nome do time
## 🧪 Testando a API
Usando cURL:
bash
curl -X POST http://localhost:8080/api/divisao/dividir \
  -H "Content-Type: application/json" \
  -d '{
    "quantidadeTimes": 2,
    "nomesCandidatos": ["Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"]
  }'
Usando Postman:
Método: POST

URL: http://localhost:8080/api/divisao/dividir

Body (raw JSON):

json
{
  "quantidadeTimes": 2,
  "nomesCandidatos": ["Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"]
}
## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👥 Contribuição
Contribuições são sempre bem-vindas!

