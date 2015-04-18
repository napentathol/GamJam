package us.sodiumlabs.game.acting;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import us.sodiumlabs.game.input.GameInputProvider;

public class GuyActor
    extends PhysicsActor
{
    private static final BodyDef GUY_DEF = new BodyDef();

    private static final float VEL = 100f;

    private static final float RADIUS = 32f;

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
        circleShape.setRadius(RADIUS);

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
        int xComp = 0, yComp = 0;

        // Move on the x axis.
        if (inputProvider.isLeft()) {
            xComp = -1;
        } else if (inputProvider.isRight()) {
            xComp = 1;
        }

        // Move on the y axis.
        if (inputProvider.isForward()) {
            yComp = -1;
        } else if (inputProvider.isBack()) {
            yComp = 1;
        }
        getBody().setLinearVelocity((new Vector2(xComp, yComp)).nor().scl(VEL));

        final Vector2 face = inputProvider.getCameraAdjMouseVector()
                .add(getX(), getY());

        getBody().setTransform( getBody().getPosition(),
                 (float) Math.atan2(
                        -face.y,
                         face.x ));

        System.out.println("ACTOR: " + getX() + "\t\tCALC: " + face.x + "\t\t");
    }

    @Override
    public void acceptInputProvider(final GameInputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }
}
