# FBE (employee storage service for companies)

Java Spring Boot CRUD REST application.

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

```
CREATE DATABASE `fbe`;

DROP TABLE `employee`;
CREATE TABLE `employee` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`company_id` BIGINT NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`surname` VARCHAR(500) NOT NULL,
	`email` VARCHAR(255),
	`address` VARCHAR(255),
	`salary` INT NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (company_id) 
        REFERENCES company(id)
        ON DELETE RESTRICT
) ENGINE=InnoDB;

DROP TABLE `company`;
CREATE TABLE `company` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(500) NOT NULL,
	`create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`update_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;
```

### SQL Example data

```
INSERT INTO `company` (`name`) VALUES ("Lacus Ltd"),("Morbi Metus Vivamus Foundation"),("Natoque Penatibus Corporation"),("Cras Associates"),("Fringilla Consulting"),("Magna Sed Incorporated"),("Pharetra Felis Inc."),("Imperdiet Dictum Magna Limited"),("Nibh Dolor Ltd"),("Tincidunt Dui Augue Company");
```

```
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (4,"Caldwell","Suarez","mollis.nec.cursus@arcuiaculis.co.uk","3249 Nullam Avenue",3519),(10,"Calvin","Burns","nec@tinciduntvehicula.org","7718 Imperdiet Road",43666),(2,"Hanna","Stanley","hendrerit@quisaccumsan.ca","2190 Iaculis, Rd.",78651),(1,"Kermit","Foster","Aenean.egestas@adipiscingenimmi.edu","P.O. Box 420, 3983 Nascetur St.",10914),(4,"Grace","Howell","quam.quis@placeratorcilacus.com","788-9101 Integer Rd.",40742),(9,"Kiayada","Finch","velit.Cras.lorem@vitaedolorDonec.co.uk","P.O. Box 368, 2134 Est, Avenue",29754),(4,"Joelle","Bell","ullamcorper@semvitaealiquam.co.uk","150-9816 Dictum Road",97173),(2,"Lynn","Snyder","dolor.egestas@massa.com","827-811 Eget St.",12289),(9,"Ora","Gaines","et.libero@nisiAenean.net","Ap #280-8232 Risus St.",13801),(9,"Omar","Burke","nascetur.ridiculus@vulputateposuere.org","331-9772 Nulla Street",68953);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (10,"Tucker","Park","egestas.Aliquam@Integeraliquamadipiscing.ca","Ap #900-741 Nunc Av.",55508),(2,"Leah","Webster","erat.Sed.nunc@Lorem.co.uk","P.O. Box 423, 7696 Tempus Street",40144),(1,"Brenden","Solomon","Suspendisse@lacus.net","332-2789 Senectus Rd.",27379),(7,"Palmer","Kelly","adipiscing@pede.net","586-4682 Turpis. Av.",35474),(9,"Callie","Salinas","Nunc.mauris.elit@tortornibh.edu","P.O. Box 892, 4285 Sapien. Rd.",12782),(4,"Ebony","Dillon","purus@cursusaenim.edu","P.O. Box 169, 971 Nullam Rd.",5576),(10,"Hedley","Foster","sem@elitelitfermentum.com","P.O. Box 151, 3349 Cursus Rd.",18667),(6,"Joelle","Gillespie","et@quis.co.uk","Ap #279-358 Tempor Rd.",72407),(8,"Jamal","Reed","mollis.non.cursus@Suspendissenon.net","P.O. Box 973, 8782 Placerat. Rd.",7097),(3,"Neil","Burgess","Mauris.eu@lobortisquispede.net","845-6426 Ornare, Rd.",5710);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (9,"Bruce","Davis","ridiculus@rutrumlorem.com","P.O. Box 960, 9659 Velit Rd.",22491),(1,"Vivien","Owen","ipsum.porta@fringillapurus.com","276-8581 Quam, Road",61659),(9,"Sybil","Santos","elementum.sem@dapibus.co.uk","640-6324 Nisi Av.",39369),(6,"Bernard","Hess","nec.mauris.blandit@at.com","935-515 Nec Avenue",70767),(5,"Ross","Dawson","hymenaeos.Mauris@Namligulaelit.co.uk","7875 Et Avenue",59731),(9,"Ebony","Ashley","orci@tellusNunc.co.uk","P.O. Box 947, 9749 Metus. Avenue",20948),(10,"Lucy","Mckenzie","Curabitur.vel@ullamcorperviverra.org","Ap #182-524 Magnis St.",59097),(4,"Kimberley","Guthrie","mi.enim@necante.ca","Ap #296-2227 Ut St.",81100),(3,"Katelyn","Odom","Duis.at.lacus@Cumsociisnatoque.net","P.O. Box 121, 7421 Elit Avenue",33585),(10,"Caleb","Casey","mauris.blandit.mattis@mollisDuissit.edu","7605 Libero Avenue",75074);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (3,"Athena","Norris","mollis.vitae.posuere@loremfringilla.net","2822 Ultrices. Rd.",6922),(4,"Gay","Cooper","velit.in.aliquet@et.com","804-8454 Ullamcorper Av.",46538),(9,"Anthony","Frost","tempus.scelerisque.lorem@pharetra.com","9765 Neque. St.",80047),(9,"Priscilla","Baldwin","ipsum.Phasellus@nibhvulputate.org","Ap #587-7783 Adipiscing Road",90697),(3,"Dorian","Newton","Vivamus@CurabiturmassaVestibulum.com","Ap #715-6132 Ornare Road",17273),(10,"Flynn","Holloway","sodales.nisi.magna@lectusCum.co.uk","Ap #946-2313 Elementum, St.",19013),(1,"Blossom","Martin","massa.Mauris.vestibulum@sedfacilisisvitae.ca","521-8876 Libero Ave",70920),(4,"Gannon","Cantu","Duis.sit@id.co.uk","928-9577 Nascetur St.",21713),(1,"Kellie","Sanchez","fringilla.est@nunc.edu","P.O. Box 172, 2163 Metus. Ave",33873),(8,"Leah","Vaughan","facilisis.Suspendisse@vulputate.edu","462-2538 Ipsum Road",61125);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (1,"Maggy","Pugh","in.dolor.Fusce@sed.co.uk","273-6786 Posuere Road",70679),(2,"Rooney","Montgomery","Donec.luctus@quamafelis.ca","400-4223 Vulputate, Avenue",43690),(3,"Elton","Shelton","nec.quam.Curabitur@sagittislobortis.net","Ap #817-1118 Nisi. Avenue",32622),(10,"Ayanna","Allison","risus.Donec.egestas@vehicula.net","131-9996 Est Road",83112),(5,"Tana","Lawrence","luctus.aliquet.odio@pellentesque.com","P.O. Box 921, 2701 Eu, Avenue",72348),(9,"Echo","Knight","dictum.eu@afeugiat.com","6746 Aliquam Ave",43706),(4,"Rigel","Valentine","gravida.non.sollicitudin@ornare.edu","1992 Eu St.",1179),(3,"Holmes","Lynn","ante.ipsum.primis@velturpisAliquam.co.uk","Ap #401-7689 Et Rd.",90723),(7,"Kasper","Ellison","augue.ut@maurisa.com","611-8135 Vivamus Ave",64519),(9,"Alec","Bolton","natoque@seddolor.com","P.O. Box 412, 3813 Venenatis Street",22464);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (4,"Ulric","Dillard","suscipit.nonummy.Fusce@augue.ca","Ap #576-7047 Id St.",86108),(9,"Kasimir","Battle","sapien.cursus@ornare.ca","P.O. Box 355, 2711 Aliquet, Av.",88111),(2,"Graham","Sims","egestas.blandit.Nam@malesuadavelconvallis.ca","P.O. Box 470, 7845 Integer St.",9160),(10,"Lyle","Baker","aliquam.eu.accumsan@mauris.net","5023 Tempor Av.",63583),(10,"Elvis","Bruce","eu.eleifend.nec@nunc.org","234-2888 Phasellus Rd.",95011),(7,"Melvin","Roy","ultricies.ligula@ipsumdolor.ca","5713 Ligula Ave",12353),(8,"Lisandra","Dennis","orci.Ut@ac.net","P.O. Box 857, 1953 Ligula. Road",27335),(8,"Baxter","Christian","Nullam@loremegetmollis.ca","4309 Commodo Rd.",72439),(8,"Timon","Wallace","quis.lectus.Nullam@hendreritid.org","542-6089 Ac Avenue",21797),(9,"Mara","Riddle","nec.urna.suscipit@magnaet.co.uk","Ap #444-8730 Sapien Rd.",97099);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (10,"Shelby","Murphy","nonummy@Curabiturut.net","Ap #212-3339 Sodales Avenue",2829),(8,"Brynn","Chavez","urna.Nullam@nibh.co.uk","366-5034 Elementum, Av.",79190),(4,"Athena","Mooney","Pellentesque.habitant.morbi@netus.org","4234 Fringilla, Street",29901),(2,"Ahmed","Gill","malesuada.vel@ridiculus.co.uk","P.O. Box 119, 6356 Eu Rd.",22940),(9,"Brenden","Banks","tincidunt.tempus@vulputate.org","484-2713 Et Ave",64890),(5,"Nathan","Mays","mauris@Crasconvallisconvallis.co.uk","Ap #428-590 A, Road",15051),(8,"Zane","Walker","amet@malesuadaid.ca","6256 Mauris St.",23779),(6,"Kareem","Kelly","Aliquam.erat@nulla.com","914-3079 Ullamcorper. Rd.",18085),(7,"Xaviera","Camacho","odio.Nam.interdum@nonummyFuscefermentum.net","Ap #207-6097 Urna Road",58763),(4,"Jack","Pena","Duis@sit.co.uk","1146 Dui Avenue",8333);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (6,"Kyle","Bauer","Nulla.eu@pharetra.co.uk","P.O. Box 620, 2053 In St.",95761),(5,"Cheryl","Castro","feugiat.non.lobortis@afelisullamcorper.net","6121 Metus. St.",83095),(1,"Brendan","Houston","pellentesque.a.facilisis@fringillaeuismodenim.net","5534 Sed Ave",34264),(7,"Felix","Frederick","velit.Quisque.varius@erosNamconsequat.ca","Ap #859-7834 At St.",65934),(3,"Jonah","Bell","in@magnis.co.uk","Ap #870-3707 Integer Ave",32673),(7,"Maxwell","Ramirez","In.mi.pede@malesuadavelvenenatis.org","2754 Elit, Avenue",14753),(5,"Medge","Anthony","litora.torquent@consequatpurusMaecenas.edu","649-2302 Class Ave",30474),(3,"Jesse","Jimenez","tortor@semperegestasurna.org","Ap #133-871 Adipiscing Rd.",96460),(4,"Quintessa","Wyatt","gravida@sem.net","Ap #481-8703 Praesent Rd.",55115),(6,"Flynn","Mcdonald","fringilla@Cumsociis.net","Ap #486-146 Vestibulum Ave",29827);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (6,"Grant","Vargas","volutpat@rhoncusNullamvelit.net","Ap #591-9178 Volutpat St.",27988),(8,"Abel","Hinton","odio@acturpisegestas.co.uk","P.O. Box 372, 8406 Cras Avenue",61638),(2,"Stephen","Odonnell","nec.luctus@Donecporttitortellus.co.uk","701-2125 Suscipit, St.",72881),(1,"Zahir","Clemons","ut.lacus.Nulla@aliquamenimnec.co.uk","P.O. Box 287, 6218 Est, Av.",44967),(2,"Penelope","Reid","metus.Vivamus@Inmi.co.uk","648-318 Sem Avenue",9620),(7,"Carla","Porter","Aliquam.tincidunt.nunc@magna.edu","P.O. Box 508, 1789 Interdum. St.",16753),(10,"Marvin","Kline","a.auctor@purus.co.uk","312-9567 Magna. Ave",73375),(1,"Ronan","Carver","quam@Sedpharetrafelis.net","Ap #104-704 Consectetuer Av.",68399),(6,"Malachi","Hinton","at@sociisnatoquepenatibus.net","P.O. Box 329, 7374 Ornare. Rd.",53982),(6,"Alvin","Ochoa","amet@orciUt.edu","3255 Ligula. Av.",55831);
INSERT INTO `employee` (`company_id`,`name`,`surname`,`email`,`address`,`salary`) VALUES (4,"Nissim","Burke","nibh.Donec.est@accumsanlaoreet.co.uk","981-7662 Pharetra Rd.",70210),(4,"Hannah","Lucas","Integer.vitae.nibh@nislarcuiaculis.edu","Ap #610-7940 Auctor, Road",17768),(2,"Evelyn","Atkinson","sed.facilisis.vitae@Ut.com","858-1750 Cras St.",92220),(4,"Dieter","Frye","felis.ullamcorper@Etiam.com","800 Tincidunt Ave",85918),(7,"Ella","Sears","blandit.congue.In@sedorcilobortis.edu","P.O. Box 184, 408 Libero Rd.",55724),(6,"Oliver","Santana","ut@ullamcorpervelit.net","5973 Orci. Rd.",26442),(2,"Ocean","Ball","ornare@ornaretortorat.com","8766 Morbi Rd.",82539),(6,"Lionel","Duncan","Nam@nonummy.net","475-7949 Odio Road",91252),(6,"Steven","Garcia","risus.varius.orci@ascelerisque.net","P.O. Box 714, 2497 Sed St.",66659),(4,"Hasad","Crane","aliquet.lobortis@Cumsociis.edu","7963 Vitae Rd.",74454);

```

### Endpoints (curl)

```
POST /company
PUT /company/[id]
GET /company/all
GET /company/[id]
DELETE /company/[id]

POST /employee
PUT /employee/[id]
GET /employee/[id]
GET /employee/all
GET /employee/all/[company-id]
DELETE /company/[company-id]
```
### Docker (Postgres and PgAdmin4)

This project are using the Postgres database and the PgAdmin4 web client. Both are running in 
docker containers that is possible to start using the follow command:

``
docker-compose up
``

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
not work because in this case is connections between containers.

### Running

```
docker-compose up
mvn spring-boot:run
```

### Testing

`mvn clean test`

### Building

`mvn clean install `
