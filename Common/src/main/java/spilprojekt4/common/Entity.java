package spilprojekt4.common;

import java.util.UUID;

public class Entity {
    private int x;
    private int y;
    private float velocity;
    private boolean hasGravity;
    private UUID ID = UUID.randomUUID();
    private int[][] map;

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public boolean isHasGravity() {
        return hasGravity;
    }

    public void setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getID() {
        return ID.toString();
    }
}
