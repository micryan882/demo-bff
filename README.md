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

## FUNZIONAMENTO