package com.project.GameEvent;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.event.EventBus;
import com.project.App;
import com.project.GameWorld.SenceType;

public class MapEventHandler {
    private static Level TrialMap = FXGL.getAssetLoader().loadLevel("TrialMap.tmx", new TMXLevelLoader());  
    
    public static EventBus eventBus;

    public static void setHandler(){
        eventBus = new EventBus();
        eventBus.addEventHandler(MapEvent.PRETRIAL_TO_TRIAL, event -> {
            //save currentMap
            FXGL.getSaveLoadService().saveAndWriteTask(FXGL.gets("nameMap")+".sav").run();
            //set Global Variable Status
            FXGL.set("nameMap","Trialmap");
            
            FXGL.set("Zoom", 1.5);
            FXGL.set("view", "camera");
            //Load Map
            FXGL.getGameWorld().setLevel(TrialMap);
            App.getSpawnDefault();
            FXGL.getGameScene().getViewport().setZoom(FXGL.getd("Zoom"));
            FXGL.getGameScene().getViewport().bindToEntity(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
            StoryEventHandler.eventBus.fireEvent(new CutsenceEvent(CutsenceEvent.TRIAL));;
            });
        
        eventBus.addEventHandler(MapEvent.RESET, event -> {
            FXGL.set("StatusGame", 0);
            System.out.println(FXGL.geti("StatusGame"));
        });
            
            
    }
    
}
