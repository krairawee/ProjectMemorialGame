package com.project;


import java.io.File;
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
import com.project.GameEvent.SystemEvent;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Factory.WorldFactory;
import com.project.SaveData.CharacterData;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
// JavaFX classes
import javafx.scene.input.KeyCode;

public class App extends GameApplication {
    private PhysicsWorld gamephysic;
    public SystemEvent gameevent;
    public static Level map;
    public static CharacterFactory characterFactory;

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
                bundle.put("CameraState",FXGL.gets("CameraState"));
                bundle.put("Zoom",FXGL.getd("Zoom"));
                bundle.put("Level",FXGL.geti("Level"));

                CharacterData shuijiData = new CharacterData("shuiji", SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("shuiji").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData makiData = new CharacterData("maki", SystemEvent.getCharacterInGame("maki").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("maki").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("maki").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData kaitoData = new CharacterData("kaito", SystemEvent.getCharacterInGame("kaito").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("kaito").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("kaito").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData himikoData = new CharacterData("himiko", SystemEvent.getCharacterInGame("himiko").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("himiko").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("himiko").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData kokichiData = new CharacterData("kokichi", SystemEvent.getCharacterInGame("kokichi").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("kokichi").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("kokichi").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData tsumugiData = new CharacterData("tsumugi", SystemEvent.getCharacterInGame("tsumugi").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("tsumugi").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("tsumugi").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData gontaData = new CharacterData("gonta", SystemEvent.getCharacterInGame("gonta").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("gonta").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("gonta").getComponent(StatusComponent.class).getPhaseCutsence());
                CharacterData keeboData = new CharacterData("keebo", SystemEvent.getCharacterInGame("keebo").getComponent(MovementComponent.class).getPosX(), SystemEvent.getCharacterInGame("keebo").getComponent(MovementComponent.class).getPosY(), SystemEvent.getCharacterInGame("keebo").getComponent(StatusComponent.class).getPhaseCutsence());
                Bundle shuijiSave = shuijiData.saveData();
                Bundle makiSave = makiData.saveData();
                Bundle kaitoSave = kaitoData.saveData();
                Bundle himikoSave = himikoData.saveData();
                Bundle kokichiSave = kokichiData.saveData();
                Bundle tsumugiSave = tsumugiData.saveData();
                Bundle gontaSave = gontaData.saveData();
                Bundle keeboSave = keeboData.saveData();
                

                data.putBundle(bundle);
                data.putBundle(shuijiSave);
                data.putBundle(makiSave);
                data.putBundle(kaitoSave);
                data.putBundle(himikoSave);
                data.putBundle(kokichiSave);
                data.putBundle(tsumugiSave);
                data.putBundle(gontaSave);
                data.putBundle(keeboSave);


            }

            @Override
            public void onLoad(DataFile data) {
                Bundle bundle = data.getBundle("gameData");
                Bundle shuijiSave = data.getBundle("shuiji");
                Bundle makiSave = data.getBundle("maki");
                Bundle kaitoSave = data.getBundle("kaito");
                Bundle himikoSave = data.getBundle("himiko");
                Bundle kokichiSave = data.getBundle("kokichi");
                Bundle tsumugiSave = data.getBundle("tsumugi");
                Bundle gontaSave = data.getBundle("gonta");
                Bundle keeboSave = data.getBundle("keebo");
                
                CharacterData shuijiData = new CharacterData("shuiji", shuijiSave.get("PositionX"), shuijiSave.get("PositionY"), shuijiSave.get("PhaseCutsence"));
                CharacterData makiData = new CharacterData("maki", makiSave.get("PositionX"), makiSave.get("PositionY"), makiSave.get("PhaseCutsence"));
                CharacterData kaitoData = new CharacterData("kaito", kaitoSave.get("PositionX"), kaitoSave.get("PositionY"), kaitoSave.get("PhaseCutsence"));
                CharacterData himikoData = new CharacterData("himiko", himikoSave.get("PositionX"), himikoSave.get("PositionY"), himikoSave.get("PhaseCutsence"));
                CharacterData kokichiData = new CharacterData("kokichi", kokichiSave.get("PositionX"), kokichiSave.get("PositionY"), kokichiSave.get("PhaseCutsence"));
                CharacterData tsumugiData = new CharacterData("tsumugi", tsumugiSave.get("PositionX"), tsumugiSave.get("PositionY"), tsumugiSave.get("PhaseCutsence"));
                CharacterData gontaData = new CharacterData("gonta", gontaSave.get("PositionX"), gontaSave.get("PositionY"), gontaSave.get("PhaseCutsence"));
                CharacterData keeboData = new CharacterData("keebo", keeboSave.get("PositionX"), keeboSave.get("PositionY"), keeboSave.get("PhaseCutsence"));
                characterFactory = new CharacterFactory(shuijiData, makiData, kaitoData, himikoData, kokichiData, tsumugiData, gontaData, keeboData);

                FXGL.set("nameMap", bundle.get("nameMap"));
                FXGL.set("PositionX",bundle.get("PositionX"));
                FXGL.set("PositionY",bundle.get("PositionY"));
                FXGL.set("CameraState",bundle.get("CameraState"));
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
        SystemEvent.setHandler();
        getSpawnOnMap();
        
        
        
        //set Camera
        FXGL.getGameScene().getViewport().setZoom(FXGL.getd("Zoom"));
        if(FXGL.gets("CameraState")=="player"){
            FXGL.getGameScene().getViewport().bindToEntity(SystemEvent.getCharacterInGame("shuiji"), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
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
        vars.put("CameraState","player");
        vars.put("Zoom",3.00); 
        vars.put("Level",1);
    }

    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.F, "Save", () -> {
            FXGL.getSaveLoadService().saveAndWriteTask("save1.sav").run();
        });

        

        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).right();
            }
        }, KeyCode.D);
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).left();
            }
        }, KeyCode.A);
        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).up();
            }
        }, KeyCode.W);
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).down();
            }
        }, KeyCode.S);
        FXGL.getInput().addAction(new UserAction("InteractCharacter") {
            @Override
            protected void onAction() {
                SystemEvent.getCharacterInGame("shuiji").getComponent(InteractComponent.class).interactCharacter();
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
