package com.sozkan.testinium_web_service.util;

import java.util.List;
import java.util.Random;

public class Util {
    public static final String KEY = "KEY";
    public static final String TOKEN = "TOKEN";
    public static final String BASE_URI = "https://api.trello.com/";
    public static final String CREATE_BOARD_ENDPOINT = "/1/boards/";
    public static final String CREATE_CARD_ENDPOINT = "/1/cards/";
    public static final String GET_CARD_IN_LIST = "/1/lists/{id}/cards";
    public static final String UPDATE_CARD_ENDPOINT = "/1/cards/{id}";
    public static final String DELETE_CARD_ENDPOINT = "/1/cards/{id}";
    public static final String DELETE_BOARD_ENDPOINT = "/1/boards/{id}";

    public static String getRandomItem(List<String> list) {
        Random rand = new Random();
        String randomItem = list.get(rand.nextInt(list.size()));
        return randomItem;

    }
}