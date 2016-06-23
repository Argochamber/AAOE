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
package com.argochamber.editors.aceattorneyonline.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used as an static library for loggin data (Errors).
 * @author Pablo
 */
public abstract class Log {
    
    /**
     * The output file.
     */
    private static java.util.logging.FileHandler handler;
    
    static {
        
        try {
            handler = new java.util.logging.FileHandler("log.xml");
            java.util.logging.Logger.getGlobal().addHandler(handler);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This logs to the logger.
     * @param level The level of the message
     * @param msg the message itself.
     */
    public static void log(java.util.logging.Level level, Object msg){
        java.util.logging.Logger.getGlobal().log(level, msg.toString());
    }
    
    /**
     * Flushes the stream.
     */
    public static void flush(){
        handler.flush();
    }
    
}
