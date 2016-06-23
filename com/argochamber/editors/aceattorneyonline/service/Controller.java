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

import com.argochamber.editors.aceattorneyonline.game.Actor;
import com.argochamber.editors.aceattorneyonline.game.Evidence;
import com.argochamber.editors.aceattorneyonline.game.InvalidWorkspaceException;
import com.argochamber.editors.aceattorneyonline.game.Scene;
import com.argochamber.editors.aceattorneyonline.game.Workspace;
import com.argochamber.editors.aceattorneyonline.views.MainView;
import com.argochamber.editors.aceattorneyonline.views.SceneView;
import com.argochamber.editors.aceattorneyonline.views.WorkspaceInput;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

/**
 * This will control the behaviour of the program.
 *
 * @author Pablo
 */
public class Controller {
    
    /**
     * String constants
     */
    public static final String VALUE_LAST_PATH = "lastpath",
            SECTION_LAST = "last",
            CONFIG_FILE = "config.ini",
            ACTORS_JUDGE = "Judge",
            ACTORS_JUDGE_DESC = "The judge of the case.";
    
    /**
     * The view of the path selection
     */
    private WorkspaceInput pathView;
    
    /**
     * The main view
     */
    private MainView mainView;
    
    /**
     * Workspae manager
     */
    private Workspace workspace;

    /**
     * The controller's constructor.
     */
    public Controller() {

    }

    /**
     * Main program logic.
     */
    public void begin() {
        
        //Setup the "Last used" configuration. Improves user experience! :D
        File cfg = new File(CONFIG_FILE);
        if (!cfg.exists()){
            try {
                cfg.createNewFile();
                Ini icfg = new Ini(cfg);
                Section sec = (Section)icfg.add(SECTION_LAST);
                sec.add(VALUE_LAST_PATH, "not set");
                icfg.store();
            } catch (IOException ex) {
                Log.log(Level.SEVERE, ex);
            }
        } else {
            try {
                Ini icfg = new Ini(cfg);
                Map<String, String> lastkp = (Section)icfg.get(SECTION_LAST);
                this.pathView.setPath(lastkp.get(VALUE_LAST_PATH));
            } catch (IOException ex) {
                Log.log(Level.SEVERE, ex);
            }
        }

        //Center out the panels
        pathView.setLocationRelativeTo(null);
        mainView.setLocationRelativeTo(null);

        //Initialize the panels
        pathView.setVisible(true);
        mainView.setVisible(false);

    }

    /**
     * This will checkout the path.
     *
     * @param path The path to check out
     * @param err The dialog that handles the error.
     */
    public void pathCheckAction(String path, JDialog err) {
        try {
            this.workspace = new Workspace(path);
            //If not thrown any exception, proceed.
            this.pathView.setVisible(false);
            this.mainView.setVisible(true);
            //This will setup the views
            setupMainView();
        } catch (InvalidWorkspaceException ex) {
            Log.log(Level.SEVERE, ex);
            err.setVisible(true);
        }
    }

