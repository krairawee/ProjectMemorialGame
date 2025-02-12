package com.project.GameCharacter.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.project.GameCharacter.CharacterType;
import com.project.GameEvent.CutsenceEvent;
import com.project.GameEvent.SystemEvent;




public class StatusComponent extends Component {
    String currentMap;
    public String nameCharacter;
    public String nameCutsence;
    public int phaseCutsence;
    public double PosX;
    public double PosY;

        
    @Override
    public void onUpdate(double tpf) {
        //save load data
        phaseCutsence = FXGL.geti(nameCutsence);
        currentMap = FXGL.gets("nameMap");
        if(entity.isType(CharacterType.PLAYER)){
            PosX = FXGL.getd("PositionX");
            PosY = FXGL.getd("PositionY");
        }
        //event phase process
        if(entity.isType(CharacterType.PLAYER)){
            if(entity.getComponent(StatusComponent.class).getPhaseCutsence()%2==1){
                SystemEvent.eventBus.fireEvent(new CutsenceEvent(CutsenceEvent.SHUIJI));
                FXGL.set("PhaseCutsenceShuiji", FXGL.geti("PhaseCutsenceShuiji")+1);
            }
        }
    }

    public StatusComponent(String name,String nameCutsence){
        this.nameCharacter = name;
        this.nameCutsence = nameCutsence;
    }
    
    public String getName(){
        return this.nameCharacter;
    }
    public int getPhaseCutsence(){
        return this.phaseCutsence;
    }
}
