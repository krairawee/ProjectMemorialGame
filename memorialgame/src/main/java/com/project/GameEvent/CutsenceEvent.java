package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class CutsenceEvent extends Event {

    public static final EventType<CutsenceEvent> SHUIJI
            = new EventType<>(Event.ANY, "SHUIJI");

    public static final EventType<CutsenceEvent> MAKI
            = new EventType<>(Event.ANY, "MAKI");

    public static final EventType<CutsenceEvent> KAITO
            = new EventType<>(Event.ANY, "KAITO");

    public static final EventType<CutsenceEvent> PhaseEvent1
            = new EventType<>(Event.ANY, "PhaseEvent1");
    

    public CutsenceEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
