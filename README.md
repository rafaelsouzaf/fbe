# FBE (employee storage service for companies)

This project:
https://github.com/rafaelsouzaf/fbe

Web client Project:
https://github.com/rafaelsouzaf/fbe-client

## Requirement Specification
Please visit https://gist.github.com/rafaelsouzaf/01cf788673c617802b70392f4bb7e3dd

## Solution

- Spring Boot
- Hibernate
- Docker
- PostgreSQL

### Requirements

- Java 11
- Docker
- Lombok plugin for your IDE.
Intellij:
https://plugins.jetbrains.com/plugin/6317-lombok
Eclipse:
https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/

### Endpoints (curl)

#### Company

| ACTION            | cURL
| ------            | ------
| ALL               | `curl -X GET localhost:8081/company`
| ALL WITH AVG SALARY| `curl -X GET localhost:8081/company/with-avg-salary`
| T BY ID           | `curl -X GET localhost:8081/company/3`
| ADD               | `curl -X POST localhost:8081/company -H 'Content-type:application/json' -d '{"name": "Company Name Here"}'`
| EDIT              | `curl -X PUT localhost:8081/company -H 'Content-type:application/json' -d '{"id": 3, "name": "Changed Company Name"}'`
| DELETE            | `curl -X DELETE localhost:8081/company/3`
| AVERAGE SALARY BY ID | `curl -X GET localhost:8081/company/average-salary/3`
| COUNT             | `curl -X GET localhost:8081/company/count`


### Employee

| ACTION            | cURL
| ------            | ------
| ALL               | `curl -X GET localhost:8081/employee`
| BY ID             | `curl -X GET localhost:8081/employee/3`
| ADD               | `curl -X POST localhost:8081/employee -H 'Content-type:application/json' -d '{"name": "Rafael", "surname": "Fijalkowski", "email": "myemail@gmail.com", "address": "Street 41, 50832", "salary": 120000, "company": {"id": 4}}'`
| EDIT              | `curl -X PUT localhost:8081/employee -H 'Content-type:application/json' -d '{"id": 3, "name": "Rafael", "surname": "Fijalkowski", "email": "myemail@gmail.com", "address": "Street 41, 50832", "salary": 120000, "company": {"id": 4}}'`
| DELETE            | `curl -X DELETE localhost:8081/employee/3`
| COUNT             | `curl -X GET localhost:8081/employee/count`


### Exceptions/Errors (some examples)

| ACTION            | cURL
| ------            | ------
| 404 endpoint      | `curl -X GET localhost:8081/blahblah`
| 404 company       | `curl -X GET localhost:8081/company/3000`
| 404 employee      | `curl -X GET localhost:8081/employee/3000`
| 400 bad request   | `curl -X GET localhost:8081/company/asdasdasd`

### Actuator

| ACTION            | cURL
| ------            | ------
| Health            | `curl -X GET localhost:8081/actuator/health`

### Running

```
# Clone the repository
git clone https://github.com/rafaelsouzaf/fbe
cd fbe

# Start docker containers (PostgreSQL and PgAdmin4)
docker-compose up

# Start App
mvn spring-boot:run
```

### Testing

```
# Start docker containers (PostgreSQL and PgAdmin4)
docker-compose up

# Test
mvn clean test
```

### Building

`mvn clean install` (pending)

### SQL Structure

Please see:

`./ddl-reference-structure.sql`

### Docker (Postgres and PgAdmin4)

This project are using the Postgres database and PgAdmin4 web client. Both are running in 
docker containers:

#### PgAdmin4
- Link: http://localhost:5050/browser/
- PgAdmin4 User: pgadmin4@pgadmin.org
- PgAdmin4 Password: admin

#### Postgres DB 
- Host: postgres_container (Docker's hostname)
- Database: postgres
- User: postgres
- Pass: admin

### TODO

- [X] Encapsulate JSON response in standard format
- [X] Handling exceptions.
- [X] Integration tests.
- [X] Deployed to Heroku
- [ ] Add support to filters such as orderBy, limit, offset?
- [ ] Filter response data with @JsonView or DTO?
- [ ] Authentication?
- [ ] Physical logs?
