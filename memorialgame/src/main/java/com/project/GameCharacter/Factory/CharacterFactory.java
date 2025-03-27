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
import com.project.GameCharacter.Component.SpawnComponent;
import com.project.GameCharacter.Component.StatusComponent;
import com.project.GameEvent.CutsenceEvent;
import com.project.SaveData.CharacterData;

import javafx.geometry.Point2D;

public class CharacterFactory implements EntityFactory{
    private CharacterData shuijiBundle;
    private CharacterData makiBundle;
    private CharacterData kaitoBundle;
    private CharacterData kokichiBundle;
    private CharacterData tsumugiBundle;
    private CharacterData gontaBundle;
    private CharacterData himikoBundle;
    private CharacterData keeboBundle;
    public CharacterFactory(CharacterData shuiji,CharacterData maki,CharacterData kaito,CharacterData himiko,CharacterData kokichi,CharacterData tsumugi,CharacterData gonta,CharacterData keebo){
        this.makiBundle = maki;
        this.shuijiBundle = shuiji;
        this.kaitoBundle = kaito;
        this.kokichiBundle = kokichi;
        this.tsumugiBundle = tsumugi;
        this.gontaBundle = gonta;
        this.himikoBundle = himiko;
        this.keeboBundle = keebo;
    }
    public CharacterFactory(){
    }
    @Spawns("shuiji")
    public Entity newPlayerCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        
        return FXGL.entityBuilder(data)
                .type(CharacterType.PLAYER)
                .bbox(new HitBox(new Point2D(8/2,8/2),BoundingShape.box(24,28)))
                .at(data.getX(),data.getY())
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Shuiji.png","shuiji",96,128))
                .with(new StatusComponent("shuiji","PhaseCutsenceShuiji",CutsenceEvent.SHUIJI,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointPlayer")
    public Entity newSpawnPointPlayer(SpawnData data){
        if(shuijiBundle != null){
            return FXGL.entityBuilder()
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("shuiji",shuijiBundle.getPositionX(),shuijiBundle.getPositionY(),shuijiBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder()
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("shuiji",data.getX(),data.getY(),0,true))
                   .build();
        
    }

    @Spawns("maki")
    public Entity newMakiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC); 
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .at(data.getX(), data.getY())
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Maki.png","maki",96,128))
                .with(new StatusComponent("maki","PhaseCutsenceMaki",CutsenceEvent.MAKI,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointMaki")
    public Entity newSpawnPointMaki(SpawnData data){
        if(makiBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("maki",makiBundle.getPositionX(),makiBundle.getPositionY(),makiBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("maki",data.getX(),data.getY(),0,true))
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
                .with(new StatusComponent("kaito","PhaseCutsenceKaito",CutsenceEvent.KAITO,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointKaito")
    public Entity newSpawnPointKaito(SpawnData data){
        if(kaitoBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("kaito",kaitoBundle.getPositionX(),kaitoBundle.getPositionY(),kaitoBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("kaito",data.getX(),data.getY(),0,true))
                   .build();
    }

    @Spawns("himiko")
    public Entity newhimikoCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Himiko.png","himiko",96,128))
                .with(new StatusComponent("himiko","PhaseCutsenceHimiko",CutsenceEvent.HIMIKO,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointHimiko")
    public Entity newSpawnPointHimiko(SpawnData data){
        if(himikoBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("himiko",himikoBundle.getPositionX(),himikoBundle.getPositionY(),himikoBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("himiko",data.getX(),data.getY(),0,true))
                   .build();
    }

    @Spawns("kokichi")
    public Entity newkokichiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32, 32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Kokichi.png","kokichi",96,128))
                .with(new StatusComponent("kokichi","PhaseCutsenceKokichi",CutsenceEvent.KOKICHI,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointKokichi")
    public Entity newSpawnPointKokichi(SpawnData data){
        if(kokichiBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("kokichi",kokichiBundle.getPositionX(),kokichiBundle.getPositionY(),kokichiBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("kokichi",data.getX(),data.getY(),0,true))
                   .build();
    }

    @Spawns("gonta")
    public Entity newgontaCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Gonta.png","gonta",96,128))
                .with(new StatusComponent("gonta","PhaseCutsenceGonta",CutsenceEvent.GONTA,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointGonta")
    public Entity newSpawnPointGonta(SpawnData data){
        if(gontaBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("gonta",gontaBundle.getPositionX(),gontaBundle.getPositionY(),gontaBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("gonta",data.getX(),data.getY(),0,true))
                   .build();
    }

    @Spawns("tsumugi")
    public Entity newtsumugiCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Tsumugi.png","tsumugi",96,128))
                .with(new StatusComponent("tsumugi","PhaseCutsenceTsumugi",CutsenceEvent.TSUMUGI,data.get("PhaseCutsence")))
                .with(new InteractComponent())
                .build(); 
    }

    @Spawns("SpawnPointTsumugi")
    public Entity newSpawnPointTsumugi(SpawnData data){
        if(tsumugiBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("tsumugi",tsumugiBundle.getPositionX(),tsumugiBundle.getPositionY(),tsumugiBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("tsumugi",data.getX(),data.getY(),0,true))
                   .build();
    }

    @Spawns("keebo")
    public Entity newkeeboCharacter(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0));

        return FXGL.entityBuilder(data)
                .type(CharacterType.OTHER)
                .bbox(new HitBox(BoundingShape.box(32,32)))
                .with(physics)//set physic
                .with(new CollidableComponent(true))
                .with(new MovementComponent("Keebo.png","keebo",96,128))
                .with(new StatusComponent("keebo","PhaseCutsenceKeebo",CutsenceEvent.KEEBO,data.get("PhaseCutsence")))
                .build(); 
    }

    @Spawns("SpawnPointKeebo")
    public Entity newSpawnPointKeebo(SpawnData data){
        if(keeboBundle != null){
            return FXGL.entityBuilder(data)
                               .type(CharacterType.SPAWNPOINT)
                               .with(new SpawnComponent("keebo",keeboBundle.getPositionX(),keeboBundle.getPositionY(),keeboBundle.getPhaseCutsence(),true))
                               .build();
        }
        return FXGL.entityBuilder(data)
                   .type(CharacterType.SPAWNPOINT)
                   .with(new SpawnComponent("keebo",data.getX(),data.getY(),0,true))
                   .build();
    }


    public void setData(){
        this.makiBundle = null;
        this.shuijiBundle = null;
        this.gontaBundle = null;
        this.tsumugiBundle = null;
        this.kaitoBundle = null;
        this.kokichiBundle = null;
        this.keeboBundle = null;
    }
    public void setCharacter(String name,CharacterData data){
        if(name == "shuiji"){
            shuijiBundle = data;
        }
        else if(name == "maki"){
            makiBundle = data;
        }
        else if(name == "kaito"){
            kaitoBundle = data;
        }
    }
}
