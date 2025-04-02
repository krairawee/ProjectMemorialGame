package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.project.App;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StoryEventHandler {
    public static IntegerProperty phase = new SimpleIntegerProperty(0);

    public static void setHandler(int number){
        phase.set(number);
        phase.addListener((obs, oldValue,newValue)->{
            if(newValue.intValue() == 1){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame", true);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET));
            });
            FXGL.set("StatusGame", false);
            App.Save();
            }
            else if(newValue.intValue() == 2){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame", false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_NETURAL));
                App.Save();
                });
            }
            else if(newValue.intValue() == 3){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame", false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET));
                App.Save();
                });
            }
        }); 
    }

    public static int getPhase(){
        return phase.getValue().intValue();
    }
    public static void setInit(int number){
        phase.setValue(number);
    }
}
