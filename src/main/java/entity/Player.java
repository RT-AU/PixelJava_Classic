package main.java.entity;

import main.java.mainframe.GamePanel;
import main.java.mainframe.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel gamePanel, KeyInput keyInput){
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;
        initialisePlayer();
    }

    public void initialisePlayer(){
        setX(100);
        setY(100);
        setSpeed(4);
        setDirection("down");

        try{
            setImage("up1", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_up_1.png"))));
            setImage("up2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_up_2.png"))));
            setImage("down1", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_down_1.png"))));
            setImage("down2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_down_2.png"))));
            setImage("left1", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_left_1.png"))));
            setImage("left2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_left_2.png"))));
            setImage("right1", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_right_1.png"))));
            setImage("right2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/player/player_right_2.png"))));
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if(keyInput.upPressed || keyInput.downPressed || keyInput.leftPressed || keyInput.rightPressed) {
            if(keyInput.upPressed){
                setY(getY() - getSpeed());
                setDirection("up");
            }
            else if(keyInput.downPressed){
                setY(getY() + getSpeed());
                setDirection("down");
            }
            else if(keyInput.leftPressed){
                setX(getX() - getSpeed());
                setDirection("left");
            }
            else{ // right pressed
                setX(getX() + getSpeed());
                setDirection("right");
            }

            incrementSpriteAnim(true);
            if(getSpriteAnim() > 12) { // Player sprite changes every X frames
                if(getSpriteNum() == 1) setSpriteNum(2);
                else if(getSpriteNum() == 2) setSpriteNum(1);
                incrementSpriteAnim(false);
            }
        }
    }

    public void draw(GamePanel gamePanel, Graphics2D graphics2D) {
//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize());

        BufferedImage image = null;

        switch(getDirection()){
            case "up":
                if(getSpriteNum() == 1){
                    image = getImage("up1");
                }
                if(getSpriteNum() == 2){
                    image = getImage("up2");
                }
                break;
            case "down":
                if(getSpriteNum() == 1){
                    image = getImage("down1");
                }
                if(getSpriteNum() == 2){
                    image = getImage("down2");
                }
                break;
            case "left":
                if(getSpriteNum() == 1){
                    image = getImage("left1");
                }
                if(getSpriteNum() == 2){
                    image = getImage("left2");
                }
                break;
            case "right":
                if(getSpriteNum() == 1){
                    image = getImage("right1");
                }
                if(getSpriteNum() == 2){
                    image = getImage("right2");
                }
                break;
        }
        graphics2D.drawImage(image, getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
