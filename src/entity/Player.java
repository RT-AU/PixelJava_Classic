package entity;

import main.GamePanel;
import main.KeyInput;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel gamePanel, KeyInput keyInput){
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;
        setDefaultValues();
    }

    public void setDefaultValues(){
        setX(100);
        setY(100);
        setSpeed(4);
    }

    public void update() {
        if(keyInput.upPressed){
            setY(getY() - getSpeed());
            System.out.println("UP");
        }
        else if(keyInput.downPressed){
            setY(getY() + getSpeed());
            System.out.println("Down");
        }
        else if(keyInput.leftPressed){
            setX(getX() - getSpeed());
            System.out.println("Left");
        }
        else if(keyInput.rightPressed){
            setX(getX() + getSpeed());
            System.out.println("Right");
        }
    }

    public void draw(GamePanel gamePanel, Graphics2D graphics2D) {
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
