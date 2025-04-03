package com.project.GameEvent;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.project.App;
import com.project.GameEvent.Interface.MinigameUI;
import com.project.GameEvent.Interface.PictureScene;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.util.Duration;

public class StoryEventHandler {
    public static IntegerProperty phase = new SimpleIntegerProperty(0);
    public static enum CurrentEvent {
        TRIAL,BEFORE_TRIAL
    }
    public static enum StatusGame {
        INVOKE,CONTINUE,LOADEVENT
    }
    public static CurrentEvent currentEvent;
    public static StatusGame statusgame = StatusGame.INVOKE;
    private static ChangeListener<Number> phaseListener;

    public static void setHandler(int number){
        phase.set(number);
        phaseListener = (obs, oldValue,newValue)->{
            if(newValue.intValue() == 1){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame",false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET)); 

            });
            }
            else if(newValue.intValue() == 2){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame",false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET));
                });
            }
            else if(newValue.intValue() == 3){
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame",false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET));
                });
            }
            else if(newValue.intValue() == 4){
                
                FXGL.getInput().setProcessInput(true);
                FXGL.set("StatusGame",false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.SHOW_PICTURE));
                });
            }
            else if(newValue.intValue() == 5){
                
                FXGL.getInput().setProcessInput(false);
                FXGL.set("StatusGame",false);
                Cutscene dialogue1 = FXGL.getAssetLoader().loadCutscene("trialDialogue"+FXGL.geti("TrialDialoguePhase")+".txt");
                FXGL.getCutsceneService().startCutscene(dialogue1,()-> {
                MinigameEventHandler.eventBus.fireEvent(new MinigameEvent(MinigameEvent.CHOICE_BULLET));
                });
            }
        };
        phase.addListener(phaseListener);
        
    }

    public static int getPhase(){
        return phase.getValue().intValue();
    }
    public static void setInit(int number){
        phase.setValue(number);
    }

    public static void removeListener(){
        phase.removeListener(phaseListener);
    }

    public static void setSameNumber(){
        phase.removeListener(phaseListener);
        phase.set(getPhase()-1);
        phase.addListener(phaseListener);
        phase.set(getPhase()+1);
    }
}
