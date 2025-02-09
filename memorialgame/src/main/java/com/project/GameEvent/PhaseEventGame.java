package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class PhaseEventGame extends Event{

    public static final EventType<PhaseEventGame> PHASE_1
            = new EventType<>(Event.ANY, "Phase");


    public PhaseEventGame(EventType<? extends Event> eventType) {
        super(eventType);
    }
    
}
