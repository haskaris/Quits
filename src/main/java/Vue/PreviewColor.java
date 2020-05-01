/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Mathis
 */
public class PreviewColor extends JPanel {
    Color c;
    
    public PreviewColor(Color c) {
        super();
        this.c = c;
        this.setSize(new Dimension(200, 200));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(c);
      g.fillRect(0, 0, 200, 200);
    }
    
    public void updateColor(Color c) {
        this.c = c;
    }
}
