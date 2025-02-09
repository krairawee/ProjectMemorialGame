package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class MapEvent extends Event {

    public static final EventType<MapEvent> LOAD_PRETRIAL
            = new EventType<>(Event.ANY, "Pretrial");

    

    public MapEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
