package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.event.EventBus;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameWorld.SenceType;

import java.util.List;

public class SystemEvent {
    public static EventBus eventBus;

    public static void setHandler(){
        eventBus = FXGL.getEventBus();

            eventBus.addEventHandler(CutsenceEvent.SHUIJI, event -> {
                if(getCharacterInGame("shuiji").getComponent(StatusComponent.class).getName().equals("shuiji")){
                    int currentLevel = FXGL.geti("Level");
                    Cutscene shuijiCutsence = FXGL.getAssetLoader().loadCutscene("Map"+currentLevel+"shuijiCutsence"+getCharacterInGame("shuiji").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                    FXGL.getCutsceneService().startCutscene(shuijiCutsence);
                    }
                });   

            eventBus.addEventHandler(CutsenceEvent.MAKI, event -> {
                if(getCharacterInGame("maki").getComponent(StatusComponent.class).getName().equals("maki")){
                        int currentLevel = FXGL.geti("Level");
                        Cutscene makiCutsence = FXGL.getAssetLoader().loadCutscene("Map"+currentLevel+"makiCutsence"+getCharacterInGame("maki").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(makiCutsence);
                        }
                    }); 
            
            eventBus.addEventHandler(CutsenceEvent.KAITO, event -> {
                if(getCharacterInGame("kaito").getComponent(StatusComponent.class).getName().equals("kaito")){
                        int currentLevel = FXGL.geti("Level");
                        Cutscene kaitoCutsence = FXGL.getAssetLoader().loadCutscene("Map"+currentLevel+"kaitoCutsence"+getCharacterInGame("kaito").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(kaitoCutsence);
                        }
                    }); 
            
            eventBus.addEventHandler(CutsenceEvent.KOKICHI, event -> {
                if(getCharacterInGame("kokichi").getComponent(StatusComponent.class).getName().equals("kokichi")){
                        Cutscene kokichiCutsence = FXGL.getAssetLoader().loadCutscene("kokichiCutsencePhase"+getCharacterInGame("kokichi").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(kokichiCutsence);
                        }
                    }); 

            eventBus.addEventHandler(CutsenceEvent.HIMIKO, event -> {
                if(getCharacterInGame("himiko").getComponent(StatusComponent.class).getName().equals("himiko")){
                        Cutscene himikoCutsence = FXGL.getAssetLoader().loadCutscene("himikoCutsencePhase"+getCharacterInGame("himiko").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(himikoCutsence);
                        }
                    }); 
            eventBus.addEventHandler(CutsenceEvent.TSUMUGI, event -> {
                if(getCharacterInGame("tsumugi").getComponent(StatusComponent.class).getName().equals("tsumugi")){
                        Cutscene tsumugiCutsence = FXGL.getAssetLoader().loadCutscene("tsumugiCutsencePhase"+getCharacterInGame("tsumugi").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(tsumugiCutsence);
                        }
                    });  
            eventBus.addEventHandler(CutsenceEvent.GONTA, event -> {
                if(getCharacterInGame("gonta").getComponent(StatusComponent.class).getName().equals("gonta")){
                        Cutscene gontaCutsence = FXGL.getAssetLoader().loadCutscene("gontaCutsencePhase"+getCharacterInGame("gonta").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(gontaCutsence);
                        }
                    }); 
            eventBus.addEventHandler(CutsenceEvent.KEEBO, event -> {
                if(getCharacterInGame("keebo").getComponent(StatusComponent.class).getName().equals("keebo")){
                        Cutscene keeboCutsence = FXGL.getAssetLoader().loadCutscene("keeboCutsencePhase"+getCharacterInGame("keebo").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                        FXGL.getCutsceneService().startCutscene(keeboCutsence);
                        }
                    }); 

            eventBus.addEventHandler(MapEvent.TELEPORT_PRETRIAL, event -> {
                FXGL.set("nameMap","Trialmap.tmx");
                Level mapgame =  FXGL.getAssetLoader().loadLevel(FXGL.gets("nameMap"), new TMXLevelLoader());
                FXGL.set("PositionX", 160.33);
                FXGL.set("PositionY",180.00);
                FXGL.getGameWorld().setLevel(mapgame);
                FXGL.spawn("Player");
                FXGL.set("Zoom", 1.5);
                FXGL.getGameScene().getViewport().setZoom(FXGL.getd("Zoom"));
                FXGL.set("CameraState", "camera");
                FXGL.getGameScene().getViewport().bindToEntity(FXGL.getGameWorld().getEntitiesByType(SenceType.CAMERA).get(0), FXGL.getAppWidth()/2, FXGL.getAppHeight()/2);
                FXGL.set("PhaseCutsenceShuiji", FXGL.geti("PhaseCutsenceShuiji")+1);
                FXGL.set("animaInitMaki", "Left");
                FXGL.set("animaInitKaito","Left");
                });
        }
        


    public static Entity getCharacterInGame(String name){
        if(name.equals("shuiji")){
            return FXGL.getGameWorld().getEntitiesByType(CharacterType.PLAYER).get(0);
        }
        else{
            List<Entity> charcterGame = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
        for (Entity character : charcterGame) {
            if (character.getComponent(StatusComponent.class).getName().equals(name)) {
                return character; 
            }
        }
        return FXGL.getGameWorld().getEntitiesByType(CharacterType.PLAYER).get(0);
        }
    }
}
