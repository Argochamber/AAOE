/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.editors.aceattorneyonline.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class Actor {
    
    /**
     * Character name, also it's spriteset name.
     */
    private String name;
    
    /**
     * A brief description of the actor.
     */
    private String desc;
    
    
    /**
     * A list of evidence's references.
     */
    private List<Integer> evidenceIds;

    /**
     * This takes two arguments.
     * @param name
     * @param desc 
     */
    public Actor(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.evidenceIds = new ArrayList<>();
    }
    
    /**
     * Empty constructor.
     */
    public Actor() {
        this.name = new String();
        this.desc = new String();
        this.evidenceIds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Integer> getEvidenceIds() {
        return evidenceIds;
    }

    public void setEvidenceIds(List<Integer> evidenceIds) {
        this.evidenceIds = evidenceIds;
    }

    @Override
    public String toString() {
        return "Actor{" + "name=" + name + ", desc=" + desc + ", evidenceIds=" + evidenceIds + '}';
    }
    
    
}
