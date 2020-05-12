/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Dialogs.RulesDialog;
import View.Filters.SaveFilter;
import Controleur.Mediator;
import Global.Tools;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Mathis
 */
public class InitGame extends javax.swing.JPanel {

    Mediator mediator;
    Tools.GameMode gameMode;
    JFrame frame;
    private final JFileChooser fc;
    
    /**
     * Creates new form InitGameAuto
     * @param frame
     * @param mediateur
     */
    public InitGame(JFrame frame, Mediator mediateur) {
        initComponents();
        this.gameMode = Tools.GameMode.TwoPlayersFiveBalls;
        this.editPlayers.add(new EditPlayer("JoueurA", Color.BLUE));
        this.editPlayers.add(new EditPlayer("JoueurB", Color.RED));
        this.mediator = mediateur;
        this.frame = frame;
        
        this.fc = new JFileChooser();
        this.fc.setAcceptAllFileFilterUsed(false);
        this.fc.setFileFilter(new SaveFilter());
        
        this.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonRules = new javax.swing.JButton();
        labelGameMode = new javax.swing.JLabel();
        ButtonPlay = new javax.swing.JButton();
        gameModeList = new javax.swing.JComboBox<>();
        editPlayers = new javax.swing.JPanel();
        loadButton = new javax.swing.JButton();

        buttonRules.setText("Règles");
        buttonRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRulesActionPerformed(evt);
            }
        });

        labelGameMode.setText("Mode de jeu");

        ButtonPlay.setText("Jouer");
        ButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPlayActionPerformed(evt);
            }
        });

        gameModeList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2 joueurs 5 billes", "2 joueurs 3 billes", "4 joueurs" }));
        gameModeList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gameModeListItemStateChanged(evt);
            }
        });

        editPlayers.setLayout(new javax.swing.BoxLayout(editPlayers, javax.swing.BoxLayout.Y_AXIS));

        loadButton.setText("Charger");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonRules)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelGameMode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gameModeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRules)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGameMode)
                            .addComponent(gameModeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(editPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonPlay)
                    .addComponent(loadButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void gameModeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gameModeListItemStateChanged
        this.editPlayers.removeAll();
            switch(this.gameModeList.getSelectedIndex()) {
                case 0:
                    this.editPlayers.add(new EditPlayer("JoueurA", Color.BLUE));
                    this.editPlayers.add(new EditPlayer("JoueurB", Color.RED));
                    this.gameMode = Tools.GameMode.TwoPlayersFiveBalls;
                    break;
                case 1:
                    this.editPlayers.add(new EditPlayer("JoueurA", Color.BLUE));
                    this.editPlayers.add(new EditPlayer("JoueurB", Color.RED));
                    this.gameMode = Tools.GameMode.TwoPlayersThreeBalls;
                    break;
                case 2:
                    this.editPlayers.add(new EditPlayer("JoueurA", Color.BLUE));
                    this.editPlayers.add(new EditPlayer("JoueurB", Color.RED));
                    this.editPlayers.add(new EditPlayer("JoueurC", Color.YELLOW));
                    this.editPlayers.add(new EditPlayer("JoueurD", Color.GREEN));
                    this.gameMode = Tools.GameMode.FourPlayersFiveBalls;
                    break;
            }
        this.updateUI();
    }//GEN-LAST:event_gameModeListItemStateChanged

    private void ButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPlayActionPerformed
        this.mediator.initGame(this.gameMode);
    }//GEN-LAST:event_ButtonPlayActionPerformed

    private void buttonRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRulesActionPerformed
        RulesDialog rulesDialog = new RulesDialog(new javax.swing.JFrame(), true);
        rulesDialog.setTitle("Règles du Quits");
        rulesDialog.setVisible(true);
    }//GEN-LAST:event_buttonRulesActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.mediator.loadGame(file.getName());
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    public ArrayList<EditPlayer> getEditsPlayers() {
        ArrayList<EditPlayer> tmp = new ArrayList<>();
        //Je récupère tous le contenu de mon panel editPlayers
        //Il ne devrait y avoir que des EditPlayers
        Component[] components = this.editPlayers.getComponents();
        for (Component c: components) {
            //Si le component récupéré est un EditPlayer
            if (c.getClass().equals(EditPlayer.class)) {
                //Je l'ajoute à ma liste
                tmp.add((EditPlayer)c);
            }
        }
        return tmp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonPlay;
    private javax.swing.JButton buttonRules;
    private javax.swing.JPanel editPlayers;
    private javax.swing.JComboBox<String> gameModeList;
    private javax.swing.JLabel labelGameMode;
    private javax.swing.JButton loadButton;
    // End of variables declaration//GEN-END:variables
}
