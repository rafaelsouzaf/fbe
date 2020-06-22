# FBE (employee storage service for companies)

Java Spring Boot application.

https://github.com/rafaelsouzaf/fbe

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
| GET ALL           | `curl -X GET localhost:8081/company`
| GET BY ID         | `curl -X GET localhost:8081/company/3`
| ADD               | `curl -X POST localhost:8081/company -H 'Content-type:application/json' -d '{"name": "Company Name Here"}'`
| EDIT              | `curl -X PUT localhost:8081/company -H 'Content-type:application/json' -d '{"id": 3, "name": "Changed Company Name"}'`
| DELETE            | `curl -X DELETE localhost:8081/company/3`
| AVERAGE SALARY BY ID | `curl -X GET localhost:8081/company/average-salary/3`


### Employee

| ACTION            | cURL
| ------            | ------
| GET ALL           | `curl -X GET localhost:8081/employee`
| GET BY ID         | `curl -X GET localhost:8081/employee/3`
| ADD               | `curl -X POST localhost:8081/employee -H 'Content-type:application/json' -d '{"name": "Rafael", "surname": "Fijalkowski", "email": "myemail@gmail.com", "address": "Street 41, 50832", "salary": 120000, "company": {"id": 4}}'`
| EDIT              | `curl -X PUT localhost:8081/employee -H 'Content-type:application/json' -d '{"id": 3, "name": "Rafael", "surname": "Fijalkowski", "email": "myemail@gmail.com", "address": "Street 41, 50832", "salary": 120000, "company": {"id": 4}}'`
| DELETE            | `curl -X DELETE localhost:8081/employee/3`


### Exceptions/Errors (some examples)

| ACTION            | cURL
| ------            | ------
| 404 endpoint      | `curl -X GET localhost:8081/blahblah`
| 404 company       | `curl -X GET localhost:8081/company/3000`
| 404 employee      | `curl -X GET localhost:8081/employee/3000`
| 400 bad request   | `curl -X GET localhost:8081/company/asdasdasd`

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

`mvn clean test` (pending)

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
- [ ] Unit/Integration tests.
- [ ] Filter response data with @JsonView or DTO?
- [ ] Authentication?
- [ ] Physical logs?
- [ ] Add JenkinsFile and deploy to some place?
- [ ] Add support to filters such as orderBy, limit, offset?
