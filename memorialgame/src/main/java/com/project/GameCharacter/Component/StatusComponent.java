package com.project.GameCharacter.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.project.GameEvent.CutsenceEvent;

import javafx.event.EventType;




public class StatusComponent extends Component {
    public String currentMap;
    public String nameCharacter;
    public String nameCutsence;
    public int phaseCutsence = 0;
    public EventType<CutsenceEvent> nameEvent;

    public StatusComponent(String name,String nameCutsence,EventType<CutsenceEvent> nameEvent){
        this.nameCharacter = name;
        this.nameCutsence = nameCutsence;
        this.nameEvent = nameEvent;
    }

    @Override
    public void onUpdate(double tpf) {
        currentMap = FXGL.gets("nameMap");
    }


    
    public String getName(){
        return this.nameCharacter;
    }
    public int getPhaseCutsence(){
        return this.phaseCutsence;
    }
    public EventType<CutsenceEvent> getNameEvent(){
        return this.nameEvent;
    }
}
