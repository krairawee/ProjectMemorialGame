package com.project.GameEvent;


import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.event.EventBus;


public class SystemPhaseEvent {
    public static EventBus eventBusPhase;

    public void setHandler(){
        eventBusPhase = FXGL.getEventBus();
            eventBusPhase.addEventHandler(PhaseEventGame.PHASE_1, event -> {
                SystemEvent.eventBus.fireEvent(new MapEvent(MapEvent.LOAD_PRETRIAL));
            });
        }
    
}
