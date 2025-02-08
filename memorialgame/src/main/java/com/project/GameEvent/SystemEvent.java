package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.event.EventBus;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.StatusComponent;
import java.util.List;

public class SystemEvent {
    public static EventBus eventBus;

    public void setHandler(){
        eventBus = FXGL.getEventBus();

        if (getCharacterInGame("maki").getComponent(StatusComponent.class).getName().equals("maki")) {
            eventBus.addEventHandler(CutsenceEvent.MAKI, event -> {
                Cutscene makiCutsence_1 = FXGL.getAssetLoader().loadCutscene("makiCutsencePhase1"+getCharacterInGame("maki").getComponent(StatusComponent.class).getPhaseCutsence());
                FXGL.getCutsceneService().startCutscene(makiCutsence_1);
            });
        }

        if(getCharacterInGame("kaito").getComponent(StatusComponent.class).getName().equals("kaito")){
            eventBus.addEventHandler(CutsenceEvent.KAITO, event -> {
                Cutscene kaitoCutsence_1 = FXGL.getAssetLoader().loadCutscene("kaitoCutsencePhase"+getCharacterInGame("kaito").getComponent(StatusComponent.class).getPhaseCutsence());
                FXGL.getCutsceneService().startCutscene(kaitoCutsence_1);
            });
        }
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
