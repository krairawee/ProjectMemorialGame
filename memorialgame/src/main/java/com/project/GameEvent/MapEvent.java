package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class MapEvent extends Event {

    public static final EventType<MapEvent> PRETRIAL_TO_TRIAL
            = new EventType<>(Event.ANY, "Teleport_Trial");
    public static final EventType<MapEvent> TRIAL_TO_PRETRIAL
            = new EventType<>(Event.ANY, "trial_to_pretrial");
    

    

    public MapEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
