package com.project.GameEvent;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.event.EventBus;
import com.project.App;
import com.project.GameWorld.SenceType;

public class MapEventHandler {
    public static EventBus eventBus;

    public static void setHandler(){
        eventBus = new EventBus();
        eventBus.addEventHandler(MapEvent.TELEPORT_PRETRIAL, event -> {
            App.characterFactory.setData();
            FXGL.set("nameMap","Trialmap.tmx");
            Level mapgame =  FXGL.getAssetLoader().loadLevel(FXGL.gets("nameMap"), new TMXLevelLoader());  
            FXGL.getGameWorld().setLevel(mapgame);
            App.getSpawnOnMap();
            FXGL.set("StatusGame",0);
            FXGL.set("Zoom", 1.5);
            FXGL.getGameScene().getViewport().setZoom(FXGL.getd("Zoom"));
            
            FXGL.getGameScene().getViewport().bindToEntity(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
            });
    }
    
}
