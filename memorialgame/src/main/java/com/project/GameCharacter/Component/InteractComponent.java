package com.project.GameCharacter.Component;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Items;
import com.project.GameEvent.CutsenceEvent;
import com.project.GameEvent.SystemEvent;

public class InteractComponent extends Component{
    private ArrayList<Items> inventory;

    public InteractComponent(){
        this.inventory = new ArrayList<>();
    }
  
    public void interactCharacter(){
        Entity characterInteract = calculateDistance();
        if(characterInteract.getType() != entity.getType()){
            if(characterInteract.getComponent(StatusComponent.class).getName().equals("maki")){
                SystemEvent.eventBus.fireEvent(new CutsenceEvent(CutsenceEvent.MAKI));
            }
            else if(characterInteract.getComponent(StatusComponent.class).getName().equals( "kaito")){
                SystemEvent.eventBus.fireEvent(new CutsenceEvent(CutsenceEvent.KAITO));
            }
        }
    }

    @Override
    public void onUpdate(double tpf){
    }

    public Entity calculateDistance(){
        var characterInGame = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
        for(int i = 0;i<characterInGame.size();i++){
            if(entity.distance(characterInGame.get(i))<20){
                return characterInGame.get(i);
            }
        }
        return entity;
    }
}
