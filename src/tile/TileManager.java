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
		mapTileNum = new int[V.maxScreenCol][V.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/Map01/");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass_1.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall_1.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water_1.png"));
			
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
			
			while (col < V.maxScreenCol && row < V.maxScreenRow) {
				String line = br.readLine();
				
				while (col < V.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == V.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		// This creates the background
		while (col < V.maxScreenCol && row < V.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, V.tileSize, V.tileSize, null);
			col++;
			x += V.tileSize;
			
			if (col == V.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += V.tileSize;
			}
		}
	}
}
