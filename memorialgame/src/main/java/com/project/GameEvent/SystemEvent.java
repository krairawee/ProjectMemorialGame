package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.event.EventBus;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameWorld.Property.MapData;

import java.util.List;

public class SystemEvent {
    public static EventBus eventBus;

    public void setHandler(){
        eventBus = FXGL.getEventBus();

        
            eventBus.addEventHandler(CutsenceEvent.MAKI, event -> {
                if (getCharacterInGame("maki").getComponent(StatusComponent.class).getName().equals("maki")) {
                Cutscene makiCutsence = FXGL.getAssetLoader().loadCutscene("makiCutsencePhase"+getCharacterInGame("maki").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                FXGL.getCutsceneService().startCutscene(makiCutsence);
                }
            });
        


            eventBus.addEventHandler(CutsenceEvent.KAITO, event -> {
            if(getCharacterInGame("kaito").getComponent(StatusComponent.class).getName().equals("kaito")){
                Cutscene kaitoCutsence = FXGL.getAssetLoader().loadCutscene("kaitoCutsencePhase"+getCharacterInGame("kaito").getComponent(StatusComponent.class).getPhaseCutsence()+".txt");
                FXGL.getCutsceneService().startCutscene(kaitoCutsence);
                }
            });

            eventBus.addEventHandler(MapEvent.LOAD_PRETRIAL, event -> {
                FXGL.getGameWorld().setLevel(MapData.mapPreTrial);
                });
        }
        


    public static Entity getCharacterInGame(String name){
        List<Entity> charcterGame = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
        if (charcterGame.isEmpty()) {
            return FXGL.getGameWorld().getEntitiesByType(CharacterType.PLAYER).get(0); // คืนค่าผู้เล่นหากไม่มีตัวละครในเกม
        }

        for (Entity character : charcterGame) {
            if (character.getComponent(StatusComponent.class).getName().equals(name)) {
                return character; 
            }
        }
        return FXGL.getGameWorld().getEntitiesByType(CharacterType.PLAYER).get(0);
    }
}
