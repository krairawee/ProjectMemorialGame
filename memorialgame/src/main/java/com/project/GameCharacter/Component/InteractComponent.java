package com.project.GameCharacter.Component;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.project.GameCharacter.CharacterType;
import com.project.GameEvent.CutsenceEvent;
import com.project.GameEvent.MapEvent;
import com.project.GameEvent.MapEventHandler;
import com.project.GameEvent.CharacterEventHandler;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Component.ObjectComponent;


public class InteractComponent extends Component{
    private Entity nearCharacter; 
    private Entity nearTeleport;
    private int previousPhaseCutsence; 

  
    public void interactCharacter(){

        if(nearTeleport.getType() == SenceType.TELEPORT){
                MapEventHandler.eventBus.fireEvent(new MapEvent(nearTeleport.getComponent(ObjectComponent.class).getMapEvent()));
        }
        
        if(nearCharacter.getType() == CharacterType.OTHER){
            nearCharacter.getComponent(StatusComponent.class).phaseCutsence++;
        }
    }

    @Override
    public void onAdded() {
    // กำหนดค่าเริ่มต้นให้ previousPhaseCutsence ตรงกับ phaseCutsence ใน StatusComponent
    previousPhaseCutsence = entity.getComponent(StatusComponent.class).getPhaseCutsence();
 
    }

    

    @Override
    public void onUpdate(double tpf){
        nearCharacter = calculateDistance("character");
        nearTeleport = calculateDistance("teleport");

        int currentPhaseCutsence = entity.getComponent(StatusComponent.class).getPhaseCutsence();
        if(FXGL.geti("StatusGame")==0){
            previousPhaseCutsence = currentPhaseCutsence;
        }
        else if((FXGL.geti("StatusGame")!=0&&currentPhaseCutsence != previousPhaseCutsence)){
            
                CharacterEventHandler.eventBus.fireEvent(new CutsenceEvent(entity.getComponent(StatusComponent.class).getNameEvent()));
                previousPhaseCutsence = currentPhaseCutsence;
            
        }


    }



    public Entity calculateDistance(String seperate){
        if(seperate == "character"){
            var characterInGame = FXGL.getGameWorld().getEntitiesByType(CharacterType.OTHER);
            for(int i = 0;i<characterInGame.size();i++){
            if(entity.distance(characterInGame.get(i))<30){
                return characterInGame.get(i);
            }
        }
        }
        else if (seperate == "teleport"){
            var teleportInGame = FXGL.getGameWorld().getEntitiesByType(SenceType.TELEPORT);
            for(int i = 0;i<teleportInGame.size();i++){
            if(entity.distance(teleportInGame.get(i))<30){
                return teleportInGame.get(i);
            }
        }
        }
        return entity;
    }
}
