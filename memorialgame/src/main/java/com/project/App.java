package com.project;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.InteractComponent;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Component.SpawnComponent;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameCharacter.Factory.CharacterFactory;
import com.project.GameEvent.CharacterEventHandler;
import com.project.GameEvent.MapEventHandler;
import com.project.GameEvent.MinigameEventHandler;
import com.project.GameEvent.StoryEventHandler;
import com.project.GameEvent.StoryEventHandler.CurrentEvent;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Factory.WorldFactory;
import com.project.SaveData.CharacterData;


import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
// JavaFX classes
import javafx.scene.input.KeyCode;

public class App extends GameApplication {
    private PhysicsWorld gamephysic;
    public CharacterEventHandler gameevent;
    public static Level map;
    public static CharacterFactory characterFactory;
    public static ArrayList<String> allCharacter = new ArrayList<>(Arrays.asList("shuiji","maki","kaito","himiko","kokichi","tsumugi","gonta","keebo"));
    private int saveStoryPhase = 0;
    private String nameSav;
    private boolean TrialStarted = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        // setting Screen
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setWidth(60 * 16);
        settings.setHeight(60 * 16);
        // setting GameName
        settings.setTitle("Memorial");
        settings.setVersion("Beta");
        settings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initPhysics() {
        gamephysic = FXGL.getPhysicsWorld();  // Initialize after FXGL engine starts
        gamephysic.setGravity(0, 0);  
    }
    @Override
    protected void onPreInit() {
        FXGL.getSaveLoadService().addHandler(new SaveLoadHandler() {
            @Override
            public void onSave(DataFile data) {
                //put general data
                Bundle bundle = new Bundle("gameData");
                bundle.put("nameMap", FXGL.gets("nameMap"));
                bundle.put("view", FXGL.gets("view"));

                bundle.put("StatusGame", FXGL.getb("StatusGame"));
                bundle.put("MinigamePhase",FXGL.geti("MinigamePhase"));
                bundle.put("TrialDialoguePhase", FXGL.geti("TrialDialoguePhase"));
                bundle.put("StoryPhase",FXGL.geti("StoryPhase"));
                bundle.put("CurrentEvent", FXGL.gets("CurrentEvent"));
                data.putBundle(bundle);
                
                //put character data
                var allentity = FXGL.getGameWorld().getEntitiesFiltered(entity -> entity.isType(CharacterType.OTHER) || entity.isType(CharacterType.PLAYER));
                for(int i = 0;i<allentity.size();i++){
                    String name = allentity.get(i).getComponent(StatusComponent.class).getName();
                    CharacterData characterData = new CharacterData(name, CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).getPosX(), CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).getPosY(), CharacterEventHandler.getCharacterInGame(name).getComponent(StatusComponent.class).getPhaseCutsence());
                    data.putBundle(characterData.saveData());
                }
                
   


            }

            @Override
            public void onLoad(DataFile data) {
                Bundle bundle = data.getBundle("gameData");
                for(String name :  allCharacter){
                    if(data.getBundle(name)!=null){
                        new CharacterData(name, data.getBundle(name).get("PositionX"), data.getBundle(name).get("PositionY"), data.getBundle(name).get("PhaseCutsence"));
                    }
                }

                FXGL.set("nameMap", bundle.get("nameMap"));
                FXGL.set("view", bundle.get("view")); 
                FXGL.set("MinigamePhase",bundle.get("MinigamePhase"));
                FXGL.set("TrialDialoguePhase",bundle.get("TrialDialoguePhase"));
                FXGL.set("StatusGame", bundle.get("StatusGame"));
                FXGL.set("StoryPhase", bundle.get("StoryPhase"));
                FXGL.set("CurrentEvent", bundle.get("CurrentEvent"));
        
        
               
                
    
            }
        });
    }

    


    @Override
    protected void initGame() {
        //set BackGround Scence
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);

        //load Save Data
        
        if(characterFactory == null){
            characterFactory = new CharacterFactory();
        }

        //init Map Game
        nameSav = getCurrentSaveName();
        if(nameSav != null){   
            FXGL.getSaveLoadService().readAndLoadTask(nameSav).run();
        }
        FXGL.getGameWorld().addEntityFactory(characterFactory);
        FXGL.getGameWorld().addEntityFactory(new WorldFactory());
        map = FXGL.getAssetLoader().loadLevel(FXGL.gets("nameMap")+".tmx", new TMXLevelLoader());
        FXGL.getGameWorld().setLevel(map);

        //setting event
        StoryEventHandler.setHandler(FXGL.geti("StoryPhase"));
        CharacterEventHandler.setHandler();
        MapEventHandler.setHandler();
        MinigameEventHandler.setHandler();

        //spawn Entity
        if(checkFile(FXGL.gets("nameMap")+".sav")){
            getSpawnOnMap();
        }
        else{
            getSpawnDefault();
        }
        
        //set Camera
        getCamera(FXGL.gets("view"));
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        // General Variable
        vars.put("nameMap","PreTrialMap");
        vars.put("view","player");
        vars.put("StatusGame",true);
        // Storyline Variable
        vars.put("StoryPhase",0);
        vars.put("CurrentEvent", "Default");
        vars.put("MinigamePhase",1);
        vars.put("TrialDialoguePhase", 1);
    }
    
    @Override 
    public void onUpdate(double tft){
        if(FXGL.gets("nameMap").equals("TrialMap")&&FXGL.geti("StoryPhase")==0){
            FXGL.set("CurrentEvent", "Trial");
            FXGL.set("StoryPhase", FXGL.geti("StoryPhase")+1);
            App.Save();
        }
        if((StoryEventHandler.statusgame == StoryEventHandler.StatusGame.INVOKE&&FXGL.gets("CurrentEvent").equals("Trial"))){
            StoryEventHandler.statusgame = StoryEventHandler.StatusGame.CONTINUE;
            StoryEventHandler.setSameNumber();
        }
        
    }

    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.F, "Save", () -> {
            App.Save();
        });

        

        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).right();
                
            }
        }, KeyCode.D);
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).left();
            }
        }, KeyCode.A);
        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).up();
            }
        }, KeyCode.W);
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).down();
            }
        }, KeyCode.S);
        FXGL.onKeyDown(KeyCode.E, "Interact", () -> {
         
            CharacterEventHandler.getCharacterInGame("shuiji").getComponent(InteractComponent.class).interactCharacter();
        });
    }
    public static boolean checkFile(String name){
        File folder = new File(name);
        if (folder.exists()) {
            return true;
        }
        return false;
    } 

    public static void getSpawnOnMap(){
        for(CharacterData data : CharacterData.allCharacter){
            Point2D position = new Point2D(data.getPositionX(), data.getPositionY());
            FXGL.spawn(data.getName(),new SpawnData(position).put("PhaseCutsence", data.getPhaseCutsence()));
            
        }
        System.out.println(CharacterData.allCharacter);
    }
    
    public static void getSpawnDefault(){
        List<Entity> entities = FXGL.getGameWorld().getEntitiesByType((CharacterType.SPAWNPOINT));
        for(Entity entity : entities){
            SpawnComponent component = entity.getComponent(SpawnComponent.class);
            FXGL.spawn(component.getName(),new SpawnData(component.getPosition()).put("PhaseCutsence", component.getPhaseCutsence()));     
            }
        }
    
    public static void getCamera(String view){
        if(view.equals("player")){
            FXGL.getGameScene().getViewport().setZoom(3.00);
            FXGL.getGameScene().getViewport().bindToEntity(CharacterEventHandler.getCharacterInGame("shuiji"), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        }
        else{
            FXGL.getGameScene().getViewport().setZoom(1.9);
            FXGL.getGameScene().getViewport().bindToEntity(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        }
    }

    public static String getCurrentSaveName(){
        File input = new File("CurrentSave.txt");
        try(Scanner scanner = new Scanner(input)){
            String a = scanner.nextLine();
            return a;
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void writeCurrentSaveName(String name){
        File input = new File("CurrentSave.txt");
        try(PrintWriter printWriter = new PrintWriter(input)){
            printWriter.println(name);
           
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void Save(){
        FXGL.getSaveLoadService().saveAndWriteTask(FXGL.gets("nameMap")+".sav").run();
        CharacterData.removeAll();
        writeCurrentSaveName(FXGL.gets("nameMap")+".sav");
    }

    public static void Load(){
        FXGL.getSaveLoadService().readAndLoadTask(FXGL.gets("nameMap")+".sav").run();
    }

        


}
