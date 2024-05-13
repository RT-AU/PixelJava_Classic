package main.java.tile;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.mainframe.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;


public class TileManager {

    private final GamePanel gamePanel;
    private final Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxScreenX()][gamePanel.getMaxScreenY()];
        getTileImage();
        loadMap("/main/resources/assets/maps/map00.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/grass.png"))));
            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/grass1.png"))));
            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/wall.png"))));
            tile[3] = new Tile();
            tile[3].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/water.png"))));
            tile[4] = new Tile();
            tile[4].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/water1.png"))));
            tile[5] = new Tile();
            tile[5].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/assets/tiles/water2.png"))));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filepath) {
        try {

            InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(filepath));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int x = 0;
            int y = 0;

            while(x < gamePanel.getMaxScreenX() && y < gamePanel.getMaxScreenY()){
                String line = bufferedReader.readLine();

                while(x < gamePanel.getMaxScreenX()){
                    String[] numbers = line.split(" ");
                    int tileRef = Integer.parseInt(numbers[x]);

                    mapTileNum[x][y] = tileRef;
                    x++;
                }
                if (x == gamePanel.getMaxScreenX()){
                    x = 0;
                    y++;
                }
            }
            //bufferedReader.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw (Graphics2D graphics2D){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.getMaxScreenX() && row < gamePanel.getMaxScreenY()){
            int tileNum = mapTileNum[col][row];


            graphics2D.drawImage(tile[tileNum].getImage(), x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            col++;
            x += gamePanel.getTileSize();

            if(col == gamePanel.getMaxScreenX()){
                col = 0;
                x = 0;
                row++;
                y += gamePanel.getTileSize();
            }
        }





//        graphics2D.drawImage(tile[0].getImage(), 0, 0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
//        graphics2D.drawImage(tile[1].getImage(), 48, 0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
//        graphics2D.drawImage(tile[2].getImage(), 96, 0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
