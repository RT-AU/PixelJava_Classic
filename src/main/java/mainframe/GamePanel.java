package main.java.mainframe;

import main.java.entity.Player;
import main.java.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile default size of player character, tiles, etc
    final double scale = 1.38; // scale tiles up to x times

    final int tileSize = (int)(originalTileSize * scale); // actual tile size

    final int maxScreenX = 16; // 4 by 3
    final int maxScreenY = 12;
    final int screenWidth = tileSize * maxScreenX;
    final int screenHeight = tileSize * maxScreenY;

    //SET GAME FRAMES PER SECOND
    float fps = 30f;

    KeyInput keyInput;
    Thread gameThread;
    TileManager tileManager;
    Player player;

    public GamePanel(KeyInput keyInput) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.keyInput = keyInput;

        this.addKeyListener(keyInput); // allows gamepanel to recognise key input
        this.setFocusable(true);
        //CREATE WORLD
        tileManager = new TileManager(this);
        //INITIALISE PLAYER
        player = new Player(this, keyInput);

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

        // Main Game Loop, which renders the game at the currently set FPS
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

    // Draw the graphics
    public void paintComponent(Graphics OriginalGraphics) {
        super.paintComponent(OriginalGraphics);

        Graphics2D graphics2D = (Graphics2D)OriginalGraphics;

        tileManager.draw(graphics2D);
        player.draw(this, graphics2D);

        graphics2D.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenX() {
        return maxScreenX;
    }

    public int getMaxScreenY() {
        return maxScreenY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
