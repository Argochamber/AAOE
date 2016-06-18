/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.editors.aceattorneyonline.views;

import com.argochamber.editors.aceattorneyonline.game.Actor;
import com.argochamber.editors.aceattorneyonline.game.Evidence;
import com.argochamber.editors.aceattorneyonline.game.Scene;
import com.argochamber.editors.aceattorneyonline.game.Workspace;
import com.argochamber.editors.aceattorneyonline.service.Controller;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class SceneView extends javax.swing.JPanel {

    /**
     * Controller's reference.
     */
    private final Controller controller;

    /**
     * Scene reference.
     */
    private final Scene scene;

    /**
     * Creates new form SceneView
     *
     * @param controller
     * @param scene
     */
    public SceneView(Controller controller, Scene scene) {
        this.controller = controller;
        this.scene = scene;
        initComponents();
        controller.getAvailableBackgrounds().stream().forEach(bg -> {
            this.backgroundSelect.addItem(bg);
        });
        this.backgroundSelect.setSelectedItem(scene.getBackground());
        this.sceneNameInput.setText(this.scene.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JTabbedPane();
        charList = new javax.swing.JScrollPane();
        charPane = new javax.swing.JPanel();
        evList = new javax.swing.JScrollPane();
        evPane = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        backgroundSelect = new javax.swing.JComboBox<>();
        deleteScene = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        sceneNameInput = new javax.swing.JTextField();

        setLayout(new javax.swing.OverlayLayout(this));

        charList.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        charList.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        charPane.setLayout(new javax.swing.BoxLayout(charPane, javax.swing.BoxLayout.PAGE_AXIS));
        charList.setViewportView(charPane);

        mainPanel.addTab("Characters", charList);

        evList.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        evList.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        evPane.setLayout(new javax.swing.BoxLayout(evPane, javax.swing.BoxLayout.PAGE_AXIS));
        evList.setViewportView(evPane);

        mainPanel.addTab("Evidences", evList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Background:");

        backgroundSelect.setEnabled(false);
        backgroundSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundSelectActionPerformed(evt);
            }
        });

        deleteScene.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        deleteScene.setForeground(new java.awt.Color(153, 0, 0));
        deleteScene.setText("DELETE");
        deleteScene.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteSceneMouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Name:");

        sceneNameInput.setEditable(false);
        sceneNameInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sceneNameInputMouseReleased(evt);
            }
        });
        sceneNameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sceneNameInputKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backgroundSelect, 0, 267, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteScene)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sceneNameInput)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backgroundSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sceneNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(deleteScene)
                .addContainerGap())
        );

        mainPanel.addTab("Options", jPanel1);

        add(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSceneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSceneMouseReleased
        // This handles the delete request.

        int n = JOptionPane.showConfirmDialog(
                this,
                "Are you sure? THIS CANNOT BE UNDONE!",
                "Delete scene",
                JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            if (!this.controller.deleteScene(this.scene)) {
                System.err.println("There was an error during the deletion!");
                //custom title, error icon
                JOptionPane.showMessageDialog(this,
                        "Could not delete your file for some reason.",
                        "Deletion error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_deleteSceneMouseReleased

    private void sceneNameInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sceneNameInputKeyTyped

    }//GEN-LAST:event_sceneNameInputKeyTyped

    private void sceneNameInputMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sceneNameInputMouseReleased
        String newName = JOptionPane.showInputDialog("Enter a new name:");
        this.sceneNameInput.setText(newName);
        this.controller.renameScene(this.scene, this.sceneNameInput.getText());
    }//GEN-LAST:event_sceneNameInputMouseReleased

    private void backgroundSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundSelectActionPerformed
        // An action has been performed, register the state.
        //this.scene.setBackground((String)this.backgroundSelect.getSelectedItem());
    }//GEN-LAST:event_backgroundSelectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> backgroundSelect;
    private javax.swing.JScrollPane charList;
    private javax.swing.JPanel charPane;
    private javax.swing.JButton deleteScene;
    private javax.swing.JScrollPane evList;
    private javax.swing.JPanel evPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JTextField sceneNameInput;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Adds actor data to the view.
     * @param actor
     * @param workspace 
     */
    public void addActor(Actor actor, Workspace workspace) {
        ActorView aview = new ActorView(actor, this.scene, this.controller);
        this.charPane.add(aview);
        this.charPane.revalidate();
    }
    
    /**
     * Adds the evidence.
     * @param evi
     * @param evList 
     */
    public void addEvidence(Evidence evi, List<String> evList) {
        EvidenceView eviview = new EvidenceView(evi, evList, this.scene, controller);
        this.evPane.add(eviview);
        this.evPane.revalidate();
    }

    /**
     * Generate an actor list from the view's data.
     * @return 
     */
    public List<Actor> getActorList() {
        List<Actor> actors = Arrays.asList(this.charPane.getComponents()).stream()
                .map(comp -> ((ActorView)comp))
                .map(ActorView::asActor)
                .collect(Collectors.toList());
        return actors;
    }
    
    /**
     * Updates the graphical data.
     * @param actors 
     */
    void updateActorList(List<Actor> actors) {
        this.charPane.removeAll();
        actors.stream().forEach(actor -> this.charPane.add(new ActorView(actor, this.scene, this.controller)));
    }
    
    /**
     * Performs an update of the graphical data.
     * @param evidences 
     * @param ref
     */
    void updateEvidenceList(List<Evidence> evidences, List<String> ref) {
        this.evPane.removeAll();
        evidences.stream().forEach(evi -> this.evPane.add(new EvidenceView(evi, ref, this.scene, this.controller)));
    }

    /**
     * Getter for the scene data reference.
     * @return 
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Retrieves a list of evidences.
     * @return 
     */
    public List<Evidence> getEvidences() {
        List<Evidence> evidences = Arrays.asList(this.evPane.getComponents()).stream()
                .map(comp -> (EvidenceView)comp)
                .map(EvidenceView::asEvidence)
                .collect(Collectors.toList());
        return evidences;
    }
    
}
