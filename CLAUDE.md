# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build the WAR
mvn clean install

# Run with embedded WildFly (serves at http://localhost:8080/jsf-refresher-demo/)
mvn wildfly:run

# Build a bootable JAR
mvn clean wildfly:package
# Then run with: java -jar target/jsf-refresher-demo-bootable.jar
```

No tests exist yet. To add tests, create `src/test/java/`, add JUnit/Arquillian dependencies to pom.xml, and configure maven-surefire-plugin.

## Architecture

This is a minimal Jakarta Faces (JSF) web application packaged as a WAR, targeting **Java 21** and **Jakarta EE 10**.

- **View layer:** Facelets (`.xhtml` files in `src/main/webapp/`)
- **Managed beans:** CDI-managed beans in `com.example.jsf` package using Jakarta EE annotations (`@ViewScoped`, `@ApplicationScoped`, `@Named`, `@Inject`)
- **Server:** WildFly (provisioned automatically via `wildfly-maven-plugin` v5.1.5.Final with a minimal JSF-only layer)
- **CDI discovery:** `annotated` mode (only explicitly annotated beans are discovered — see `beans.xml`)
- **No faces-config.xml** — all JSF configuration is annotation-driven

### Key beans

- `HelloBean` (`@ViewScoped`) — form-backing bean that injects `CounterService`, demonstrates view-scoped state and action methods
- `CounterService` (`@ApplicationScoped`) — thread-safe singleton using `AtomicInteger`, shared across all views
