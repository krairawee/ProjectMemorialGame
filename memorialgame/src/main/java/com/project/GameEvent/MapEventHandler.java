package com.project.GameEvent;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.event.EventBus;
import com.project.App;

public class MapEventHandler {

    
    public static EventBus eventBus;

    public static void setHandler(){
        eventBus = new EventBus();
        eventBus.addEventHandler(MapEvent.PRETRIAL_TO_TRIAL, event -> {
            //save currentMap
            App.Save();
            FXGL.getSaveLoadService().readAndLoadTask("TrialMap.sav").run();
            
            //set Global Variable Status
            FXGL.set("nameMap","TrialMap");
            FXGL.set("view", "camera");
            FXGL.set("StatusGame", false);
            //Load Map
            FXGL.getGameWorld().setLevel(FXGL.getAssetLoader().loadLevel("TrialMap.tmx", new TMXLevelLoader()));
            if(App.checkFile("TrialMap.sav")){
                App.getSpawnOnMap();
            }
            else{
                App.getSpawnDefault();
            }
            App.getCamera("camera");
            //load Status
            FXGL.set("StatusGame", false);
            });

        eventBus.addEventHandler(MapEvent.TRIAL_TO_PRETRIAL, event -> {
            //save 
            App.Save();
            FXGL.getSaveLoadService().readAndLoadTask("PreTrialMap.sav").run();
           
    
            //set Global Variable Status
            FXGL.set("nameMap","PreTrialMap");
            FXGL.set("view", "player");
            FXGL.set("StatusGame", false);
            //Load Map
            FXGL.getGameWorld().setLevel(FXGL.getAssetLoader().loadLevel("PreTrialMap.tmx", new TMXLevelLoader()));
            if(App.checkFile("PreTrialMap.sav")){
                App.getSpawnOnMap();
            }
            else{
                App.getSpawnDefault();
            } 
            App.getCamera("player");
            //load Status
            FXGL.set("StatusGame", true);
            });
    }
    
}
