# Bank Agencies
Micro serviço que consome endpoint do banco central que contém todas as agencias bancárias no Brasil

# Pré-Requisitos para executar o projeto localmente
* Java 11
* Maven
* Git
* Intellij, Visual Studio Code, Eclipse ou a IDE de sua preferência

# Swagger
* http://localhost:8080/bank-agencies/swagger-ui.html

# Sobre o projeto
Esse projeto é uma API REST com SpringBoot, Maven em JAVA 11 usando uma adaptação do modelo 'Clean Architecture'.

O endpoint exposto(/agencies) retorna uma lista com informações de todas as agências da instituição bancária "Banco do Brasil" de toda a extensão nacional.

Dados obtidos através do Portal de Dados do Banco Central do Brasil:
https://dadosabertos.bcb.gov.br/dataset/agencias
