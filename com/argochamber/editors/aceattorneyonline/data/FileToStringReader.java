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
package com.argochamber.editors.aceattorneyonline.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simply spawn one of these, then invoke .toString() method.
 * @author Pablo
 */
public class FileToStringReader extends FileInputStream{
    
    /**
     * This will build a reader.
     * @param file The input file
     * @throws java.io.FileNotFoundException on error opening the file.
     */
    public FileToStringReader(File file) throws FileNotFoundException{
        super(file);
    }
    
    /**
     * Tries to read the stream input.
     * Null if error.
     * @return The stream input data, as a String.
     */
    @Override
    public String toString(){
        try {
            StringBuilder sb = new StringBuilder();
            int ch = this.read();
            while(ch >= 0) {
                sb.append(ch);
                ch = this.read();
            }
            return sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(FileToStringReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
