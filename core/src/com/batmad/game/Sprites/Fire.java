package com.batmad.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tm on 06.01.2016.
 */
public class Fire {
    ParticleEffect fire;
    ParticleEmitter emitter;
    Rectangle target;
    Vector2 velocity;
    float x, y;
    int damage;
    boolean isFired;
    int noDamageTime;

    public Fire(float x, float y, int damage){
        this.x = x;
        this.y = y;
        this.damage = damage;
        noDamageTime = 300;

        fire = new ParticleEffect();
        fire.load(Gdx.files.internal("fireFD"), Gdx.files.internal(""));
        fire.setPosition(x, y);
        emitter = fire.getEmitters().first();
        emitter.getScale().setHigh(5, 20);
        velocity = new Vector2(x,y);
        velocity.sub(x, y);
        //stop();
    }

    public void start(){
        //emitter.durationTimer = 3000;
        emitter.setContinuous(true);
    }

    public void stop(float dt){
        //emitter.durationTimer = 0;
        emitter.setContinuous(false);
        fire.update(dt);
    }

    public void setTarget(Rectangle target){
        this.target = target;
    }

    public void update(float dt){
        fire.update(dt);
        velocity.add(target.getX() + target.getWidth(), target.getY());
        velocity.sub(x,y);
        velocity.scl(dt);
        emitter.getAngle().setHigh(angle());
        emitter.getAngle().setLow(angle());
    }

    public int getDamage() {
        return damage;
    }

    public ParticleEffect getFire() {
        return fire;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setIsFired(boolean isFiring) {
        this.isFired = isFiring;
    }

    public float angle(){
        return velocity.angle();
    }

    public int getNoDamageTime() {
        return noDamageTime;
    }
}
