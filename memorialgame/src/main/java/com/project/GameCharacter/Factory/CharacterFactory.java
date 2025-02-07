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
import com.project.GameCharacter.Component.MovementComponent;

public class CharacterFactory implements EntityFactory{
    @Spawns("Player")
    public Entity newCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC); //set ให้ตัวละครขยับได้ เปลี่ยนเป็น static ถ้าอยากsetให้สิ่งที่ไม่มีชีวิต
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.PLAYER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))//อย่าลืม set collidablecomponent ด้วยให้มันชนกันได้เดี่ยวทะลุแมพ
                .with(new MovementComponent())//set Animation ของตัวละคร
                .build();
                
                
                
    }
}
