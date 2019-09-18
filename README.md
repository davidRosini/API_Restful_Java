# Gerenciador de gastos
- API Backend restful para gerenciar gastos.

- Projeto utiliza as tecnologias Java 8, Spring boot, web, data jpa, jdbc, actuator, swagger2, swagger-UI, mapstruct, h2database e lombok.

- Projeto é baseado no modelo maven de dependências.

# Configuração e execução da API

1: Instale JDK java 8 -> https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

2: Instale o Maven -> https://maven.apache.org/download.cgi

3: Configurados, na pasta do projeto pelo terminal execute os comandos

4: mvn clean install

5: mvn spring-boot:run

- A API está disponível no link (local) -> http://localhost:8080/
- End-points criados disponível para teste em (local) -> http://localhost:8080/swagger-ui.html
- Banco de dados utilizado em memória após a execução do API -> http://localhost:8080/h2-console -> url jdbc:h2:mem:runtime
