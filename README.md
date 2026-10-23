# Diagnostic Catalog

A REST service for structured diagnostic knowledge. Each entry records an observed
**symptom**, its likely **cause** and the **remedy**, linked to the affected
**component** and tagged for retrieval — the structure of the *Symptom / Possible
Cause / Corrective Action* tables found in service manuals.

The goal is search that works on meaning rather than on keywords: a query for
*"motor won't start when it's cold"* should find an entry titled *"Unit does not
power on after cold storage"*, which shares no keyword with it.

All sample data in this repository is fictional.

## Status

Under development. Currently available: `GET /entries`, `GET /entries/{id}` and
`POST /entries`, backed by PostgreSQL. Search, update and delete are not implemented yet.

## Requirements

- Java 21
- Docker (for the PostgreSQL database)

Maven is not needed, the wrapper `./mvnw` is included.

## Setup

### 1. Choose a database password

Create a file named `.env` in the project root. It is listed in `.gitignore` and must
never be committed. It needs two variables with the **same** value, which you choose yourself:

```
POSTGRES_PASSWORD=<your-password>
SPRING_DATASOURCE_PASSWORD=<your-password>
```

`POSTGRES_PASSWORD` is used by the database container, `SPRING_DATASOURCE_PASSWORD` by the application.

### 2. Create the database container (once)

```bash
docker run -d --name diagnostic-catalog-db --env-file .env -e POSTGRES_USER=catalog -e POSTGRES_DB=diagnostic_catalog -p 5432:5432 -v diagnostic-catalog-data:/var/lib/postgresql postgres:18
```

The data lives in the Docker volume `diagnostic-catalog-data` and survives the container.
From now on, start the existing container with:

```bash
docker start diagnostic-catalog-db
```

Port 5432 must be free on your machine. `POSTGRES_PASSWORD` only takes effect when the
volume is created, changing it later requires removing the volume.

### 3. Run the application

Load the variables into your shell:

```bash
set -a; . ./.env; set +a
```

Start the service:

```bash
./mvnw spring-boot:run
```

It listens on `http://localhost:8080`. Flyway creates the tables and sample data on startup.

## Try it

```bash
curl http://localhost:8080/actuator/health
```

```bash
curl http://localhost:8080/entries
```

```bash
curl http://localhost:8080/entries/1
```

Create an entry (this writes to the database, and there is no `DELETE` yet):

```bash
curl -i -X POST http://localhost:8080/entries -H 'Content-Type: application/json' -d '{"symptom":"Unit beeps twice at start","cause":"Battery is low","remedy":"Replace the battery","component":{"id":2},"tags":["beep","battery"]}'
```

The response is `201 Created` with a `Location` header pointing to the new entry.

## Tests

```bash
./mvnw clean test
```

The tests run against an in-memory H2 database and need neither the container nor the password.

## License

Apache License 2.0 — see [LICENSE](LICENSE).
