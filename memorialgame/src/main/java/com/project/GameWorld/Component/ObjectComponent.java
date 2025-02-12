package com.project.GameWorld.Component;

import com.almasb.fxgl.entity.component.Component;

public class ObjectComponent extends Component {
    public String name;
    public ObjectComponent(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
