package com.project.GameCharacter.Component;

import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;

public class SpawnComponent extends Component{
    private String name;
    private double PositionX;
    private double PositionY;
    private int PhaseCutsence;
    private boolean isShow;
    public SpawnComponent(String name,double PositionX,double PositionY,int phaseCutsence,boolean isShow){
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.PhaseCutsence = phaseCutsence;
        this.isShow = isShow;
        this.name = name;
    }

    public boolean isShow(){
        return this.isShow;
    }

    public Point2D getPosition(){
        return new Point2D(this.PositionX, this.PositionY);
    }
    public String getName(){
        return this.name;
    }
    public int getPhaseCutsence(){
        return this.PhaseCutsence;
    }
}
