package main.java.mainframe;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create instance of KeyInput/Character Controller
        KeyInput keyInput = new KeyInput();

        // Create GUI elements and sizes
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PixelJava Classic");

        JLayeredPane layeredPane = new JLayeredPane();
        BackgroundPanel backgroundPanel = new BackgroundPanel(keyInput);
        backgroundPanel.setBounds(0, 0, backgroundPanel.getScreenWidth(), backgroundPanel.getScreenHeight());
        GamePanel gamePanel = new GamePanel(keyInput);
        gamePanel.setBounds(112, 108, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        layeredPane.setPreferredSize(new Dimension(backgroundPanel.getScreenWidth(), backgroundPanel.getScreenHeight()));
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(gamePanel, JLayeredPane.PALETTE_LAYER);

        window.add(layeredPane);
        window.pack();
        window.setLocationRelativeTo(null); // Set the window to display in the middle of the screen
        window.setVisible(true); // Show the window

        // Start required threads
        gamePanel.startGameThread();
        backgroundPanel.startBackgroundThread();
    }
}