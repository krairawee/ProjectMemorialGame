package com.project.GameWorld.Factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.project.GameEvent.MapEvent;
import com.project.GameWorld.SenceType;
import com.project.GameWorld.Component.ObjectComponent;

public class WorldFactory implements EntityFactory {
    @Spawns("obstacle")
    public Entity newObstacle(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(SenceType.OBSTACLE)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();        
    }
    @Spawns("Pretrial_to_Trial")
    public Entity newTeleport(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(SenceType.TELEPORT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .with(new ObjectComponent("Pretrial_to_Trial",MapEvent.PRETRIAL_TO_TRIAL))
                .build();        
    }

    @Spawns("Trial_to_Pretrial")
    public Entity newTrial_to_Pretrial(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(SenceType.TELEPORT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .with(new ObjectComponent("Trial_to_Pretrial",MapEvent.TRIAL_TO_PRETRIAL))
                .build();        
    }
    @Spawns("camera")
    public Entity newCamera(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(SenceType.CAMERA)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();        
    }
}
