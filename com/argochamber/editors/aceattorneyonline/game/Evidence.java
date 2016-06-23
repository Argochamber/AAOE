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

/**
 * In-game evidence data (Name, image, description...)
 * @author Pablo
 */
public class Evidence {
    
    /**
     * The name of the evidence.
     */
    private String name;
    
    /**
     * A brief description.
     */
    private String desc;
    
    /**
     * The name of the image file (ie: bullet.png)
     */
    private String image;

    /**
     * Getter for the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the desc
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter for the desc
     * @param desc new desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter for the image
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for the image
     * @param image new image
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    
}
