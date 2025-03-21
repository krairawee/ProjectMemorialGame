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
import com.project.GameEvent.CutsenceEvent;

import javafx.geometry.Point2D;

public class CharacterFactory implements EntityFactory{
    @Spawns("Player")
    public Entity newPlayerCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));
        
       

        return FXGL.entityBuilder()
                .type(CharacterType.PLAYER)
                .bbox(new HitBox(new Point2D(8/2,8/2),BoundingShape.box(24,28)))
                .at(FXGL.getd(("PositionX")), FXGL.getd("PositionY"))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Shuiji.png","shuiji",96,128))
                .with(new InteractComponent())
                .with(new StatusComponent("shuiji","PhaseCutsenceShuiji",CutsenceEvent.SHUIJI))
                .build(); 
    }

    @Spawns("SpawnPoint")
    public Entity newSpawnPoint(SpawnData data){
        return FXGL.entityBuilder()
                .type(CharacterType.SPAWNPOINT)
                .at(FXGL.getd(("PositionX")), FXGL.getd("PositionY"))
                .build(); 
    }

    @Spawns("maki")
    public Entity newMakiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC); 
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Maki.png","maki",96,128))
                .with(new InteractComponent())
                .with(new StatusComponent("maki","PhaseCutsenceMaki",CutsenceEvent.MAKI))
                .build(); 
    }
    
    @Spawns("kaito")
    public Entity newkaitoCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Kaito.png","kaito",96,128))
                .with(new InteractComponent())
                .with(new StatusComponent("kaito","PhaseCutsenceKaito",CutsenceEvent.KAITO))
                .build(); 
    }

    @Spawns("himiko")
    public Entity newhimikoCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Himiko.png","himiko",96,128))
                .with(new StatusComponent("himiko","PhaseCutsenceHimiko",CutsenceEvent.HIMIKO))
                .build(); 
    }

    @Spawns("kokichi")
    public Entity newkokichiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Kokichi.png","kokichi",96,128))
                .with(new StatusComponent("kokichi","PhaseCutsenceKokichi",CutsenceEvent.KOKICHI))
                .build(); 
    }

    @Spawns("gonta")
    public Entity newgontaCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Gonta.png","gonta",96,128))
                .with(new StatusComponent("gonta","PhaseCutsenceGonta",CutsenceEvent.GONTA))
                .build(); 
    }

    @Spawns("tsumugi")
    public Entity newtsumugiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Tsumugi.png","tsumugi",96,128))
                .with(new StatusComponent("tsumugi","PhaseCutsenceTsumugi",CutsenceEvent.TSUMUGI))
                .build(); 
    }

    @Spawns("keebo")
    public Entity newkeeboCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Keebo.png","keebo",96,128))
                .with(new StatusComponent("keebo","PhaseCutsenceKeebo",CutsenceEvent.KEEBO))
                .build(); 
    }
}
