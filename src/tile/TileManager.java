package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.V;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][]; // Used to store the contents of a text file and creates map out of it
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10]; // Array size is how many different tiles you have (ex. water, grass, glass, etc)
		mapTileNum = new int[V.maxWorldCol][V.maxWorldRow];
		
		getTileImage();
		loadMap(V.defaultMap);
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass_1.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall_1.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water_1.png"));
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt_1.png"));
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand_1.png"));
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree_1.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath) { // Reads the text file and inserts the value into the array
		
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Reads text file
			
			int col = 0;
			int row = 0;
			
			while (col < V.maxWorldCol && row < V.maxWorldRow) {
				String line = br.readLine();
				
				while (col < V.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == V.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		// This creates the background
		while (worldCol < V.maxWorldCol && worldRow < V.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * V.tileSize;
			int worldY = worldRow * V.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + V.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - V.tileSize < gp.player.worldX + gp.player.screenX &&
			    worldY + V.tileSize > gp.player.worldY - gp.player.screenY &&
			    worldY - V.tileSize < gp.player.worldY + gp.player.screenY)
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, V.tileSize, V.tileSize, null);
			worldCol++;
			
			if (worldCol == V.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
