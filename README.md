# Atividade Backend - Conta Azul
- Atividade para seleção Java Backend
- Eduardo Barreto Brito
- Data de início: 03.08.2022

A atividade consiste em construir uma aplicação com o Spring Boot que aceita requisições REST para o caminho "/rest/mars/{comando}" para controlar um robô, que retorna como resposta a posição final do robô após os comandos.

A aplicação foi desenvolvida com Java 1.8 e, para rodar, basta executar os comandos a seguir na pasta raiz do projeto.
```
    mvn clean
    mvn install
    mvn spring-boot:run
```

Disclaimer: como não é necessário guardar a última posição do robô, a camada de repositório foi abstraída. 