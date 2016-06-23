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

import com.argochamber.editors.aceattorneyonline.service.Log;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

/**
 * This is a workspace object.
 * <p>
 *  Contains all the data of the choosen root dir in order to make cases.
 * </p>
 * @author Pablo
 */
public class Workspace {

    public static final String ROOT_CLIENT = "client",
            ROOT_SERVER = "server",
            MIXED_BASE_FOLDER = "base",
            SERVER_SCENE_FOLDER = "scene",
            MIXED_EVIDENCE_FOLDER = "evidence",
            CLIENT_CHARACTER_FOLDER = "characters",
            CLIENT_BACKGROUND_FOLDER = "background",
            DEFAULT_BACKGROUND = "gs4";

    /**
     * Scenes contained in the server.
     */
    private List<Scene> scenes;

    /**
     * Background list, for IDs.
     */
    private List<File> backgrounds;

    /**
     * Actor list, global, for IDs.
     */
    private List<File> actors;

    /**
     * This is a reference to the scene's root folder.
     */
    private File sceneRoot;

    /**
     * This will try to build a new workspace.
     *
     * @param path The path to the workspace (The root dir)
     * @throws  com.argochamber.editors.aceattorneyonline.game.InvalidWorkspaceException this is thrown when the workspace is so messed up that we cannot continue.
     */
    public Workspace(String path) throws InvalidWorkspaceException {

        //The workspace will check if there is valid data beneath.
        File wksp = new File(path);

        //First test: CL/SV tree?
        File[] root = wksp.listFiles();
        if (root.length >= 2) {
            File clientf = null, serverf = null;
            for (File f : root) {
                if (f.getName().equalsIgnoreCase(ROOT_CLIENT)) {
                    clientf = f;
                } else if (f.getName().equalsIgnoreCase(ROOT_SERVER)) {
                    serverf = f;
                }
            }
            if (clientf != null && serverf != null) {
                //Now, we have both, client and server.

                //Serve the client files.
                File[] client_list = clientf.listFiles();
                File clientBase = null;
                for (File f : client_list) {
                    if (f.getName().equalsIgnoreCase(MIXED_BASE_FOLDER)) {
                        clientBase = f;
                    }
                }

                //Serve the server files.
                File[] server_list = serverf.listFiles();
                File serverBase = null;
                for (File f : server_list) {
                    if (f.getName().equalsIgnoreCase(MIXED_BASE_FOLDER)) {
                        serverBase = f;
                    }
                }

                if (clientBase != null && serverBase != null) {
                    //This is it
                    try {
                        //This is important; First we must take the references for backgrounds and actors, then the scenes.
                        fillBackgrounds(clientBase);
                        fillActors(clientBase);
                        fillScenes(serverBase);
                    } catch (IOException ex) {
                        throw new InvalidWorkspaceException("Cannot open some server files!");
                    }

                } else {
                    throw new InvalidWorkspaceException("Client or server base missing!");
                }
            } else {
                throw new InvalidWorkspaceException("There are folders missing! (Check out if Client & Server is present)");
            }
        } else {
            throw new InvalidWorkspaceException("The root folders are missing one or more elements (Client or Server, or both)");
        }
    }

