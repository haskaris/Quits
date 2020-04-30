package Modele;

import Global.Configuration;
import Global.Properties;
import Modele.Joueurs.Joueur;
import Modele.Joueurs.JoueurHumain;
import Modele.Joueurs.JoueurIAFacile;
import Modele.Support.Bille;
import Modele.Support.Plateau;
import Vue.InterfaceGraphique;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;

public class GameManager {
    /**
     * Les actions raccourcis claviers (elle viennent du Sokoban). A voir si on garde ou non
     */
    public static KeyListener GameKeyListener=new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {}

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            // if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) interfacegraphique.toggleFullScreen();

            // if (keyEvent.getKeyCode() == KeyEvent.VK_F11) interfacegraphique.toggleAnimation();

            if (keyEvent.getKeyCode() == KeyEvent.VK_Q)
                Exit();

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {}
    };

    /**
     * Ici la liste des Objets accessibles aux autres parties du jeu
     */
    public static Plateau plateau;
    public static InterfaceGraphique interfacegraphique;
    public static Joueur[] joueurs = new Joueur[4];
    public static Historique historique;
    public static int joueurcourant ;

    /**
     * Permet d'initialiser une partie. Les parametres de la partie sont definies dans Configuration
     */
    public static void InstanceGame(){
        plateau = new Plateau((Integer)Configuration.Lis("Joueurs"),(Integer)Configuration.Lis("Taille"));

        for (int i = 0; i < (Integer)Configuration.Lis("Joueurs"); i++) {
                joueurs[i] = new JoueurIAFacile("Default",i);
        }

        interfacegraphique = new InterfaceGraphique();
        historique = new Historique();
        joueurcourant = 0;

        LecteurRedacteur.AffichePartie(plateau);
        JouerTour();
    }

    /**
     *  Joue les tours de la partie. S'arrete à la fin
     */
    public static void JouerTour(){
        while(FinTour()){
            List<Coup> coupspossible = new CalculateurCoup(joueurcourant,plateau.BillesJoueur(joueurcourant)).CoupsPossible();
            Coup coup = joueurs[joueurcourant].Jouer(coupspossible);
            historique.Faire(coup);
            LecteurRedacteur.AffichePartie(plateau);
        }
    }

    /**
     * Clot un tour. Verifie les conditions de victoire et passe au joueur suivant
     */
    private static boolean FinTour(){
        boolean estfini = true;
        for (Bille bille:plateau.BillesJoueur(joueurcourant)) {
            if(!bille.EstSortie())
                estfini = false;
        }

        if(estfini){
            System.out.println("Joueur " + joueurcourant + " a gagné");
            return false;
        }

        joueurcourant++;
        if(joueurcourant>=(Integer)Configuration.Lis("Joueurs"))
            joueurcourant =0;
        return true;
    }



    /**
     * Charge une partie
     */
    public static void ChargerPartie(){
        try {
            LecteurRedacteur lr = new LecteurRedacteur("default.save");
            lr.LitPartie();
            plateau = lr.plateau;
            joueurs = lr.joueurs;
            joueurcourant = lr.joueurcourant;
        }
        catch (Exception e){
            System.out.println("Erreur de chargement de la partie");
        }

        interfacegraphique = new InterfaceGraphique();
        historique = new Historique();

        JouerTour();
    }

    /**
     * Enregistre une partie
     */
    public static void EnregistrerPartie(){
        try {
            new LecteurRedacteur("default.save",plateau,joueurs,joueurcourant).EcrisPartie();
        }
        catch (Exception e){
            System.out.println("Erreur d'enregistrement de la partie");
        }

    }


    /**
     * Fermeture du jeu
     */
    public static void Exit(){
        try {
            Properties.Store();
        } catch (IOException e) {
            Configuration.logger().severe("Erreur d'ecriture des Properties");
            System.exit(1);
        }
        System.exit(0);
    }



}
