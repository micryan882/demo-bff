package com.edw;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/**
 * <pre>
 *     com.edw.LoanTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 15 Mar 2023 10:48
 */
@QuarkusTest
public class LoanTest {

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

    @Test
    public void testMediumRisk() {
        given()
                .body("{\"loan\" : [{\"age\":30, \"salary\":900}]}")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/get-risk")
                .then()
                .statusCode(200).log().all()
                .body("risk", hasItem("Medium"));

        given()
                .body("{\"loan\" : [{\"age\":19, \"salary\":1200}]}")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/get-risk")
                .then()
                .statusCode(200).log().all()
                .body("risk", hasItem("Medium"));
    }

    @Test
    public void testLowRisk() {
        given()
                .body("{\"loan\" : [{\"age\":28, \"salary\":2000}]}")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/get-risk")
                .then()
                .statusCode(200).log().all()
                .body("risk", hasItem("Low"));
    }

    @Test
    public void testAllRisk() {
        given()
                .body("{" +
                            "\"loan\" : [" +
                                "{\"age\":28, \"salary\":2000}," +
                                "{\"age\":17, \"salary\":900}," +
                                "{\"age\":19, " +
                                "\"salary\":1200}" +
                            "]" +
                        "}")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post("/get-risk")
                .then()
                .statusCode(200).log().all()
                .body("risk", hasItems("Low", "Medium", "High"));
    }

}
