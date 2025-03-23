package com.project.GameCharacter.Component;

import com.almasb.fxgl.entity.component.Component;

public class SpawnComponent extends Component{
    public String name;
    public double PositionX;
    public double PositionY;
    public int PhaseCutsence;
    public boolean isShow;
    public SpawnComponent(String name,double PositionX,double PositionY,int phaseCutsence,boolean isShow){
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.PhaseCutsence = phaseCutsence;
        this.isShow = isShow;
        this.name = name;
    }
}
