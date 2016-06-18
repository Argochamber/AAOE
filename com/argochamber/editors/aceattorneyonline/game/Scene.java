/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.editors.aceattorneyonline.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a scene for the server's cases.
 * @author Pablo
 */
public class Scene {
    
    /**
     * This is a list
     */
    private List<Actor> actors;
    
    /**
     * A list of evidences references.
     */
    private List<Evidence> evidences;
    
    /**
     * Absolute reference of the images.
     */
    private List<String> evidenceFiles;
    
    /**
     * The background used reference.
     */
    private String background;
    
    /**
     * The name of the scene.
     */
    private String name;
    
    /**
     * The scene folder, used for deletion.
     */
    private File root;

    /**
     * Bulk setup.
     * @param actors
     * @param evidences
     * @param evidenceFiles
     * @param background
     * @param name
     * @param root 
     */
    public Scene(List<Actor> actors, List<Evidence> evidences, List<String> evidenceFiles, String background, String name, File root) {
        this.actors = actors;
        this.evidences = evidences;
        this.evidenceFiles = evidenceFiles;
        this.background = background;
        this.name = name;
        this.root = root;
    }

    /**
     * This will avoid the null-pointer exception.
     */
    public Scene() {
        this.actors = new ArrayList<>();
        this.background = new String();
        this.evidenceFiles = new ArrayList<>();
        this.evidences = new ArrayList<>();
        this.name = new String();
        this.root = new File("");
    }
    
    /**
     * Gets the folder that holds the scene data.
     * @return 
     */
    public File getRoot() {
        return root;
    }

    public void setRoot(File root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<Evidence> evidences) {
        this.evidences = evidences;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<String> getEvidenceFiles() {
        return evidenceFiles;
    }

    public void setEvidenceFiles(List<String> evidenceFiles) {
        this.evidenceFiles = evidenceFiles;
    }
    
    
    
}
