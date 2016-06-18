/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.editors.aceattorneyonline.game;

/**
 * This is thrown whenever a new workspace is opened and the files did not match.
 * @author Pablo
 */
public class InvalidWorkspaceException extends Exception {

    public InvalidWorkspaceException(String message) {
        super(message);
    }
    
    
}
