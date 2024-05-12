package main.java.tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private Boolean collision = false;





    public void setCollision(Boolean collision) {
        this.collision = collision;
    }

    public Boolean getCollision() {
        return collision;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
