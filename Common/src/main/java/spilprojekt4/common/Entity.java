package spilprojekt4.common;

import java.util.UUID;

public class Entity {
    private EntityType entityType;
    private float x;
    private float y;
    private float[] shapeX;
    private float[] shapeY;
    private float velocity;
    private boolean hasGravity;
    private UUID ID = UUID.randomUUID();
    private int[][] map;

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    
    
    public EntityType getEntityType()
    {
        return entityType;
    }

    public void setEntityType(EntityType entityType)
    {
        this.entityType = entityType;
    }
    
    public int[][] getMap()
    {
        return map;
    }

    public void setMap(int[][] map)
    {
        this.map = map;
    }
    
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getID() {
        return ID.toString();
    }
}
