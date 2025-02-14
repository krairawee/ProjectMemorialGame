package com.project.GameCharacter.Component;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.project.GameCharacter.CharacterType;

import javafx.geometry.Point2D;
import javafx.util.Duration;

public class MovementComponent extends Component{
    public String name;
    public int speedX = 0;
    public int speedY = 0;
   
    
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animDown,animLeft,animRight,animUp;
    private PhysicsComponent physics;
    

    public MovementComponent(String nameFile,String nameCharacter,int sizewidth,int sizeheight) {
        animIdle = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 1, 1);
        animDown = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 0, 2);
        animLeft = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 3, 5);
        animRight = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 6, 8);
        animUp = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 9, 11);

        texture = new AnimatedTexture(animIdle);
        this.name = nameCharacter;
        
    }

    @Override
    public void onAdded() {

        entity.getTransformComponent().setScaleOrigin(new Point2D(entity.getWidth(), entity.getHeight()));
        entity.getViewComponent().addChild(texture);  
    }
    @Override
    public void onUpdate(double tpf) {
        if(speedX != 0 && speedY == 0){

            physics.setVelocityX(speedX);
                
            
            if((texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animRight) && speedX < 0){
                texture.loopAnimationChannel(animLeft);
            }
            else if((texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animLeft) && speedX > 0){
                texture.loopAnimationChannel(animRight);
            }

            speedX = (int) (speedX * 0.9);
            if (FXGLMath.abs(speedX) < 1) {
                speedX = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
        else if(speedY != 0 && speedX == 0){

            physics.setVelocityY(speedY);;
           
            if((texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animDown) && speedY < 0){
                texture.loopAnimationChannel(animUp);
            }
            else if((texture.getAnimationChannel() == animIdle || texture.getAnimationChannel() == animUp) && speedY > 0){
                texture.loopAnimationChannel(animDown);
            }
            speedY = (int) (speedY * 0.9);
            if (FXGLMath.abs(speedY) < 1) {
                speedY = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
        
        else if(speedY != 0 && speedX != 0){
            speedX = 0;
            speedY = 0;
            physics.setVelocityX(0);
            physics.setVelocityY(0);
            texture.loopAnimationChannel(animIdle);
        }
        else if(speedY == 0 && speedX == 0){
            speedX = 0;
            speedY = 0;
            physics.setVelocityX(0);
            physics.setVelocityY(0);
            texture.loopAnimationChannel(animIdle);
        }
        if(entity.isType(CharacterType.PLAYER)){
            FXGL.set("PositionX",entity.getPosition().getX());
            FXGL.set("PositionY",entity.getPosition().getY());
        }
    }
    
    public void moveUp(){
        speedY = -100;
        entity.setScaleX(1);
    }
    public void moveDown(){
        speedY = 100;
        entity.setScaleX(1);
    }
    public void moveLeft(){
        speedX = -100;
        entity.setScaleX(1);
    }
    public void moveRight(){
        speedX = 100;
        entity.setScaleX(1);
    }
}
