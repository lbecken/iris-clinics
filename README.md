# Iris Clinics

A clinic management web application built with Jakarta EE 10 and Jakarta Faces (JSF). Manages patients, doctors and appointments for ophthalmology clinics.

## Tech Stack

- **Java 21**
- **Jakarta EE 10** (CDI, JPA, JSF, JAX-RS, Security)
- **WildFly** (provisioned via wildfly-maven-plugin with Galleon layers)
- **PrimeFaces 14** (JSF component library)
- **PostgreSQL 17** (via Docker)
- **Hibernate** (JPA provider, provided by WildFly)
- **JUnit 5** + **Mockito** (unit tests)
- **Arquillian** (integration tests, deployed to managed WildFly)

## Features

- **Patient management** -- list, create, edit patients
- **Doctor management** -- list, create, edit, activate/deactivate doctors
- **Appointment management** -- list appointments with patient and doctor details
- **Authentication** -- form-based login using Jakarta Security with a custom database identity store
- **Role-based authorization** -- menu items and pages restricted by user role (Admin, Secretary, Doctor)
- **REST API** -- JSON endpoints for patients, doctors and appointments

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker

## Getting Started

### 1. Start PostgreSQL

```bash
docker compose up -d
```

This starts a PostgreSQL 17 container and runs the init scripts (`src/main/resources/db/`) to create tables and load mock data.

### 2. Set environment variables

```bash
source .env
```

### 3. Build and run

```bash
mvn clean package wildfly:dev
```

The application is available at http://localhost:8080/iris/

### Login credentials (mock data)

| Username    | Password | Role      |
|-------------|----------|-----------|
| admin       | admin    | Admin     |
| jsmith      | pass123  | Secretary |
| mwilliams   | pass123  | Secretary |
| drjones     | pass123  | Doctor    |
| drmartinez  | pass123  | Doctor    |
| drpatel     | pass123  | Doctor    |

## REST API

Base URL: `http://localhost:8080/iris/api`

| Method | Endpoint        | Description          |
|--------|-----------------|----------------------|
| GET    | /patients       | List all patients    |
| GET    | /doctors        | List all doctors     |
| GET    | /appointments   | List all appointments|

## Project Structure

```
src/main/java/com/iris/
  appointment/       # Appointment bean, service, repository, REST, model
  auth/              # LoginBean, AuthService
  clinic/            # Clinic model (Address, Clinic)
  patient/           # Patient bean, service, repository, REST, model
  security/          # SecurityConfig, DatabaseIdentityStore
  staff/             # Doctor bean, service, repository, REST, model
  user/              # User model, UserRole, repository

src/main/webapp/
  login.xhtml                 # Login page
  main.xhtml                  # Root template (header/content/footer layout)
  templates/main.xhtml        # Common template (menu, footer)
  patient/                    # Patient list and edit pages
  staff/                      # Doctor list and edit pages
  appointment/                # Appointment list page

src/main/resources/
  db/01-init.sql              # Database schema
  db/02-mock-data.sql         # Mock data
  META-INF/persistence.xml    # JPA configuration

src/main/scripts/
  configure-security.cli      # WildFly JASPI/security configuration

src/test/java/com/iris/
  ...                         # Unit tests (*Test.java) and integration tests (*IT.java)

src/test/resources/
  arquillian.xml              # Arquillian container configuration
```

## Architecture

- **View layer** -- Facelets (.xhtml) with PrimeFaces components
- **Managed beans** -- CDI beans (`@ViewScoped`, `@SessionScoped`, `@ApplicationScoped`)
- **Services** -- `@ApplicationScoped` CDI beans with `@Transactional` for write operations
- **Repositories** -- `@ApplicationScoped` CDI beans using JPA `EntityManager`
- **REST** -- JAX-RS resources under `/api`
- **Security** -- Jakarta Security with `@CustomFormAuthenticationMechanismDefinition`, custom `IdentityStore`, role-based access via `web.xml` security constraints
- **Database** -- PostgreSQL, schema managed via SQL scripts (JPA schema generation set to `none`)
- **Testing** -- unit tests with JUnit 5 + Mockito; integration tests with Arquillian (deployed to a managed WildFly instance)

## Testing

### Unit tests

```bash
mvn test
```

Runs `*Test.java` files via Maven Surefire. These use Mockito to test service logic in isolation without a container or database.

### Integration tests

```bash
mvn clean package -DskipTests
mvn verify -Dtest=none -DfailIfNoTests=false
```

Runs `*IT.java` files via Maven Failsafe. These deploy a mini WAR to a real WildFly instance (provisioned in `target/server`) and test against the live PostgreSQL database. Make sure PostgreSQL is running (`docker compose up -d`) and environment variables are set (`source .env`) before running.
