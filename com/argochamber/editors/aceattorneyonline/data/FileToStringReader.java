/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * @param file
     * @throws java.io.FileNotFoundException
     */
    public FileToStringReader(File file) throws FileNotFoundException{
        super(file);
    }
    
    /**
     * Tries to read the stream input.
     * Null if error.
     * @return 
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
