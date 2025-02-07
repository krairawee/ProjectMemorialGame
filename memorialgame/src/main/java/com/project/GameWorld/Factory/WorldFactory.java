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
import com.project.GameWorld.SenceType;

public class WorldFactory implements EntityFactory {
    @Spawns("obstacle")
    public Entity newObstacle(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(SenceType.OBSTACLE)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())//set Animation ของตัวละคร
                .with(new CollidableComponent(true))//อย่าลืม set collidablecomponent ด้วยให้มันชนกันได้เดี่ยวทะลุแมพ
                .build();         
    }
}