    /**
     * This will fill up scene data.
     * @param server The 'server' folder.
     */
    private void fillScenes(File server) throws InvalidWorkspaceException, IOException {
        this.scenes = new ArrayList<>();
        File[] server_base = server.listFiles();
        File scene_root = null;
        for (File f : server_base) {
            if (f.getName().equalsIgnoreCase(SERVER_SCENE_FOLDER)) {
                scene_root = f;
                this.sceneRoot = f;
            }
        }
        if (scene_root != null) {
            //Read in each scene
            File[] scenes_f = scene_root.listFiles();
            for (File scene_folder : scenes_f) {
                try {
                    // --- FOR EACH SCENE --- //
                    //Important data
                    HashMap<String, Ini> cfg = new HashMap<>();
                    ArrayList<Evidence> evList = new ArrayList<>();
                    Scene scen = new Scene();
                    scen.setName(scene_folder.getName());
                    scen.setRoot(scene_folder);
                    for (File scene_file : scene_folder.listFiles()) {
                        //System.out.println("Scanning "+scene_file.getName());
                        //Ignore the evidence folder
                        if (!scene_file.getName().equalsIgnoreCase(MIXED_EVIDENCE_FOLDER)) {
                            // Fill each ini file.
                            Ini ini = new Ini();
                            ini.load(new FileReader(scene_file));
                            cfg.put(scene_file.getName(), ini);
                        } else {
                            //If it is the folder of the evidences, fill out with reference names.
                            File[] evi_images = scene_file.listFiles();
                            Arrays.asList(evi_images).stream().map(File::getName).forEach(str -> scen.getEvidenceFiles().add(str));
                        }
                    }
                    //Once scene files are read, do the proper scan:
                    Map<String, String> global = (Section)((Ini)cfg.get("init.ini")).get("Global");
                    scen.setBackground(global.get("BackGround"));

                    //Then the evidence data
                    List<Evidence> evis = new ArrayList<>();
                    for (int i = 0; i < cfg.get("init.ini").size() - 3; i++) {
                        Section ev = (Section)((Ini)cfg.get("init.ini")).get("evi" + (i + 1));
                        Evidence evi = new Evidence();
                        evi.setName((String)ev.get("name"));
                        evi.setDesc((String)ev.get("desc"));
                        evi.setImage((String)ev.get("image"));
                        evis.add(evi);
                    }
                    scen.setEvidences(evis);

                    //The character reference list
                    List<Actor> charNames = new ArrayList<>();

                    Map<String, String> chars = (Section)((Ini)cfg.get("init.ini")).get("chars");
                    Map<String, String> descs = (Section)((Ini)cfg.get("init.ini")).get("desc");
                    Integer lim = Integer.parseInt(chars.get("number"));
                    for (Integer i = 0; i < lim; i++) {
                        //New actor!
                        try {
                            Actor act = new Actor();
                            act.setDesc(descs.get(i.toString()));
                            act.setName(chars.get(i.toString()));

                            //THIS IS IMPORTANT: The evidences that the characters will have.
                            String charN = (String)((Section)((Ini)cfg.get("char" + i + ".ini")).get("desc")).get("evi");
                            List<Integer> evids = Arrays.asList(charN.split(","))
                                    .stream()
                                    .map(str -> Integer.parseInt(str.trim()))
                                    .collect(Collectors.toList());
                            act.setEvidenceIds(evids);
                            charNames.add(act);
                        } catch (NullPointerException ex) {
                            Log.log(Level.SEVERE, "Actor is corrupted, or non existant (char" + i + ".ini not found?)");
                        }
                    }
                    scen.setActors(charNames);
                    this.scenes.add(scen);
                } catch (NullPointerException ex) {
                    Log.log(Level.SEVERE, "Scene corrupted!");
                }
            }
        } else {
            throw new InvalidWorkspaceException("Corrupted server folder! Scenes not found!");
        }
    }

    /**
     * This will get background names.
     */
    private void fillBackgrounds(File client) throws InvalidWorkspaceException {
        this.backgrounds = new ArrayList<>();
        File[] base_list = client.listFiles();
        File bg_root = null;
        for (File f : base_list) {
            if (f.getName().equalsIgnoreCase(CLIENT_BACKGROUND_FOLDER)) {
                bg_root = f;
            }
        }
        if (bg_root != null) {
            //Foreach background:
            File[] bg_list = bg_root.listFiles();
            this.backgrounds.addAll(Arrays.asList(bg_list));
        } else {
            throw new InvalidWorkspaceException("Corrupted client folder! Backgrounds not found!");
        }
    }

