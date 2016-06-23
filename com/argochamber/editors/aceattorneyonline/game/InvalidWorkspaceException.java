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
 * This is thrown whenever a new workspace is opened and the files did not match.
 * @author Pablo
 */
public class InvalidWorkspaceException extends Exception {

    /**
     * Build the exception
     * @param message A brief description of why this was thrown.
     */
    public InvalidWorkspaceException(String message) {
        super(message);
    }
    
    
}
