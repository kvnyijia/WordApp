
## Prerequisite
This backend service requires a PostgreSQL database running on port 5431
```bash
docker start your-postgres-db
```

## How to run
```bash
./mvnw spring-boot:run
```
Running on http://localhost:8080

## How to build jar file
```bash
mvn package
```

```bash
java -jar target/*.jar
```

## How to run tests

```bash
./mvnw test
```
Run all tests

```bash
./mvnw test -Dtest=WordControllerTest
```
Run a specific test class

```bash
./mvnw test -Dtest=WordControllerTest#shouldCreateNewWord
```
Run a specific test method
