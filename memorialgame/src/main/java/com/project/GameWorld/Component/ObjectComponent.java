package com.project.GameWorld.Component;

import com.almasb.fxgl.entity.component.Component;
import com.project.GameEvent.MapEvent;

import javafx.event.EventType;

public class ObjectComponent extends Component {
    public String name;
    public EventType<MapEvent> nameEvent;
    public ObjectComponent(String name,EventType<MapEvent> nameEvent){
        this.name = name;
        this.nameEvent = nameEvent;
    }

    public String getName(){
        return this.name;
    }
    public EventType<MapEvent> getMapEvent(){
        return this.nameEvent;
    }
}
