/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.editors.aceattorneyonline;

import com.argochamber.editors.aceattorneyonline.service.Controller;
import com.argochamber.editors.aceattorneyonline.views.MainView;
import com.argochamber.editors.aceattorneyonline.views.WorkspaceInput;

/**
 *
 * @author Pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Controller controller = new Controller();
        
        //Register the main view
        MainView mainView = new MainView(controller);
        controller.setMainView(mainView);
        
        //Register the path view
        WorkspaceInput pathView = new WorkspaceInput(controller);
        controller.setPathView(pathView);
        
        controller.begin();
        
    }
    
}
