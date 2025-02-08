package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class CutsenceEvent extends Event {

    public static final EventType<CutsenceEvent> MAKI_PHASE1
            = new EventType<>(Event.ANY, "MAKI_PHASE");

    public static final EventType<CutsenceEvent> KAITO_PHASE1
            = new EventType<>(ANY, "KAITO_PHASE");

    public CutsenceEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
