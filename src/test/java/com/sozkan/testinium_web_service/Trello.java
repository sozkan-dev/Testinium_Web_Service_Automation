package com.sozkan.testinium_web_service;

import com.sozkan.testinium_web_service.pages.Board;
import com.sozkan.testinium_web_service.pages.Card;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.Test;

public class Trello {

    @Test
    public void trelloTest(){
        Board board = new Board();
        board.createBoard();

        Card card = new Card();
        card.createCard("New Card 1",board.getLists().get(0));
        card.createCard("New Card 2", board.getLists().get(0));
        card.updateCard("Updated Card Name","Demo1");
        card.deleteAllCards();

        board.deleteBoard();
    }
}