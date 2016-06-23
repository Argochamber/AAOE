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

import java.util.ArrayList;
import java.util.List;

/**
 * This represents an in-game character.
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
     * @param name The name (Also the folder that contains it)
     * @param desc A brief description given by the case's editor.
     */
    public Actor(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.evidenceIds = new ArrayList<>();
    }
    
    /**
     * Empty default constructor.
     */
    public Actor() {
        this.name = new String();
        this.desc = new String();
        this.evidenceIds = new ArrayList<>();
    }
    
    /**
     * Gets the name.
     * @return The actor's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name.
     * @param name Actor's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the desc
     * @return Actor's desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the desc
     * @param desc Actor's new desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the list of evidence's (Owned by the character)
     * @return List evidence's IDs
     */
    public List<Integer> getEvidenceIds() {
        return evidenceIds;
    }

    /**
     * Sets the list of evidence's IDs
     * @param evidenceIds The new list.
     */
    public void setEvidenceIds(List<Integer> evidenceIds) {
        this.evidenceIds = evidenceIds;
    }
    
    @Override
    public String toString() {
        return "Actor{" + "name=" + name + ", desc=" + desc + ", evidenceIds=" + evidenceIds + '}';
    }
    
    
}
