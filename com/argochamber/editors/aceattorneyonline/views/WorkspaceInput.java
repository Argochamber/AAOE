/*
 * Ace Attorney Online Editor (AAOE) -  A Simple AAOE Case editor.
 * Copyright (C) Argochamber Interactive 2016
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.argochamber.editors.aceattorneyonline.views;

import com.argochamber.editors.aceattorneyonline.service.Controller;
import javax.swing.JFileChooser;

/**
 *
 * @author Pablo
 */
public class WorkspaceInput extends javax.swing.JFrame {
    
    /**
     * The controller's reference.
     */
    private final Controller controller;
    
    /**
     * Creates new form WorkspaceInput
     * @param c Controller's reference
     */
    public WorkspaceInput(Controller c) {
        this.controller = c;
        initComponents();
        
        //Location centrix
        this.errorDialog.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        errorDismiss = new javax.swing.JButton();
        pathPanel = new javax.swing.JFileChooser();
        pathInput = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        enterPath = new javax.swing.JButton();

        errorDialog.setTitle("ERROR!");
        errorDialog.setAlwaysOnTop(true);
        errorDialog.setLocation(new java.awt.Point(0, 0));
        errorDialog.setMinimumSize(new java.awt.Dimension(350, 120));
        errorDialog.setModal(true);
        errorDialog.setResizable(false);

        jLabel1.setText("The workspace that you have entered is NOT VALID!");

        errorDismiss.setText("Dismiss");
        errorDismiss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                errorDismissMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout errorDialogLayout = new javax.swing.GroupLayout(errorDialog.getContentPane());
        errorDialog.getContentPane().setLayout(errorDialogLayout);
        errorDialogLayout.setHorizontalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorDismiss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        errorDialogLayout.setVerticalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorDismiss)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pathPanel.setDialogTitle("Select a workspace...");
        pathPanel.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Select the game path");
        setAlwaysOnTop(true);
        setResizable(false);

        pathInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathInputActionPerformed(evt);
            }
        });

        browseButton.setText("Browse");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                browseButtonMouseReleased(evt);
            }
        });
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        enterPath.setText("Ok");
        enterPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                enterPathMouseReleased(evt);
            }
        });
        enterPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pathInput, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(enterPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterPath)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Useless
     * @param evt ???
     * @deprecated 
     */
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_browseButtonActionPerformed

    /**
     * Useless
     * @param evt ???
     * @deprecated 
     */
    private void pathInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathInputActionPerformed

    /**
     * Useless
     * @param evt ???
     * @deprecated 
     */
    private void enterPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterPathActionPerformed

    /**
     * When mouse gets released from "OK", it will check if all the data is correct.
     * @param evt the event that fired the action.
     */
    private void enterPathMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterPathMouseReleased
        
        //Checkout if the job can be done.
        this.controller.pathCheckAction(this.pathInput.getText(), this.errorDialog);
        
    }//GEN-LAST:event_enterPathMouseReleased

    private void errorDismissMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_errorDismissMouseReleased
        this.errorDialog.setVisible(false);
    }//GEN-LAST:event_errorDismissMouseReleased

    private void browseButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseReleased
        
        int code = this.pathPanel.showOpenDialog(this);
        
        if (code == JFileChooser.APPROVE_OPTION) {
            String file = this.pathPanel.getSelectedFile().getAbsolutePath();
            this.pathInput.setText(file);
            this.controller.setLastPath(file);
        }
        
    }//GEN-LAST:event_browseButtonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton enterPath;
    private javax.swing.JDialog errorDialog;
    private javax.swing.JButton errorDismiss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField pathInput;
    private javax.swing.JFileChooser pathPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Sets the path value.
     * @param get the path of the text field.
     */
    public void setPath(String get) {
        this.pathInput.setText(get);
    }
}
