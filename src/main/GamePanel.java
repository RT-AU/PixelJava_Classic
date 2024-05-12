package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTitleSize = 16; // 16x16 tile default size of player character, tiles, etc
    final int scale = 3; // scale tiles up to x times - can be edited later using drop down?

    final int tileSize = originalTitleSize * scale; // actual tile size

    final int maxScreenX = 16; // 4 by 3
    final int maxScreenY = 12;
    final int screenWidth = tileSize * maxScreenX; // 768 pixels
    final int screenHeight = tileSize * maxScreenY; // 576 pixels

    //SET GAME FRAMES PER SECOND
    float fps = 60f;

    KeyInput keyInput = new KeyInput();
    Thread gameThread;

    //CREATE PLAYER
    Player player = new Player(this, keyInput);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyInput); // allows gamepanel to recognise key input
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

        @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //FPS Display
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime-lastTime;
            lastTime = currentTime;

            if(delta > 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) { // Display FPS
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update(){
        player.update();
    }

    public void paintComponent(Graphics OriginalGraphics) {
        super.paintComponent(OriginalGraphics);

        Graphics2D graphics2D = (Graphics2D)OriginalGraphics;

        player.draw(this, graphics2D);

        graphics2D.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }
}
