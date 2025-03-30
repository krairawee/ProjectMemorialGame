package com.project.SaveData;

import com.almasb.fxgl.core.serialization.Bundle;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameEvent.CharacterEventHandler;

public class CharacterData {
    public String name;
    public double PositionX;
    public double PositionY;
    public int PhaseCutsence;

    public CharacterData(String name,double PositionX,double PositionY,int phaseCutsence){
        this.name = name;
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.PhaseCutsence = phaseCutsence;
    }
    
    public int getPhaseCutsence(){
        return this.PhaseCutsence;
    }
    public double getPositionX(){
        return this.PositionX;
    }
    public double getPositionY(){
        return this.PositionY;
    }
    public String getName(){
        return this.name;
    }
    public Bundle saveData(){
        var bundle = new Bundle(name);
        bundle.put("PositionX", CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).PosX);
        bundle.put("PositionY", CharacterEventHandler.getCharacterInGame(name).getComponent(MovementComponent.class).PosY);
        bundle.put("PhaseCutsence",CharacterEventHandler.getCharacterInGame(name).getComponent(StatusComponent.class).getPhaseCutsence());
        return bundle;
        
    }
}
