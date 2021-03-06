package Model.Players;

import Global.Tools;
import Global.Tools.PlayerStatus;
import Model.Move;
import Model.MoveCalculator;
import Model.Support.Board;
import Model.Support.Marble;
import Model.Support.Tile;
import View.ViewBoard;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.List;

public class HumanPlayer extends Player {

    private PlayerStatus status;

    public HumanPlayer(String name, Color color) {
        super(name, color);
        status = PlayerStatus.MarbleSelection;

    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    @Override
    public Move Jouer(List<Move> coups_possibles) {

        return coups_possibles.get(0);
    }

    /**
     * S'imprime dans la sortie stream
     *
     * @param stream
     * @throws IOException
     */
    @Override
    public void print(OutputStream stream) throws IOException {
        stream.write("HumanPlayer".getBytes());
        stream.write('\n');
        super.print(stream);
    }

    public void Jouer(Board board, int column, int line) {
        
        Tile[][] grid = board.getGrid();

        //On clique sur la grille, s'il y a une bille de notre couleur
        if ((column >= 0 && column <= 4 && line >= 0 && line <= 4) && grid[column][line].hasMarble()
                && color.equals(grid[column][line].getMarble().getColor())) {
            
            //Si la bille sélectionnée n'a pas déjà été sélectionnée
            if (!grid[column][line].getMarble().equals(board.selectedMarble)) {
                board.clearShifts();
                board.resetAvailableTiles();
                board.availableTiles[column][line] = 1;
                board.selectedMarble = grid[column][line].getMarble();
                board.getMediator().updateSelectedMarble();

                List<Move> possibleMovesWithSource = new MoveCalculator(board).possibleMovesWithSource(column, line);
                
                //Pour chaque mouvement qui est un mouvement de billes
                possibleMovesWithSource.stream().filter((m) -> (!m.isShift())).forEachOrdered((m) -> {
                    Point pos = m.getPosition();
                        
                    Point dir = m.getCoordinatesDirection();

                    int x = pos.x + dir.x;
                    int y = pos.y + dir.y;
                    board.availableTiles[x][y] = 2;
                });

                //On passe en choix d'action
                this.setStatus(Tools.PlayerStatus.ActionSelection);
            } else {
                board.clearSelectedMarble();
                board.resetAvailableTiles();
                board.listAllShifts();
                
                //On passe en choix de billes
                this.setStatus(Tools.PlayerStatus.MarbleSelection);
            }
        //Soit il cherche en dehors de la grille (déplacement de tuiles)
        //Soit il déplace la bille sélectionnée
        //Soit il a cliqué dans un endroit non valide 
        } else {
            //The player selects a good move, else they are put back to MarbleSelection status
            if (!(column >= 0 && column <= 4 && line >= 0 && line <= 4)) {
                //We clicked on an arrow so we shift the rows or columns if its a valid move

                Tools.Direction d = Tools.Direction.NODIR;
                Point anchorSource = null;
                if (column == -1 && line >= 0 && line <= 4) {
                    //Shifting towards East
                    d = Tools.Direction.E;
                    anchorSource = new Point(0, line);

                } else if (column == 5 && line >= 0 && line <= 4) {
                    //Shifting towards West
                    d = Tools.Direction.W;
                    anchorSource = new Point(4, line);
                } else if (line == -1 && column >= 0 && column <= 4) {
                    //Shifting towards South
                    d = Tools.Direction.S;
                    anchorSource = new Point(column, 0);
                } else if (line == 5 && column >= 0 && column <= 4) {
                    //Shifting towards North
                    d = Tools.Direction.N;
                    anchorSource = new Point(column, 4);
                }
                
                Move move = new Move(
                        new Point(Tools.findAppropriateCoordinatesForTileShifts(column),
                                Tools.findAppropriateCoordinatesForTileShifts(line)), 
                                d/*, this*/);
                
                
                if (moveExists(move, board.allPotentialShifts)) {
                    board.resetAvailableTiles();
                    board.getHistory().doMove(move);
                    
                    this.setStatus(Tools.PlayerStatus.MarbleSelection);
                    board.endTurn();
                }
                
                //Si on a une bille déjà sélectionné
                //On ne peut pas déplacer une rangée
                if (board.selectedMarble != null) {
                    board.clearSelectedMarble();
                    board.listAllShifts();
                }
                
            } else if (this.getStatus() == Tools.PlayerStatus.ActionSelection) {
                if (board.availableTiles[column][line] == 2) {
                    //That's a good action, we can move the marble to the new position
                    Point pos = board.selectedMarble.getPosition();
                    
                    board.resetAvailableTiles();

                    board.getHistory().doMove(
                            new Move(board.selectedMarble,
                                    Tools.PointToDir(Tools.PointToPointDiff(pos, new Point(column, line)))/*,
                                    this*/)
                    );

                    this.setStatus(Tools.PlayerStatus.MarbleSelection);
                    board.endTurn();
                    board.clearSelectedMarble();
                } else {
                    //That's not a good action, we get back to MarbleSelection, but we don't change players
                    this.setStatus(Tools.PlayerStatus.MarbleSelection);
                    board.resetAvailableTiles();
                    board.clearSelectedMarble();
                    board.listAllShifts();
                }
            }
        }
    }

    /**
     * Permet de savoir si un mouvement existe dans une liste de mouvements
     * @param myM Move - Mouvement à étudier
     * @param allMoves ArrayList<Move> - Liste des mouvements à étudier
     * @return Boolean - Vrai si le mouvement est dans la liste des mouvemens
     */
    private boolean moveExists(Move myM, ArrayList<Move> allMoves) {
        boolean found = false;

        for (Move m : allMoves) {
            if (m.isEqual(myM)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
