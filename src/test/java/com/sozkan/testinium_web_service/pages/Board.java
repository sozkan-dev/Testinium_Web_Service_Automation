package com.sozkan.testinium_web_service.pages;

import com.sozkan.testinium_web_service.util.Util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Board {
    public String boardName = "New Board 1";

    public void createBoard() {
        RestAssured.baseURI = Util.BASE_URI;

        given().log()
               .all()
               .queryParam("key", Util.KEY)
               .queryParam("token", Util.TOKEN)
               .queryParam("name", boardName)
               .header("Content-Type", "application/json")
               .when()
               .post(Util.CREATE_BOARD_ENDPOINT)
               .then()
               .log()
               .all()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON);
    }

    public String getBoardId() {
        RestAssured.baseURI = Util.BASE_URI;
        String boardId = given().when()
                                .get("/1/members/me/boards?key=" + Util.KEY + "&token=" +
                                        Util.TOKEN)
                                .then()
                                .extract()
                                .jsonPath()
                                .getString("find {it.name == '" + boardName + "'}.id");
        System.out.println("Board ID: " + boardId);
        return boardId;
    }

    public List<String> getLists() {
        RestAssured.baseURI = Util.BASE_URI;
        Response listsResponse = given()
                .queryParam("key", Util.KEY)
                .queryParam("token", Util.TOKEN)
                .pathParams("id", getBoardId())
                .when()
                .get("/1/boards/{id}/lists")
                .then()
                .extract()
                .response();

        List<String> ids = listsResponse.path("id");
        return ids;
    }

    public void deleteBoard() {
        RestAssured.baseURI = Util.BASE_URI;
        given()
                .queryParam("key", Util.KEY)
                .queryParam("token", Util.TOKEN)
                .pathParams("id", getBoardId())
                .when()
                .delete(Util.DELETE_BOARD_ENDPOINT)
                .then()
                .extract()
                .response()
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

}