# FBE (employee storage service for companies)

(!!! Not ready !!!)

Java Spring Boot REST (CRUD) application.

https://github.com/rafaelsouzaf/fbe

## Requirement Specification

### Definition

We're building an employee storage service for companies. 

A company admin needs to be able to:

- create employee records for company,
- view them in a list and detail view for company, 
- update the employee records and delete them for company,
- find the average salary for the company

### Data definition

- Employee model
  - Name
  - Surname
  - email
  - address
  - salary
  - company id
  
- Company model
  - Name

## Solution

- Spring Boot
- Hibernate
- Docker
- Postgres
- cURL

### Requirements

- Java 11
- Docker
- Lombok plugin for your IDE.
Intellij:
https://plugins.jetbrains.com/plugin/6317-lombok
Eclipse:
https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/

### SQL Structure

Please see:

`./ddl-reference-structure.sql`


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

_** To connect the PgAdmin container with the Postgres container, in the PgAdmin configuration we need to 
use `host: postgres_container` (the host is the container's name). The `localhost` or `127.0.0.1` does 
not work because in this case is connections between containers._

### Running

```
# Start docker containers (PostgreSQL and PgAdmin4)
docker-compose up

# Start Spring Boot
mvn spring-boot:run
```

### Testing

`mvn clean test`

### Building

`mvn clean install `

### TODO

- [ ] Unit/Integration tests.
- [ ] Handling exceptions.
- [ ] Filter response data with @JsonView
- [ ] Authentication?
- [ ] Physical logs?
- [ ] Add JenkinsFile and deploy to some place?
- [x] Encapsulate JSON response in standard format
