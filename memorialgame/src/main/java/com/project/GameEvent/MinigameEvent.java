package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class MinigameEvent extends Event{

        public static final EventType<MinigameEvent> CHOICE
            = new EventType<>(Event.ANY, "CHOICE");
        
        public MinigameEvent(EventType<? extends Event> eventType){
            super(eventType);
        }
}
