package com.project.GameCharacter.Component;

import com.almasb.fxgl.entity.component.Component;

public class StatusComponent extends Component {
    public String nameCharacter;
    public boolean isDead;
    public int phaseCutsence = 1;

    public StatusComponent(String name){
        this.nameCharacter = name;
    }
    public String getName(){
        return this.nameCharacter;
    }
    public int getPhaseCutsence(){
        return this.phaseCutsence;
    }
}
