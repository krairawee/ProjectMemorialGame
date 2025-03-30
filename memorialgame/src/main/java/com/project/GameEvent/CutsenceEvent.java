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

    public static final EventType<CutsenceEvent> KOKICHI
            = new EventType<>(Event.ANY, "KOKICHI");

    public static final EventType<CutsenceEvent> HIMIKO
            = new EventType<>(Event.ANY, "HIMIKO");

    public static final EventType<CutsenceEvent> KEEBO
            = new EventType<>(Event.ANY, "KEEBO");
            
    public static final EventType<CutsenceEvent> TSUMUGI
            = new EventType<>(Event.ANY, "TSUMUGI");

    public static final EventType<CutsenceEvent> GONTA
            = new EventType<>(Event.ANY, "GONTA");

    public static final EventType<CutsenceEvent> TRIAL
            = new EventType<>(Event.ANY,"TRIAL");

    

    public CutsenceEvent(EventType<? extends Event> eventType) {
        super(eventType);

    }
}
