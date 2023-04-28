package com.sozkan.testinium_web_service.pages;

import com.sozkan.testinium_web_service.util.Util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Card {


    public void createCard(String name, String listId) {
        RestAssured.baseURI = Util.BASE_URI;
        given().log()
               .all()
               .queryParam("key", Util.KEY)
               .queryParam("token", Util.TOKEN)
               .queryParam("name", name)
               .queryParam("idList", listId)
               .header("Content-Type", "application/json")
               .when()
               .post(Util.CREATE_CARD_ENDPOINT)
               .then()
               .log()
               .all()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON);
    }

    public List<String> getCardList() {
        Board board = new Board();
        Response listsResponse = given()
                .queryParam("key", Util.KEY)
                .queryParam("token", Util.TOKEN)
                .pathParams("id", board.getLists()
                                       .get(0))
                .when()
                .get(Util.GET_CARD_IN_LIST)
                .then()
                .extract()
                .response();

        List<String> ids = listsResponse.path("id");
        System.out.println("Card IDS: " + ids);
        return ids;
    }
    public void updateCard(String name, String description){
        RestAssured.baseURI = Util.BASE_URI;
        given().log()
               .all()
               .queryParam("key", Util.KEY)
               .queryParam("token", Util.TOKEN)
               .pathParams("id", Util.getRandomItem(getCardList()))
               .queryParam("desc", description)
                .queryParam("name",name)
               .header("Content-Type", "application/json")
               .when()
               .put(Util.UPDATE_CARD_ENDPOINT)
               .then()
               .log()
               .all()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON);
    }

    public void deleteAllCards(){
        List<String> cardList = getCardList();
        RestAssured.baseURI = Util.BASE_URI;
        for(String cardId: cardList) {
            given().log()
                   .all()
                   .queryParam("key", Util.KEY)
                   .queryParam("token", Util.TOKEN)
                   .pathParams("id", cardId)
                   .header("Content-Type", "application/json")
                   .when()
                   .delete(Util.DELETE_CARD_ENDPOINT)
                   .then()
                   .log()
                   .all()
                   .assertThat()
                   .statusCode(200)
                   .contentType(ContentType.JSON);
        }
    }
}