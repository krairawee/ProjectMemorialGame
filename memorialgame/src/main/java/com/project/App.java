package com.project;


import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import com.project.GameCharacter.Component.InteractComponent;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameCharacter.Factory.CharacterFactory;
import com.project.GameEvent.SystemEvent;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Factory.WorldFactory;


import javafx.scene.paint.Color;
// JavaFX classes
import javafx.scene.input.KeyCode;

public class App extends GameApplication {
    private PhysicsWorld gamephysic;
    public SystemEvent gameevent;
    public SystemEvent systemEvent;
    public Level map;
    public Cutscene cutSenceMaki;
    public Cutscene cutSenceKaito;

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
                var bundle = new Bundle("gameData");
                String nameMap = FXGL.gets("nameMap");

                bundle.put("PositionX", SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).PosX);
                bundle.put("PositionY", SystemEvent.getCharacterInGame("shuiji").getComponent(MovementComponent.class).PosY);
                bundle.put("nameMap", nameMap);
                bundle.put("PhaseCutsenceMaki",SystemEvent.getCharacterInGame("maki").getComponent(StatusComponent.class).getPhaseCutsence());
                bundle.put("PhaseCutsenceKaito",SystemEvent.getCharacterInGame("kaito").getComponent(StatusComponent.class).getPhaseCutsence());
                bundle.put("CameraState",FXGL.gets("CameraState"));
                bundle.put("Zoom",FXGL.getd("Zoom"));

                data.putBundle(bundle);
            }

            @Override
            public void onLoad(DataFile data) {
                var bundle = data.getBundle("gameData");

                String nameMap = bundle.get("nameMap");

                FXGL.set("nameMap", nameMap);
                FXGL.set("PhaseCutsenceMaki",bundle.get("PhaseCutsenceMaki"));
                FXGL.set("PhaseCutsenceKaito",bundle.get("PhaseCutsenceKaito"));
                FXGL.set("PhaseCutsenceShuiji",bundle.get("PhaseCutsenceShuiji"));
                FXGL.set("PhaseCutsenceKokichi",bundle.get("PhaseCutsenceKokichi"));
                FXGL.set("PhaseCutsenceHimiko",bundle.get("PhaseCutsenceHimiko"));
                FXGL.set("PhaseCutsenceKeebo",bundle.get("PhaseCutsenceKeebo"));
                FXGL.set("PhaseCutsenceGonta",bundle.get("PhaseCutsenceGonta"));
                FXGL.set("PhaseCutsenceTsumugi",bundle.get("PhaseCutsenceTsumugi"));
                FXGL.set("PositionX",bundle.get("PositionX"));
                FXGL.set("PositionY",bundle.get("PositionY"));
                FXGL.set("CameraState",bundle.get("CameraState"));
                FXGL.set("Zoom",bundle.get("Zoom"));

            }
        });
    }

    


    @Override
    protected void initGame() {
        //load Save Data
        FXGL.getSaveLoadService().readAndLoadTask("save1.sav").run();
        // setting Baseworld and EntityFactory
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        //setting event
        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setHandler();
        //init Map Game
        FXGL.getGameWorld().addEntityFactory(new CharacterFactory());
        FXGL.getGameWorld().addEntityFactory(new WorldFactory());
        map = FXGL.getAssetLoader().loadLevel(FXGL.gets("nameMap"), new TMXLevelLoader());
        FXGL.getGameWorld().setLevel(map);
        FXGL.getGameWorld().spawn("Player");
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
}
