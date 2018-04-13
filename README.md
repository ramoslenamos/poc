# POC Data hub ISTIC
Auteurs : REZK Mino, POINCE Alexis, BOITARD Romain, DUMONT Axel, WAFI Othmane.
## Présentation
Démonstration de la faisabilité d'une API permettant d'accéder au données de l'ISTIC. Nous proposons des exemples de services REST pour accéder aux données des étudiants, tout en ignorant la source des données et sa complexité. L'architecture choisie permet d'ajouter des sources de données et des services web utilisant plusieurs sources.
## Installation :
1. Sur mySQL : éxécuter le script poc_create_insert_userinfos.sql.
2. Lancer avec maven : ```mvn spring-boot:run``` ou importer et lancer à l'aide d'un IDE (projet Spring Boot).
3. Documentation de l'API : http://localhost:XXXX/swagger-ui.html.
### Informations complémentaires :
Définir le port et les informations de connexion mySQL : /src/main/resources/application.properties.
## Technologies :
Java, REST, JSON, MySQL, Spring Boot, Hibernate, Tomcat, Unirest, Swagger.


