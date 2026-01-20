# Quarkus and Rule Engine

A sample Java project that involved Quarkus, Kogito, and Drools to display the capability of `Rule Engine` on top of Quarkus. It provides a rest API endpoint with a specific JSON format for request and response.

## Build
```
$ mvn clean package -s settings.xml
```

## Run
```
$ java -jar .\target\quarkus-app\quarkus-run.jar
```

## Test
```
$ curl  -X POST http://localhost:8080/get-risk  \
    -H 'content-type: application/json'  \
    -H 'accept: application/json'   \
    -d '{"loan" : [{"age":17, "salary":900}]}'
    
[{"age":17,"salary":900.0,"risk":"High"}]
```

```
$ curl  -X POST http://localhost:8080/get-risk  \
    -H 'content-type: application/json'  \
    -H 'accept: application/json'   \
    -d '{"loan" : [{"age":17, "salary":900}, {"age":39,"salary":1900.0}]}'

[{"age":39,"salary":1900.0,"risk":"Low"},{"age":17,"salary":900.0,"risk":"High"}]    
```

## Unit Tests
```java
@Test
public void testHighRisk() {
        given()
        .body("{\"loan\" : [{\"age\":17, \"salary\":900}]}")
        .contentType(ContentType.JSON)
        .log().all()
        .when()
        .post("/get-risk")
        .then()
        .statusCode(200).log().all()
        .body("risk", hasItem("High"));
        }
```

## SWAGGER
http://localhost:8090/q/swagger-ui/#

## Query su DB:
docker compose exec data-index-db psql -U kogito -d kogito_dataindex -c "SELECT * FROM tasks;"
docker compose exec data-index-db psql -U kogito -d kogito_dataindex -c "SELECT table_name FROM information_schema.tables WHERE table_schema='public';"

Se fatto girare in locale:
creare db  mydb con user quarkus e password quarkus 
creare database kogito_dataindex con user kogito e password kogito
Data Index: tabelle come tasks, processes, nodes, jobs, ecc. create da Quarkus/Hibernate al primo avvio del servizio Data Index.
commentare/decommentare: QUARKUS_DATASOURCE_JDBC_URL e QUARKUS_DATASOURCE_JDBC_URL sia sotto environment che sotto data index

##AVVIO DOCKER se db è interno al container:
docker compose up -d data-index-db kafka
docker compose up -d data-index
docker compose up -d app task-console management-console


