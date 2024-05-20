package main.java.mainframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BackgroundPanel extends JPanel implements Runnable{
    // Set dimensions for game console background tile/image
    final int originalTileSize = 1;
    final int scale = 6;
    final int tileSize = originalTileSize * scale; // actual tile size

    final int maxScreenX = 96;
    final int maxScreenY = 128;
    final int screenWidth = tileSize * maxScreenX;
    final int screenHeight = tileSize * maxScreenY;

    KeyInput keyInput;
    Image img;

    float fps = 30f;
    Thread backgroundThread;

    public BackgroundPanel(KeyInput keyInput) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.keyInput = keyInput;
        this.setFocusable(false);

        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void startBackgroundThread(){
        backgroundThread = new Thread(this);
        backgroundThread.start();
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

        while (backgroundThread != null){
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

    // Update for game console button presses, which display which buttons the player has pressed
    public void update() {
        try {
            if(keyInput.upPressed){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC-UP.png")));
            }
            else if(keyInput.downPressed){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC-DOWN.png")));
            }
            else if(keyInput.leftPressed){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC-LEFT.png")));
            }
            else if(keyInput.rightPressed){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC-RIGHT.png")));
            }
            else { // if no key is being pressed, show default game console
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/background/PJC.png")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Draw graphics for the game console
    public void paintComponent(Graphics OriginalGraphics) {
        super.paintComponent(OriginalGraphics);
        Graphics2D graphics2D = (Graphics2D)OriginalGraphics;
        graphics2D.drawImage(img, 0, 0, screenWidth, screenHeight, this);
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
