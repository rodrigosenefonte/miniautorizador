###############
MINIAUTORIZADOR
###############

###########
Tecnologias e ferramentas

JDK 17
Spring Framework
Spring Boot 
Spring Security
JPA
Lombok
Postgres
Git
GitHub
Docker
Postman

###########
Considerações

Devido a alguns probleminhas durante a configuração tanto do mysql quanto do Mongo, optei por utilizar uma conexão com o SGBD Postgres configurado em docker-compose.yml.

Basicamente, optei por criar uma aplicação limpa no estilo três camadas com o Controller chamado um Service que por sua vez chama um Repository que utiliza os métodos Save (herdado de JPARpository) e a customização de um método para a busca das informações dos cartões. Inclusive, como o body do response precisava ser customizado, optei por implementar VOs.

Com o objetivo de evitar o excesso de ifs no código, procurei utilizar a implentação de operadores ternários e a classe Optional para prevenir NullPointerException.

Criei as classses dos Testes Unitários pelo próprio Intellij, optei pelo JUnit 5 e Mockito.

Para tentar previnir um possível problema relacionado a concorrência, eu optei por utilziar a anotração @Transational do Spring

A branch utilziada foi a 'master'



############
Sites úteis:

https://start.spring.io/
https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html
https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html


