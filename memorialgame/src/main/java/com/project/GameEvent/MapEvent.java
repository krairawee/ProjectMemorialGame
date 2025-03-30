package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class MapEvent extends Event {

    public static final EventType<MapEvent> PRETRIAL_TO_TRIAL
            = new EventType<>(Event.ANY, "Teleport_Trial");
    public static final EventType<MapEvent> RESET
            = new EventType<>(Event.ANY, "Reset");
    

    

    public MapEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
