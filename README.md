# EniStore

Projet réalisé dans le cadre de la formation ENI.
Le cours de base consistait en un projet Java simple. J'ai choisi d'aller plus loin en intégrant des technologies supplémentaires pour me rapprocher d'un projet enterprise réel.

### Ce que j'ai ajouté par rapport au cours : 

- Spring Security — sécurisation de l'API avec authentification JWT 
- MapStruct — mapping automatique entre entités et DTOs

## Stack technique

**Back-end**
- Java / Spring Boot
- Spring Security + JWT — authentification et gestion des profils utilisateurs
- Spring Data JPA — persistance relationnelle (SQL)
- Spring Data MongoDB — persistance NoSQL
- MapStruct — mapping entre entités et DTOs
- Validation — contrôle des données entrantes
- Swagger / OpenAPI — documentation interactive de l'API REST
- Gradle — gestion des dépendances et build
- Configuration YAML

**Front-end**
- Angular CLI v21.2.0

---

## Lancer le projet

### Back-end

```bash
./gradlew bootRun
```

L'API est accessible sur `http://localhost:8080`.
La documentation Swagger est disponible sur `http://localhost:8080/swagger-ui.html`.

### Front-end

```bash
ng serve
```

L'application Angular est accessible sur `http://localhost:4200/` et se recharge automatiquement à chaque modification.

---

## Build

```bash
ng build
```

Les fichiers compilés sont générés dans le répertoire `dist/`. Le build de production optimise automatiquement les performances.

---

## Tests

### Tests unitaires

```bash
ng test
```

Exécute les tests unitaires via [Vitest](https://vitest.dev/).

### Tests end-to-end

```bash
ng e2e
```

---

## Génération de composants

```bash
ng generate component component-name
```

Pour la liste complète des schematics disponibles :

```bash
ng generate --help
```
