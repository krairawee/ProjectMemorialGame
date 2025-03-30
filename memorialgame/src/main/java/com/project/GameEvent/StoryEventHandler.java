package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.event.EventBus;

public class StoryEventHandler {
    public static EventBus eventBus;

    public static void setHandler(){
        eventBus = new EventBus();
        eventBus.addEventHandler(CutsenceEvent.TRIAL, event -> {
            Cutscene dialogue = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
            FXGL.getCutsceneService().startCutscene(dialogue,()->MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE))
            );
        });
    }
}
