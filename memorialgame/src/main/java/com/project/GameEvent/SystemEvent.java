package com.project.GameEvent;


import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.event.EventBus;

public class SystemEvent {
    public EventBus eventBus;

    public SystemEvent(){
        this.eventBus = FXGL.getEventBus();
    }

    public void setHandler(){
        eventBus.addEventHandler(CutsenceEvent.MAKI_PHASE1, event->{
            Cutscene makiCutsence_1 = FXGL.getAssetLoader().loadCutscene("MakiCutsencePhase1");
            FXGL.getCutsceneService().startCutscene(makiCutsence_1);
        });
        eventBus.addEventHandler(CutsenceEvent.KAITO_PHASE1, event->{
            Level map = FXGL.getAssetLoader().loadLevel("PreTrialMap.tmx", new TMXLevelLoader());
            FXGL.getGameWorld().setLevel(map);
        });
    }
}
