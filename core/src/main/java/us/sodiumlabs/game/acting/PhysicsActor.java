package us.sodiumlabs.game.acting;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicsActor implements Actor {
    private final Body body;

    public PhysicsActor(final Body body) {
        this.body = body;
    }

    @Override
    public float getX() {
        return body.getPosition().x;
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public float getRotation() {
        return body.getAngle();
    }

    public Body getBody() {
        return body;
    }

    public void applyDirectionalForce(final float force) {
        body.applyForceToCenter(
                (float) Math.cos(getRotation()) * force,
                (float) Math.sin(getRotation()) * force,
                true);
    }

    public void applyDirectionalVelocity(final float vel) {
        body.setLinearVelocity(
                (float) Math.cos(getRotation()) * vel,
                (float) Math.sin(getRotation()) * vel);
    }
}
