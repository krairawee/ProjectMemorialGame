package com.project;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.InteractComponent;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Factory.CharacterFactory;
import com.project.GameEvent.SystemEvent;
import com.project.GameWorld.Factory.WorldFactory;

// JavaFX classes
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class App extends GameApplication {

    private GameWorld gameworld;
    private PhysicsWorld gamephysic;
    private GameScene gamescene;
    private Viewport viewport;
    public SystemEvent gameevent;
    Level map;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        // setting Screen
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        settings.setWidth(20 * 16);
        settings.setHeight(20 * 16);
        // setting GameName
        settings.setTitle("Memorial");
        settings.setVersion("Beta");
    }

    @Override
    protected void initPhysics() {
        gamephysic = FXGL.getPhysicsWorld();  // Initialize after FXGL engine starts
        gamephysic.setGravity(0, 0);  
    }

    @Override
    protected void initGame() {
        // Initialize after FXGL engine is fully initialized
        gameworld = FXGL.getGameWorld();
        gamescene = FXGL.getGameScene();
        viewport = gamescene.getViewport();
        gameevent = new SystemEvent();
        // setting Baseworld and EntityFactory
        gamescene.setBackgroundColor(Color.BLACK);
        gameworld.addEntityFactory(new CharacterFactory());
        gameworld.addEntityFactory(new WorldFactory());
        map = FXGL.getAssetLoader().loadLevel("PreTrialMap.tmx", new TMXLevelLoader());
        gameworld.setLevel(map);
        viewport.bindToEntity(gameworld.getEntitiesByType(CharacterType.PLAYER).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
        //setting event
        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setHandler();
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                gameworld.getEntitiesByType(CharacterType.PLAYER).get(0).getComponent(MovementComponent.class).moveRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                gameworld.getEntitiesByType(CharacterType.PLAYER).get(0).getComponent(MovementComponent.class).moveLeft();
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                gameworld.getEntitiesByType(CharacterType.PLAYER).get(0).getComponent(MovementComponent.class).moveUp();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                gameworld.getEntitiesByType(CharacterType.PLAYER).get(0).getComponent(MovementComponent.class).moveDown();
            }
        }, KeyCode.S);
        FXGL.getInput().addAction(new UserAction("InteractCharacter") {
            @Override
            protected void onAction() {
                gameworld.getEntitiesByType(CharacterType.PLAYER).get(0).getComponent(InteractComponent.class).interactCharacter();
            }
        }, KeyCode.E);
    }
}
