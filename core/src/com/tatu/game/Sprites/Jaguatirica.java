package com.tatu.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.tatu.game.Screens.PlayScreen;
import com.tatu.game.TatuBola;

public class Jaguatirica extends Enemy {

    private float stateTime;
    private Animation<TextureRegion> attackAnimation;
    private Array<TextureRegion> frames;
    private FixtureDef fdef;

    public Jaguatirica(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("jaguatirica"), i * 180, 0, 180, 128));
        }
        attackAnimation = new Animation<TextureRegion>(0.2f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 180 / TatuBola.PPM, 128 / TatuBola.PPM);
    }

    public void update(float dt) {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y + 0.6f - getHeight() / 2);
        setRegion(attackAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        this.fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Vector2[] vertice = new Vector2[6];
        vertice[0] = new Vector2(-3, 3).scl(1 / TatuBola.PPM);
        vertice[1] = new Vector2(-3, 40).scl(1 / TatuBola.PPM);
        vertice[2] = new Vector2(-70, 40).scl(1 / TatuBola.PPM);
        vertice[3] = new Vector2(-70, 70).scl(1 / TatuBola.PPM);
        vertice[4] = new Vector2(60, 60).scl(1 / TatuBola.PPM);
        vertice[5] = new Vector2(60, 3).scl(1 / TatuBola.PPM);
        shape.set(vertice);

        // CATEGORIA DO OBJETO
        fdef.filter.categoryBits = TatuBola.JAGUATIRICA_BIT;
        // COM QUAIS CATEGORIAS ELE PODE COLIDIR?
        fdef.filter.maskBits = TatuBola.DEFAULT_BIT | TatuBola.TATU_BIT;

        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData(this);
        b2body.setActive(false);
    }
}