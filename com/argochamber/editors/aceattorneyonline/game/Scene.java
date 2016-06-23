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
     * @param actors List of actors
     * @param evidences List of evidences
     * @param evidenceFiles List of evidence files
     * @param background List of backgrounds
     * @param name Name of the case
     * @param root Root folder
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
     * @return root's reference
     */
    public File getRoot() {
        return root;
    }

    /**
     * Setter for the scene's root folder
     * @param root the folder reference
     */
    public void setRoot(File root) {
        this.root = root;
    }

    /**
     * Gets the name of the scene (The case)
     * @return the case's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the case's name
     * @param name new case name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the characters of the scene
     * @return actor list
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * Sets the characters in the case
     * @param actors new actor list
     */
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Gets the evidence's list
     * @return evi list
     */
    public List<Evidence> getEvidences() {
        return evidences;
    }

    /**
     * Sets the evidences list
     * @param evidences new evi list
     */
    public void setEvidences(List<Evidence> evidences) {
        this.evidences = evidences;
    }

    /**
     * Gets the bg name
     * @return bg name
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the bg name
     * @param background new bg 
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * Gets the list of evi files
     * @return a list
     */
    public List<String> getEvidenceFiles() {
        return evidenceFiles;
    }

    /**
     * Sets the files
     * @param evidenceFiles new list
     */
    public void setEvidenceFiles(List<String> evidenceFiles) {
        this.evidenceFiles = evidenceFiles;
    }
    
    
    
}
