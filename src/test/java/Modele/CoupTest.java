package Modele;

import Global.Tools;
import Model.*;
import Model.Support.*;
import Model.Players.*;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoupTest {
    Board board;
    Marble marble;
    Player player;

    @BeforeEach
    public void init(){
        board = new Board();
        player = new AIEasyPlayer("default", Color.BLUE, board);
        player.setStartPoint(Tools.Direction.SW);
        board.addPlayer(player);
        marble = player.addMarble();
        board.getGrid()[2][2].addMarble(marble);
    }

    @Test
    public void TestCoup() {
        Move c = new Move(marble, Tools.Direction.NW/*, player*/);
        c.perform(board);
        assertFalse(board.getGrid()[2][2].hasMarble());
        assertTrue(board.getGrid()[1][1].hasMarble());
        c.cancel(board);
        assertFalse(board.getGrid()[1][1].hasMarble());
        assertTrue(board.getGrid()[2][2].hasMarble());

        System.out.println("Coup OK");
    }

    @Test
    public void TestHistorique() {
        History historique = new History(board);
        Move c1 = new Move(marble.getPosition(), Tools.Direction.NW);
        historique.doMove(c1);
        Move c2 = new Move(marble.getPosition(), Tools.Direction.SW);
        historique.doMove(c2);
        historique.undo();
        historique.redo();
        historique.undo();
        assertTrue(board.getGrid()[1][1].hasMarble());
        System.out.println("Historique OK");
    }

    @Test
    public void TestEntreeController() throws IOException {
        MoveCalculator mc = new MoveCalculator(board);
        List<Move> coupspossible = mc.possibleMoves();
        //LecteurRedacteur.AffichePartie(board);
        board.getHistory().doMove(coupspossible.get(0));
        //LecteurRedacteur.AffichePartie(board);
        coupspossible = new MoveCalculator(board).possibleMoves();
        board.getHistory().doMove(player.Jouer(coupspossible));
        //LecteurRedacteur.AffichePartie(board);
    }


}

