package us.sodiumlabs.game.acting;

import com.badlogic.gdx.physics.box2d.*;
import us.sodiumlabs.game.input.GameInputProvider;

public class GuyActor
    extends PhysicsActor
{
    private static final BodyDef GUY_DEF = new BodyDef();

    private static final float VEL = 100f;

    private static final float ANG_VEL = 10f;

    private GameInputProvider inputProvider;

    static  {
        GUY_DEF.position.set(10,10);
        GUY_DEF.type = BodyDef.BodyType.KinematicBody;
    }

    public GuyActor(final World world) {
        super(createGuyBody(world));
    }

    private static Body createGuyBody(final World world) {
        final CircleShape circleShape = new CircleShape();
        circleShape.setRadius(64f);

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        final Body body = world.createBody(GUY_DEF);
        body.createFixture(fixtureDef);

        circleShape.dispose();

        return body;
    }

    @Override
    public void act(float delta) {
        // Move the body.
        if (inputProvider.isLeft()) {
            getBody().setAngularVelocity(ANG_VEL);
        } else if (inputProvider.isRight()) {
            getBody().setAngularVelocity(-ANG_VEL);
        } else {
            getBody().setAngularVelocity(0);
        }

        // Rotate the body.
        if (inputProvider.isForward()) {
            applyDirectionalVelocity(-VEL);
        } else if (inputProvider.isBack()) {
            applyDirectionalVelocity(VEL);
        } else {
            applyDirectionalVelocity(0);
        }
    }

    @Override
    public void acceptInputProvider(final GameInputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }
}
