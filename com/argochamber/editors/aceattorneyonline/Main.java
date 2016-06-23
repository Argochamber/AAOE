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
package com.argochamber.editors.aceattorneyonline;

import com.argochamber.editors.aceattorneyonline.service.Controller;
import com.argochamber.editors.aceattorneyonline.service.Log;
import com.argochamber.editors.aceattorneyonline.views.MainView;
import com.argochamber.editors.aceattorneyonline.views.WorkspaceInput;

/**
 * Main Entry Point.
 * <p>
 *  This class is not to be used ass a class.
 * </p>
 * @author Pablo
 */
public final class Main {
    
    /**
     * Now, you cannot instantiate this nor inherit.
     */
    private Main(){}

    /**
     * Entry point, program starts here.
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
        
        Log.flush();
        
    }
    
}
