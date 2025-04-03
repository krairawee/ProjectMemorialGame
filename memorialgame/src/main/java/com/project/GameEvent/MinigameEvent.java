package com.project.GameEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class MinigameEvent extends Event{

        public static final EventType<MinigameEvent> CHOICE_BULLET
            = new EventType<>(Event.ANY, "CHOICE_BULLET");

            public static final EventType<MinigameEvent> CHOICE_NETURAL
            = new EventType<>(Event.ANY, "CHOICE_NETURAL");

            public static final EventType<MinigameEvent> SHOW_PICTURE
            = new EventType<>(Event.ANY, "SHOW_PICTURE");

            public static final EventType<MinigameEvent> PANICTALK_ACTION
            = new EventType<>(Event.ANY, "PANICTALK_ACTION");
        
        public MinigameEvent(EventType<? extends Event> eventType){
            super(eventType);
        }

        
}
