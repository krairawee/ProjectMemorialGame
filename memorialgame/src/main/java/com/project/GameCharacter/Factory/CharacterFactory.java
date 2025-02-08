package com.project.GameCharacter.Factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.project.GameCharacter.CharacterType;
import com.project.GameCharacter.Component.InteractComponent;
import com.project.GameCharacter.Component.MovementComponent;
import com.project.GameCharacter.Component.StatusComponent;

public class CharacterFactory implements EntityFactory{
    @Spawns("Player")
    public Entity newPlayerCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.PLAYER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Shuiji.png","shuiji"))
                .with(new InteractComponent())
                .with(new StatusComponent("shuiji"))
                .build(); 
    }

    @Spawns("maki")
    public Entity newMakiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC); 
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Maki.png","maki"))
                .with(new StatusComponent("maki"))
                .build(); 
    }
    
    @Spawns("kaito")
    public Entity newCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Kaito.png","kaito"))
                .with(new StatusComponent("kaito"))
                .build(); 
    }
}
