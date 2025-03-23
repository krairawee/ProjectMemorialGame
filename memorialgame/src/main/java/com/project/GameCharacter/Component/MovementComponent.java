package com.project.GameCharacter.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;

public class MovementComponent extends Component{
    public String name;
    public double PosX;
    public double PosY;

    private static double SPEED = 100.00;
    private static final double DECELERATION = 300.00;

    private final AnimationChannel left;
    private final AnimationChannel right;
    private final AnimationChannel up;
    private final AnimationChannel down;
    private final AnimationChannel idle;
   
    
    private AnimatedTexture texture;
    private PhysicsComponent physics;
    

    public MovementComponent(String nameFile,String nameCharacter,int sizewidth,int sizeheight) {
        idle = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 1, 1);
        down = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 0, 2);
        left = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 3, 5);
        right = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 6, 8);
        up = new AnimationChannel(FXGL.image(nameFile), 3, sizewidth/3, sizeheight/4, Duration.seconds(0.5), 9, 11);

        texture = new AnimatedTexture(idle);
        this.name = nameCharacter;
        
    }

    @Override
    public void onAdded() {
        
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(idle);  
    }

    @Override
    public void onUpdate(double tpf) {
        PosX = entity.getPosition().getX();
        PosY = entity.getPosition().getY();
        
        double velocityX = physics.getVelocityX();
        double velocityY = physics.getVelocityY();

        
        if (velocityX > 0) {
            velocityX = Math.max(0, velocityX - DECELERATION * tpf);
        } else if (velocityX < 0) {
            velocityX = Math.min(0, velocityX + DECELERATION * tpf);
        }

        
        if (velocityY > 0) {
            velocityY = Math.max(0, velocityY - DECELERATION * tpf);
        } else if (velocityY < 0) {
            velocityY = Math.min(0, velocityY + DECELERATION * tpf);
        }

        physics.setVelocityX(velocityX);
        physics.setVelocityY(velocityY);

        
        if (velocityX == 0 && velocityY == 0 && texture.getAnimationChannel() != idle) {
            texture.loopAnimationChannel(idle);
        }
        if(velocityX != 0 && velocityY != 0){
            physics.setVelocityX(0);
            physics.setVelocityY(0);
            texture.loopAnimationChannel(idle);
        }
       
    }
    
    public void left() {
        physics.setVelocityX(-SPEED);
        if (texture.getAnimationChannel() != left) {
            texture.loopAnimationChannel(left);
        }
    }

    public void right() {
        physics.setVelocityX(SPEED);
        if (texture.getAnimationChannel() != right) {
            texture.loopAnimationChannel(right);
        }
    }

    public void up() {
        physics.setVelocityY(-SPEED);
        if (texture.getAnimationChannel() != up) {
            texture.loopAnimationChannel(up);
        }
    }

    public void down() {
        physics.setVelocityY(SPEED);
        if (texture.getAnimationChannel() != down) {
            texture.loopAnimationChannel(down);
        }
    }

    public double getPosX(){
        return this.PosX;
    }
    public double getPosY(){
        return this.PosY;
    }
}
