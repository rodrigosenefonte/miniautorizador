###############
MINIAUTORIZADOR
###############

###########
Tecnologias e ferramentas

JDK 17
Spring Boot 
Spring Security
Postgres
Docker
Postman

###########
Considerações

Devido a alguns probleminhas durante a configuração tanto do mysql quanto do Mongo, optei por utilizar uma conexão com o SGBD Postgres configurado em docker-compose.yml.

Basicamente, optei por criar uma aplicação limpa no estilo três camadas com o Controller chamado um Serivce que por sua vez chama um Repository que utiliza o save herado do core e a implementação de um método para a busca das informações dos cartões. Como o body do response precisava ser customizado, optei por implementar VOs.



############
Sites úteis:

https://start.spring.io/
https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html


