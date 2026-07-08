# 80/20 Task-Manager (Fullstack End-to-End)

Ein minimalistisches, aber architektonisch sauberes Fullstack-Webprojekt, das den kompletten Datenfluss von der Datenbank bis in den Browser demonstriert. Entwickelt nach dem "80/20-Prinzip" (maximale Funktionalität mit dem wichtigsten Kern-Code).

## Tech-Stack
* **Backend:** Java 21, Spring Boot 3, Spring Data JPA
* **Frontend:** Angular 17+ (Standalone Components, TypeScript)
* **Datenbank:** PostgreSQL 16
* **DevOps:** Docker, Docker Compose (Multi-Stage Builds), Nginx

## Architektur & Roadmap
Das Projekt wurde in 5 systematischen Schichten aufgebaut ("Monorepo-Ansatz"):
1. **Der Keller (Datenbank):** `Task` Entity und `TaskRepository` für automatische SQL-Generierung via Hibernate.
2. **Das Gehirn (Geschäftslogik):** `TaskService` mit moderner Constructor-Injection.
3. **Der Schalter (Schnittstelle):** `TaskController` als REST-API, Datentransfer über Java 21 Records (DTOs).
4. **Das Gesicht (UI):** Eigenständiges Angular-Frontend mit reaktiven HTTP-Calls.
5. **Das Paket (Infrastruktur):** Multi-Stage Dockerfiles für isolierte Build- und Laufzeitumgebungen.

## Lokaler Start (Getting Started)
Voraussetzung: Docker und Docker Compose sind installiert.

1. Repository klonen
2. Im Hauptverzeichnis das Terminal öffnen und ausführen:
   ```bash
   docker-compose up -d --build
   ```
3. Das Frontend ist nun unter `http://localhost:4200` erreichbar, die API unter `http://localhost:8080/api/tasks`.

## Gelöste Herausforderungen (Lessons Learned)
Während der Entwicklung wurden folgende technische Hürden gemeistert:
* **Angular Change Detection (Timing):** Asynchrone REST-Antworten (RxJS Subscriptions) haben die UI nicht sofort aktualisiert. Gelöst durch den gezielten Einsatz von `ChangeDetectorRef.detectChanges()` für verzögerungsfreies Rendering.
* **CORS im Container-Netzwerk:** Strikte Browser-Richtlinien blockierten API-Calls vom Nginx-Frontend zum Tomcat-Backend. Gelöst durch eine globale `WebMvcConfigurer`-Klasse im Spring Boot Backend.
* **Multi-Stage Docker Builds:** Optimierung der Image-Größen, indem das JDK/Node.js nur zum Kompilieren genutzt wird und für die Laufzeit nur eine minimale JRE bzw. ein Nginx-Server verwendet wird.
* **Git-Submodule-Konflikte:** Verhinderung von verschachtelten Repositories durch Bereinigung des isolierten `.git`-Ordners der Angular CLI zugunsten eines sauberen Monorepos.

## Nächste Schritte & Optimierungen
Dieses Projekt dient als solides Fundament und kann in folgende Richtungen skaliert werden:
1. **Testing:** Implementierung von Unit-Tests mit *JUnit 5* und *Mockito* für den Backend-Service sowie *WebMvcTest* für den Controller.
2. **Security:** Einbindung von *Spring Security* mit JWT-Tokens für geschützte API-Routen und eine Login-Maske im Frontend.
3. **State Management:** Refactoring der Angular Change Detection auf die neuen *Angular Signals* für noch performanteres Reaktiv-Programmieren.
4. **CI/CD Pipeline:** Erstellen einer GitHub Actions Workflow-Datei (`.yml`), die bei jedem Push automatisch das Projekt baut und testet.
5. **Globales Exception Handling:** Eine `@ControllerAdvice` Klasse in Spring Boot hinzufügen, um saubere und einheitliche JSON-Fehlermeldungen bei Abstürzen an das Frontend zu senden.