package com.project;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private ArrayList<String> allCharacter = new ArrayList<>(Arrays.asList("shuiji","maki","kaito","himiko","kokichi","tsumugi","gonta","keebo"));
    public static boolean isCamera = true;
    


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
                Bundle bundle = new Bundle("gameData");
                String nameMap = FXGL.gets("nameMap");
                bundle.put("nameMap", nameMap);
                bundle.put("Zoom",FXGL.getd("Zoom"));
                bundle.put("Level",FXGL.geti("Level"));
             
               

                var allentity = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
                for(int i = 0;i<allentity.size();i++){
                    String name = allentity.get(i).getComponent(StatusComponent.class).getName();
                    CharacterData characterData = new CharacterData(name, CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).getPosX(), CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).getPosY(), CharacterEventHandler.getCharacterInGame(name).getComponent(StatusComponent.class).getPhaseCutsence());
                    data.putBundle(characterData.saveData());
                }
                CharacterData PlayerData = new CharacterData("shuiji", CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).getPosX(), CharacterEventHandler.getCharacterInGame("shuiji").getComponent(MovementComponent.class).getPosY(), CharacterEventHandler.getCharacterInGame("shuiji").getComponent(StatusComponent.class).getPhaseCutsence());
                data.putBundle(PlayerData.saveData());
                data.putBundle(bundle);
   


            }

            @Override
            public void onLoad(DataFile data) {
                characterFactory = new CharacterFactory();
                Bundle bundle = data.getBundle("gameData");
                for(String name :  allCharacter){
                    if(data.getBundle(name)!=null){
                        CharacterData charData = new CharacterData(name, data.getBundle(name).get("PositionX"), data.getBundle(name).get("PositionY"), data.getBundle(name).get("PhaseCutsence"));
                        characterFactory.setCharacter(name,charData);
                    }
                }
                FXGL.set("nameMap", bundle.get("nameMap"));
                FXGL.set("PositionX",bundle.get("PositionX"));
                FXGL.set("PositionY",bundle.get("PositionY"));
                FXGL.set("Zoom",bundle.get("Zoom"));
                FXGL.set("Level", bundle.get("Level"));
         
                
            }
        });
    }

    


    @Override
    protected void initGame() {
        
        //load Save Data
        FXGL.getSaveLoadService().readAndLoadTask("save1.sav").run();
        if(characterFactory == null){
            characterFactory = new CharacterFactory();
        }
        // setting Baseworld and EntityFactory
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        //setting event
        
        //init Map Game
        FXGL.getGameWorld().addEntityFactory(characterFactory);
        FXGL.getGameWorld().addEntityFactory(new WorldFactory());
        map = FXGL.getAssetLoader().loadLevel(FXGL.gets("nameMap"), new TMXLevelLoader());
        
        
        FXGL.getGameWorld().setLevel(map);
        CharacterEventHandler.setHandler();
        MapEventHandler.setHandler();
        getSpawnOnMap();
        
        
        
        //set Camera
        FXGL.getGameScene().getViewport().setZoom(FXGL.getd("Zoom"));
        if(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).isEmpty()){
            FXGL.getGameScene().getViewport().bindToEntity(CharacterEventHandler.getCharacterInGame("shuiji"), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        }
        else{
            FXGL.getGameScene().getViewport().bindToEntity(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        }
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("nameMap","PreTrialMap.tmx");
        vars.put("PositionX",150.00);
        vars.put("PositionY",250.00);
        vars.put("view","player");
        vars.put("Zoom",3.00); 
        vars.put("Level",1);
        vars.put("StatusGame",1);
    }

    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.F, "Save", () -> {
            FXGL.getSaveLoadService().saveAndWriteTask("save1.sav").run();
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
        FXGL.getInput().addAction(new UserAction("InteractCharacter") {
            @Override
            protected void onAction() {
                CharacterEventHandler.getCharacterInGame("shuiji").getComponent(InteractComponent.class).interactCharacter();
            }
        }, KeyCode.E);
    }

    public static boolean checkFile(String name){
        File folder = new File("memorialgame\\src\\main\\java\\com");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.getName().equals(name +".sav")) {
                    return true;
                }
            }
            return false;
            
        }
        return false;
    } 
    public static void getSpawnOnMap(){
        List<Entity> entities = FXGL.getGameWorld().getEntitiesFiltered(entity -> entity.isType(CharacterType.SPAWNPOINT));
        for(Entity entity : entities){
            if(entity.getComponent(SpawnComponent.class).isShow() == true){
                SpawnComponent component = entity.getComponent(SpawnComponent.class);
                Point2D position = component.getPosition();
                FXGL.spawn(component.getName(),new SpawnData(position.getX(), position.getY()).put("PhaseCutsence", component.getPhaseCutsence()));
            }
        }
    }
}
