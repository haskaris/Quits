package Model.Players;

import Global.Tools.Direction;
import Model.Move;
import Model.ReaderWriter;
import Model.Support.Board;
import Model.Support.Marble;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    public String name;
    public Color color;
    private ArrayList<Marble> marbles;
    private Direction startPoint;
    
    /**
     * Constructeur
     * @param name
     * @param color
     */
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        marbles = new ArrayList<>();
    }
    
    /**
     * Constructeur
     * @param _nom
     * @param _couleur
     * @param p 
     */
    Player(String _nom, Color _couleur, Direction p){
        name = _nom;
        color = _couleur;
        marbles = new ArrayList<>();
        this.startPoint = p;
    }

    /**
     * Met le point de départ
     * @param p 
     */
    public void setStartPoint(Direction p) {
        this.startPoint = p;
    }
    
    /**
     * Retourne le point de départ du joueur
     * @return Direction
     */
    public Direction getStartPoint() {
        return this.startPoint;
    }
    
    /**
     * Ajoute une bille à la liste des billes du joueur
     * @param b 
     */
    public void addMarble(Marble b) {
        this.marbles.add(b);
    }
    
    /**
     * Ajoute une bille à la liste des billes du joueur 
     * @return Marble
     */
    public Marble addMarble() {
        Marble tmp = new Marble(this.color);
        this.marbles.add(tmp);
        return tmp;
    }
    
    /**
     * Retourne la liste des billes du joueur
     * @return 
     */
    public ArrayList<Marble> getMarbles() {
        return this.marbles;
    }
    
    /**
     * Enlève la bille de la liste des marbles du joueur
     * Retourne vrai si elle a été supprimée, faux sinon
     * @param b
     * @return 
     */
    public boolean removeMarble(Marble b) {
        return this.marbles.remove(b);
    }
    
    /**
     * Le joueur devra à ce moment jouer (lancer l'ia ou récupérer l'entrée utilisateur)
     * @param coups_possibles la liste des coups jouable par le joueur
     * @return le coup choisi par le joueur
     */
    abstract public Move Jouer(List<Move> coups_possibles) throws IOException;
     
    /**
     * S'imprime dans la sortie stream
     * @param stream
     * @throws IOException 
     */
    public void print(OutputStream stream) throws IOException {
        //On écrit le nom
        stream.write(this.name.getBytes());
        stream.write('\n');
        //On écrit la couleur
        stream.write(String.valueOf(this.color.getRGB()).getBytes());
        stream.write(" ".getBytes());
        //On écrit sa position
        stream.write(this.startPoint.toString().getBytes());
        stream.write('\n');
        for(Marble m : this.marbles) {
            m.print(stream);
            stream.write('/');
        }
        stream.write('\n');
    }
    
    /**
     * Créée un joueur à partir de l'entrée stream 
     * @param in_stream
     * @return Player
     * @throws IOException 
     */
    public static Player load(InputStream in_stream, Board board) throws IOException  {
        Player tmp = null;
        String playerType = ReaderWriter.readLine(in_stream);
        String playerName = ReaderWriter.readLine(in_stream);
        String[] dataPlayer = ReaderWriter.readLine(in_stream).split(" ");
        switch (playerType){
                case "HumanPlayer":
                    tmp = new HumanPlayer(playerName, new Color(Integer.parseInt(dataPlayer[0])));
                    break;
                case "DistantPlayer":
                    System.out.println("Une partie comprenant des joueurs distants ne pout pas être restaurée");
                    //tmp = new DistantPlayer(dataPlayer[1], new Color(Integer.parseInt(dataPlayer[2])));
                    //break;
                    return null;
                case "AIEasyPlayer":
                    tmp = new AIEasyPlayer(playerName, new Color(Integer.parseInt(dataPlayer[0])), board);
                    break;
                case "AINormalPlayer":
                    tmp = new AINormalPlayer(playerName, new Color(Integer.parseInt(dataPlayer[0])), board);
                    break;
                case "AIHardPlayer":
                    tmp = new AIHardPlayer(playerName, new Color(Integer.parseInt(dataPlayer[0])), board);
                    break;
            }
        tmp.setStartPoint(Direction.valueOf(dataPlayer[1]));
        return tmp;
    }
}
