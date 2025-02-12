package com.project.GameCharacter.Component;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Items;
import com.project.GameEvent.CutsenceEvent;
import com.project.GameEvent.MapEvent;
import com.project.GameEvent.SystemEvent;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Component.ObjectComponent;


public class InteractComponent extends Component{
    private ArrayList<Items> inventory;

    public InteractComponent(){
        this.inventory = new ArrayList<>();
    }
  
    public void interactCharacter(){
        Entity characterInteract = calculateDistance("character");
        Entity teleportInteract = calculateDistance("teleport");
        if(teleportInteract.getType() == SenceType.TELEPORT){
            if(teleportInteract.getComponent(ObjectComponent.class).getName().equals("pretrial")){
                SystemEvent.eventBus.fireEvent(new MapEvent(MapEvent.TELEPORT_PRETRIAL));
            }
        }
        if(characterInteract.getType() == CharacterType.OTHER){
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

    public Entity calculateDistance(String seperate){
        if(seperate == "character"){
            var characterInGame = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
            for(int i = 0;i<characterInGame.size();i++){
            if(entity.distance(characterInGame.get(i))<20){
                return characterInGame.get(i);
            }
        }
        return entity;
        }
        else{
            var characterInGame = FXGL.getGameWorld().getEntitiesByType(SenceType.TELEPORT);
            for(int i = 0;i<characterInGame.size();i++){
            if(entity.distance(characterInGame.get(i))<20){
                return characterInGame.get(i);
            }
        }
        return entity;
        }
    }
}
