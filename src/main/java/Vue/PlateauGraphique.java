/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Paterns.Observateur;
import java.awt.*;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Mathis
 */
public abstract class PlateauGraphique extends JComponent implements Observateur {
    Graphics2D drawable;
    
    protected ImageQuits lisImage(InputStream in) throws IOException {
        return new ImageQuits(ImageIO.read(in));
    }
    
    protected void tracer(ImageQuits i, int x, int y, int l, int h) {
        drawable.drawImage(i.image(), x, y, l, h, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;

        // On efface tout
        drawable.clearRect(0, 0, largeur(), hauteur());
        tracerNiveau();
    }
    
    int hauteur() {
        return getHeight();
    }

    int largeur() {
        return getWidth();
    }
    
    @Override
    public void miseAJour() {
        repaint();
    }
    
    // tracerNiveau est la partie indépendante de Swing du dessin qui se trouve dans le descendant
    abstract void tracerNiveau();
    
    abstract int hauteurCase();
    abstract int largeurCase();
    
    // Méthodes pour les animations
    // décale un des éléments d'une fraction de case (pour les animations)
    public abstract void decale(int l, int c, double dl, double dc);
    // Changements du pousseur
    public abstract void metAJourDirection(int dL, int dC);
    public abstract void changeEtape();
}