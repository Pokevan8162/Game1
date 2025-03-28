package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Toolbox;
import main.V;

public class TileManager {

	GamePanel gp;
	public static Tile[] tile;
	public ArrayList<int[][]> maps = new ArrayList<int[][]>(100); // Used to store the multiple possible random maps
	public int mapIndex; // Used to index current map
	MapManager mg = new MapManager();
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[20];
		getTileImage();
		try {
			maps.add(loadMap(V.defaultMap));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mapIndex = 0;
	}
	
	public void getTileImage() {
		setup(0, "Space_1", false, false, false);  // collision, death, bounce
		setup(1, "Asteroid_1", false, true, false);
		setup(2, "Boundary", true, false, false);
		setup(3, "BouncyBall_1", false, false, true);
	}
	
	public void setup(int index, String imageName, boolean collision, boolean death, boolean bounce) {
		Toolbox toolBox = new Toolbox();
		
		try {
			tile[index] = new Tile(); //adds a new tile to the array of tiles
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")); //sets the image of the tile at the created index
			tile[index].image = toolBox.scaleImage(tile[index].image); //pre-scales the image so the game panel doesnt have to do that every time for performance
			tile[index].collision = collision; //sets the collision of the tile (false as default)
			tile[index].death = death;
			tile[index].bounce = bounce;
		} catch (Exception e) {
			
		}
		
	}
	
	public void generateMap() throws IOException {
		String mapPath = mg.createHardMap();
		maps.add(loadMap(mapPath));
	}
	
	public int[][] loadMap(String mapPath) throws FileNotFoundException { // Reads the text file and inserts the value into the array
		int mapTileNum[][] = new int[V.maxWorldCol][V.maxWorldRow];
		File file = new File(mapPath);
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader); // instantiates a buffered reader that goes through each line in the file
		
		try {
			BufferedReader br = new BufferedReader(reader); // Reads text file
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
			System.out.println("Error reading file");
		}
		return mapTileNum;
	}
	
	public void changeMap() throws IOException {
		generateMap();
		mapIndex++;
		gp.scrollSpeed = 0;
		gp.player.worldY = V.playerY - (384 - gp.player.screenY);
	}
	
	public void draw(Graphics2D g2) {
		
//		System.out.println(gp.scrollSpeed);
//		System.out.println(gp.player.worldY);
//		System.out.println(gp.player.screenY);
		
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < V.maxWorldCol && worldRow < V.maxWorldRow) {
			
			int tileNum = maps.get(mapIndex)[worldCol][worldRow];
			
			int screenX = worldCol * V.tileSize - V.tileSize;
			int screenY = worldRow * V.tileSize + gp.scrollSpeed - (38 * V.tileSize); // - 38 * V.tileSize makes sure we start at the bottom
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			worldCol++;
			
			if (worldCol == V.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
	
}