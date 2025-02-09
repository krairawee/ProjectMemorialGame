package com.project;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.InteractComponent;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Factory.CharacterFactory;
import com.project.GameEvent.PhaseEventGame;
import com.project.GameEvent.SystemEvent;
import com.project.GameEvent.SystemPhaseEvent;
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
    public SystemEvent systemEvent;
    public SystemPhaseEvent system;
    Level map;

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
        systemEvent = new SystemEvent();
        system = new SystemPhaseEvent();
        // setting Baseworld and EntityFactory
        gamescene.setBackgroundColor(Color.BLACK);
        gameworld.addEntityFactory(new CharacterFactory());
        gameworld.addEntityFactory(new WorldFactory());
        //setting event
        systemEvent.setHandler();
        system.setHandler();
        //init Phase Game
        system.eventBusPhase.fireEvent(new PhaseEventGame(FXGL.getWorldProperties().getObject("PhaseEventGame")));
        //set Camera
        viewport.setZoom(3);
        viewport.bindToEntity(gameworld.getEntitiesByType(CharacterType.PLAYER).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("PhaseEventGame", PhaseEventGame.PHASE_1);
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
