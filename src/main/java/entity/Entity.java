package main.java.entity;

import java.awt.image.BufferedImage;

public class Entity {

    private int x, y;
    private int speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;

    private int spriteAnim = 0;
    private int spriteNum = 1;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void incrementSpriteAnim(boolean increment) {
        if(increment) {
            this.spriteAnim++;
        } else {
            this.spriteAnim = 0;
        }
    }

    public int getSpriteAnim() {
        return spriteAnim;
    }

    public void setImage(String direction, BufferedImage image){
        switch (direction){
            case "up1":
                up1 = image;
                break;
            case "up2":
                up2 = image;
                break;
            case "down1":
                down1 = image;
                break;
            case "down2":
                down2 = image;
                break;
            case "left1":
                left1 = image;
                break;
            case "left2":
                left2 = image;
                break;
            case "right1":
                right1 = image;
                break;
            case "right2":
                right2 = image;
                break;
        }
    }

    public BufferedImage getImage(String direction){
        return switch (direction) {
            case "up1" -> up1;
            case "up2" -> up2;
            case "down1" -> down1;
            case "down2" -> down2;
            case "left1" -> left1;
            case "left2" -> left2;
            case "right1" -> right1;
            case "right2" -> right2;
            default -> up1; // change this to error logo/missing texture?
        };
    }
}
