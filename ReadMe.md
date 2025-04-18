Ce projet est un microservice Spring Boot qui gère des entités `Categorie` dans une architecture microservices avec Eureka Discovery Server et une API Gateway.

Architecture Microservices
- Eureka : pour le service discovery dynamique.
- API Gateway (Spring Cloud Gateway) : pour router les requêtes client vers les microservices enregistrés.
- Ce microservice `gestion-categories` est enregistré dans Eureka sous le nom `gestionA`.


Fonctionnalités principales

- CRUD complet sur les entités `Categorie`
- Filtrage des catégories par pays d'origine (enum `Pays`)
- Statistiques simples (nombre total de catégories)
- Export CSV des catégories (téléchargement direct)
- API externe Cat Fact (https://catfact.ninja)
- API externe Dog Image (https://dog.ceo)
- API pays (https://restcountries.com)

---

Technologies utilisées

- Java 17+
- Spring Boot
- Spring Web
- RestTemplate
- Maven
- Jakarta Servlet API (pour export CSV)

Les endpoints seront accessibles via :

- http://localhost:7000/categories

Auteur
 - Thabti Ahmed
 - ahmed.thabti@esprit.tn

