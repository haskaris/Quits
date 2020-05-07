package Modele;

import Global.Tools;
import Model.ReaderWriter;
import Model.Players.Player;
import Model.Players.AINormalPlayer;
import Model.Support.Board;
import java.awt.Color;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LecteurRedacteurTest {
    String pathTest = "TestJunit.save";
    int playerNumberTest = 2;
    int boardSizeTest = 5;
    String namePlayer0Test = "Default1";
    Color colorPlayer0Test = Color.BLUE;
    String namePlayer1Test = "Default2";
    Color colorPlayer1Test = Color.GREEN;
    int currentPlayerTest = 0;

    @Test
    @Order(1)
    void ecrisPartie() {
        try {
            Board board = new Board();
            Player tmpPlayer0 = new AINormalPlayer(namePlayer0Test, colorPlayer0Test);
            tmpPlayer0.setStartPoint(Tools.Direction.SO);
            Player tmpPlayer1 = new AINormalPlayer(namePlayer1Test, colorPlayer1Test);
            tmpPlayer1.setStartPoint(Tools.Direction.NE);
            board.addPlayer(tmpPlayer0);

            new ReaderWriter(pathTest).writeGame(board);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(2)
    void litPartie() {
            Board board = null;
            try {
                board = new ReaderWriter(pathTest).readGame();
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
            assertNotNull(board);
            assertNotNull(board.players);
            assertEquals(playerNumberTest,board.players.length);
            assertEquals(boardSizeTest, board.getGrid().length);
            assertEquals(currentPlayerTest, board.currentPlayer);
            assertEquals(namePlayer0Test, board.players[0].name);
            assertEquals(colorPlayer0Test, board.players[0].color);
            assertEquals(namePlayer1Test, board.players[1].name);
            assertEquals(colorPlayer1Test, board.players[1].color);
    }


}