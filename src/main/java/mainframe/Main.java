package main.java.mainframe;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PixelJava Classic");

        KeyInput keyInput = new KeyInput();

        JLayeredPane layeredPane = new JLayeredPane();


        BackgroundPanel backgroundPanel = new BackgroundPanel(keyInput);
        backgroundPanel.setBounds(0, 0, backgroundPanel.getScreenWidth(), backgroundPanel.getScreenHeight()); // Set to your desired size
        GamePanel gamePanel = new GamePanel(keyInput);
        gamePanel.setBounds(112, 108, gamePanel.getScreenWidth(), gamePanel.getScreenHeight()); // Set to your desired size

        layeredPane.setPreferredSize(new Dimension(backgroundPanel.getScreenWidth(), backgroundPanel.getScreenHeight())); // Set to match the size of the backgroundPanel

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(gamePanel, JLayeredPane.PALETTE_LAYER);

        window.add(layeredPane);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        backgroundPanel.startBackgroundThread();
    }
}