    /**
     * Set the main view up.
     */
    private void setupMainView() {
        this.workspace.getScenes().stream().forEach((scene) -> {
            SceneView scn = this.mainView.addNewTab(scene);
            scene.getActors().stream().forEach((actor) -> {
                scn.addActor(actor, this.workspace);
            });
            scene.getEvidences().stream().forEach((evi) -> {
                scn.addEvidence(evi, scene.getEvidenceFiles());
            });
        });
    }
    /**
     * Attempt to delete a directory tree.
     * @param path The directory tree to delete.
     * @throws IOException Thrown whenever an IO Problem occurs on deletion.
     */
    public static void deleteFileOrFolder(final Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(final Path file, final IOException e) {
                return handleException(e);
            }

            private FileVisitResult handleException(final IOException e) {
                e.printStackTrace(); // replace with more robust error handling
                return TERMINATE;
            }

            @Override
            public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
                    throws IOException {
                if (e != null) {
                    return handleException(e);
                }
                Files.delete(dir);
                return CONTINUE;
            }
        });
    }

    ;
    
    /**
     * This deletes the requested scene from folder and views.
     * @param scene The scene reference.
     * @return  if could be deleted or not.
     */
    public boolean deleteScene(Scene scene) {
        try {
            Controller.deleteFileOrFolder(scene.getRoot().toPath());
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean result = !scene.getRoot().exists();
        if (result) {
            this.mainView.deleteByRef(scene);
            this.workspace.deleteScene(scene);
        } else {
            System.err.println("\tAt file: " + scene.getRoot().toString());
            System.err.println("\tCan write? " + Boolean.toString(scene.getRoot().canWrite()));
            System.err.println("\tCan read? " + Boolean.toString(scene.getRoot().canRead()));
            System.err.println("\tCan execute? " + Boolean.toString(scene.getRoot().canExecute()));
            System.err.println("\tDoes exists? " + Boolean.toString(scene.getRoot().exists()));
            System.err.println("\tIs folder? " + Boolean.toString(scene.getRoot().isDirectory()));
        }
        return result;
    }
    
    /**
     * This renames the scene each time you want.
     * @param scene Scene's reference
     * @param text The new name to give.
     */
    public void renameScene(Scene scene, String text) {
        File f = new File(scene.getRoot().getParent()+"/"+text);
        if (!f.exists()){
            //f.mkdir();
            this.mainView.renameTab(scene.getRoot().getName(), f.getName());
            scene.getRoot().renameTo(f);
            scene.setRoot(f);
            scene.setName(text);
        } else {
            JOptionPane.showMessageDialog(this.mainView,
                        "The target project name already exists!",
                        "Rename error",
                        JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * This adds a new scene to the workspace.
     * @param newScene The new scene's name.
     * @return  The newly created scene.
     */
    public Scene newScene(String newScene) {
        Scene scene = this.workspace.addScene(newScene);
        scene.getRoot().mkdir();
        SceneView view = this.mainView.addNewTab(scene);
        try {
            setupCaseData(scene, view);
        } catch (IOException ex) {
            Log.log(Level.SEVERE, ex);
        }
        return scene;
    }
    
    /**
     * This will set the last path used at config.ini.
     * @param file This is the last path used for the project (Workspace root)
     */
    public void setLastPath(String file) {
        try {
            Ini cfg = new Ini(new File(CONFIG_FILE));
            Section l = (Section)cfg.get(SECTION_LAST);
            l.remove(VALUE_LAST_PATH);
            l.add(VALUE_LAST_PATH, file);
            cfg.store();
        } catch (IOException ex) {
            Log.log(Level.SEVERE, ex);
        }
    }
    
    /**
     * Getter for the worspace path input view.
     * @return The view
     */
    public WorkspaceInput getPathView() {
        return pathView;
    }

    /**
     * Setter for the worspace path input view.
     * @param pathView the view
     */
    public void setPathView(WorkspaceInput pathView) {
        this.pathView = pathView;
    }

    /**
     * Getter for the main view ref.
     * @return the view
     */
    public MainView getMainView() {
        return mainView;
    }

    /**
     * Setter for the main view ref.
     * @param mainView the view
     */
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Frees the resources so the thread is stopped.
     */
    public void end() {
        this.mainView.dispose();
        this.pathView.dispose();
        System.exit(0);
    }

    /**
     * This will look for data, then store it.
     */
    public void storeData() {
        this.workspace.store();
    }
    
    /**
     * Sets the case data up.
     */
    private void setupCaseData(Scene scene, SceneView view) throws IOException {
       //Create one actor, the judge (It will be present everytime, don't it?)
       ArrayList<Actor> act = new ArrayList<>();
       act.add(new Actor(ACTORS_JUDGE, ACTORS_JUDGE_DESC));
       scene.setActors(act);
       
        //Now make an empty evidence list
        new File(scene.getRoot().getAbsolutePath()+"/evidence").mkdir();
       
       //Finally, dump data to the views.
        scene.getActors().stream().forEach((actor) -> {
            view.addActor(actor, this.workspace);
        });
        
        //Store the default scene data just added.
        this.workspace.store();
        
    }

    /**
     * Updates the actor's list at the given index, where the index is the key of the scene.
     * @param index The scene's index
     * @param actors the actor list (new actors and old ones mixed)
     */
    public void updateActorsAt(int index, List<Actor> actors) {
        this.workspace.getScenes().get(index).setActors(actors);
        this.mainView.updateActors(actors);
    }
    
    /**
     * Gathers a list of actors from the workspace.
     * @return list of actors
     */
    public List<File> getAvailableActors() {
        return this.workspace.getActors();
    }

    /**
     * This deletes the actor at the given scene.
     * @param actor The actor's reference
     * @param scene The scene contained in.
     */
    public void deleteActor(Actor actor, Scene scene) {
        
        scene.getActors().remove(actor);
        this.mainView.updateActors(scene.getActors());
        
    }

    /**
     * Gets the absolute list of actors.
     * @return Actors available in the wksp
     */
    public List<File> getActorList() {
        return this.workspace.getActors();
    }
    
    /**
     * This will remove the evidence by reference.
     * @param evidence The evidence's reference
     * @param scene The scene's reference
     */
    public void deleteEvidence(Evidence evidence, Scene scene) {
        scene.getEvidences().remove(evidence);
        this.mainView.updateEvidences(scene.getEvidences(), scene.getEvidenceFiles());
    }
    
    /**
     * Updates the evidence list at the given scene.
     * <p>
     *  Works like the updateActorsAt(...) method.
     * </p>
     * @see #updateActorsAt(int, java.util.List) 
     * @param sceneId the scene's index
     * @param evidences the listst of evidences.
     */
    public void updateEvidencesAt(int sceneId, List<Evidence> evidences) {
        this.workspace.getScenes().get(sceneId).setEvidences(evidences);
        this.mainView.updateEvidences(evidences, this.workspace.getScenes().get(sceneId).getEvidenceFiles());
    }

    /**
     * Gets a list of available backgrounds.
     * @return The BGs in the wksp
     */
    public List<String> getAvailableBackgrounds() {
        return this.workspace.getBackgrounds().stream().map(File::getName).collect(Collectors.toList());
    }
    

}
