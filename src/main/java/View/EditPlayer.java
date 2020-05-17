/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import View.ColorPicker.ColorPicker;
import Global.Tools;
import java.awt.Color;

/**
 *
 * @author Mathis
 */
public class EditPlayer extends javax.swing.JPanel {
    
    public String playerName;
    public Color playerColor;
    public Tools.AILevel aiLevel = Tools.AILevel.Player;
    public ColorPicker colorPicker;

    /** Creates new form EditPlayerAuto */
    public EditPlayer(String playerName, Color playerColor) {
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.colorPicker = new ColorPicker(this.playerColor);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        aiLevelList = new javax.swing.JComboBox<>();
        jButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText(this.playerName);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 200, -1));

        aiLevelList.setMaximumRowCount(4);
        aiLevelList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Humain", "Ordinateur facile", "Ordinateur normal", "Ordinateur difficile" }));
        aiLevelList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                aiLevelListItemStateChanged(evt);
            }
        });
        add(aiLevelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 13, -1, -1));

        jButton.setBackground(this.playerColor);
        jButton.setMargin(new java.awt.Insets(14, 14, 14, 14));
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });
        add(jButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChoiceGameMode/startscreen_background.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionPerformed
        this.colorPicker.showDialog(this);
    }//GEN-LAST:event_jButtonActionPerformed

    private void aiLevelListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aiLevelListItemStateChanged
        switch(this.aiLevelList.getSelectedItem().toString()) {
            case "Humain":
                aiLevel = Tools.AILevel.Player;
                break;
            case "Ordinateur facile":
                aiLevel = Tools.AILevel.Easy;
                break;
            case "Ordinateur normal":
                aiLevel = Tools.AILevel.Medium;
                break;
            case  "Ordinateur difficile":
                aiLevel = Tools.AILevel.Hard;
                break;
        }
    }//GEN-LAST:event_aiLevelListItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        this.playerName = jTextField1.getText();
    }//GEN-LAST:event_jTextField1KeyReleased

    public void updateButtonColor(Color c) {
        this.jButton.setBackground(c);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> aiLevelList;
    private javax.swing.JButton jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
