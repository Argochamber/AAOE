/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public static final String VALUE_LAST_PATH = "lastpath",
            SECTION_LAST = "last",
            CONFIG_FILE = "config.ini",
            ACTORS_JUDGE = "Judge",
            ACTORS_JUDGE_DESC = "The judge of the case.";
    
    private WorkspaceInput pathView;
    private MainView mainView;
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
                Section sec = icfg.add(SECTION_LAST);
                sec.add(VALUE_LAST_PATH, "not set");
                icfg.store();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Ini icfg = new Ini(cfg);
                Map<String, String> lastkp = icfg.get(SECTION_LAST);
                this.pathView.setPath(lastkp.get(VALUE_LAST_PATH));
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param path
     * @param err
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
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param path
     * @throws IOException 
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
     * @param scene 
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
     * @param newScene 
     * @return  The newly created scene.
     */
    public Scene newScene(String newScene) {
        Scene scene = this.workspace.addScene(newScene);
        scene.getRoot().mkdir();
        SceneView view = this.mainView.addNewTab(scene);
        try {
            setupCaseData(scene, view);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scene;
    }
    
    /**
     * This will set the last path used at config.ini.
     * @param file 
     */
    public void setLastPath(String file) {
        try {
            Ini cfg = new Ini(new File(CONFIG_FILE));
            Section l = cfg.get(SECTION_LAST);
            l.remove(VALUE_LAST_PATH);
            l.add(VALUE_LAST_PATH, file);
            cfg.store();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public WorkspaceInput getPathView() {
        return pathView;
    }

    public void setPathView(WorkspaceInput pathView) {
        this.pathView = pathView;
    }

    public MainView getMainView() {
        return mainView;
    }

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
     * @param index
     * @param actors
     */
    public void updateActorsAt(int index, List<Actor> actors) {
        this.workspace.getScenes().get(index).setActors(actors);
        this.mainView.updateActors(actors);
    }
    
    /**
     * Gathers a list of actors from the workspace.
     * @return 
     */
    public List<File> getAvailableActors() {
        return this.workspace.getActors();
    }

    /**
     * This deletes the actor at the given scene.
     * @param actor
     * @param scene
     */
    public void deleteActor(Actor actor, Scene scene) {
        
        scene.getActors().remove(actor);
        this.mainView.updateActors(scene.getActors());
        
    }

    /**
     * Gets the absolute list of actors.
     * @return 
     */
    public List<File> getActorList() {
        return this.workspace.getActors();
    }
    
    /**
     * This will remove the evidence by reference.
     * @param evidence 
     * @param scene 
     */
    public void deleteEvidence(Evidence evidence, Scene scene) {
        scene.getEvidences().remove(evidence);
        this.mainView.updateEvidences(scene.getEvidences(), scene.getEvidenceFiles());
    }
    
    /**
     * Updates the evidence list at the given scene.
     * @param sceneId
     * @param evidences 
     */
    public void updateEvidencesAt(int sceneId, List<Evidence> evidences) {
        this.workspace.getScenes().get(sceneId).setEvidences(evidences);
        this.mainView.updateEvidences(evidences, this.workspace.getScenes().get(sceneId).getEvidenceFiles());
    }

    /**
     * Gets a list of available backgrounds.
     * @return 
     */
    public List<String> getAvailableBackgrounds() {
        return this.workspace.getBackgrounds().stream().map(File::getName).collect(Collectors.toList());
    }
    

}