    /**
     * This will get actor references also.
     */
    private void fillActors(File client) throws InvalidWorkspaceException {
        this.actors = new ArrayList<>();
        File[] base_list = client.listFiles();
        File actor_root = null;
        for (File f : base_list) {
            if (f.getName().equalsIgnoreCase(CLIENT_CHARACTER_FOLDER)) {
                actor_root = f;
            }
        }
        if (actor_root != null) {
            // For each character/actor:
            File[] actor_list = actor_root.listFiles();
            this.actors.addAll(Arrays.asList(actor_list));

        } else {
            throw new InvalidWorkspaceException("Corrupted client folder! Characters not found!");
        }
    }

    /**
     * Workspace contained scenes
     * @return list of scenes (Cases)
     */
    public List<Scene> getScenes() {
        return scenes;
    }

    /**
     * Gets the available background names
     * @return list bg names
     */
    public List<File> getBackgrounds() {
        return backgrounds;
    }

    /**
     * Gets the character list
     * @return total characters in.
     */
    public List<File> getActors() {
        return actors;
    }

    /**
     * Deletes the given scene from the list.
     *
     * @param scene The scene's reference that you want to delete.
     */
    public void deleteScene(Scene scene) {
        this.scenes.remove(scene);
    }

    /**
     * This will create a new case.
     *
     * @param newScene the name of the case
     * @return the scene's reference
     */
    public Scene addScene(String newScene) {
        Scene scene = new Scene();
        scene.setName(newScene);
        scene.setRoot(new File(this.sceneRoot.getAbsolutePath() + "/" + newScene));
        scene.setActors(new ArrayList<>());
        scene.setBackground(DEFAULT_BACKGROUND);
        scene.setEvidences(new ArrayList<>());
        this.scenes.add(scene);
        return scene;
    }

    /**
     * Stores the data of the characters found within.
     */
    public void store() {
        //Store the scene data aka case data
        this.scenes.stream().forEach((Scene scene) -> {
            //First of all, clean up the ini files:
            Arrays.asList(scene.getRoot().listFiles(
                    (File dir, String name) -> name.toLowerCase().endsWith(".ini"))
            ).stream().forEach((File file) -> file.delete());
            try {
                String CASE_ROOT = Workspace.this.sceneRoot.getAbsolutePath() + "/" + scene.getName();

                //INIT.ini file
                File initFile = new File(CASE_ROOT + "/init.ini");
                System.out.println("Init file for " + scene.getName() + " is " + initFile.getAbsolutePath());
                if (!initFile.exists()) {
                    initFile.createNewFile();
                }
                Ini init = new Ini(initFile);
                init.clear();

                //Fill out the global data.
                Section global = init.add("Global");
                global.add("EviNumber", scene.getEvidences().size());
                global.add("BackGround", DEFAULT_BACKGROUND);

                Section chars = init.add("chars");
                chars.add("number", scene.getActors().size());

                Section desc = init.add("desc");

                for (Integer i = 0; i < scene.getActors().size(); i++) {
                    Actor actor = scene.getActors().get(i);
                    //CharN.ini
                    File charFile = new File(CASE_ROOT + "/char" + i + ".ini");
                    if (!charFile.exists()) {
                        charFile.createNewFile();
                    }
                    Ini act = new Ini(charFile);
                    act.clear();

                    //It's important to fill the actor's data.
                    chars.add(i.toString(), actor.getName());
                    desc.add(i.toString(), actor.getDesc());

                    //Now the reference list
                    Section dsc = act.add("desc");
                    dsc.add("evinumber", "" + actor.getEvidenceIds().size());
                    String str = actor.getEvidenceIds().stream()
                            .map(n -> Integer.toString(n))
                            .collect(Collectors.joining(", ")).concat(",");
                    //System.out.println("Lambda: "+str);
                    dsc.add("evi", str);
                    dsc.add("pass", "");

                    act.store();
                }

                for (int i = 0; i < scene.getEvidences().size(); i++) {
                    Evidence evidence = scene.getEvidences().get(i);

                    Section evi = init.add("evi" + (i + 1));
                    evi.add("type", "1");
                    evi.add("name", evidence.getName());
                    evi.add("desc", evidence.getDesc());
                    evi.add("image", evidence.getImage());
                }

                init.store();
            } catch (IOException ex) {
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
