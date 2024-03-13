package main;

public class V {

	// The purpose of this class is to allow for variables to be set, such as the
	// game title or the sizes of the window

	final static String gameTitle = "TreasureHuntin'";
	final static int FPS = 60;

	// SCREEN SETTINGS:

	final static int originalTileSize = 16; // X by X size time (16 is default)
	final static int scale = 3; // Scaling is necesarry when the tiles are too small
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 12; // these choose how many tiles are on each row/column of the game screen
	public final static int tileSize = originalTileSize * scale; // actual tile size on the game screen
	final static int screenWidth = tileSize * maxScreenCol;
	final static int screenHeight = tileSize * maxScreenRow;
	
	// PLAYER SETTINGS:
	
	public static int playerX = 100; // X value of default player location
	public static int playerY = 100; // Y value of default player location
	public static int playerSpeed = 4; // This value will be added/subtracted from the player's location with every respective key input
	public static String direction = "down";

	public static void main(String[] args) {

		System.out.println("Game Title: " + gameTitle);
		System.out.println("Size of each tile: " + originalTileSize * scale);
		System.out.println("Screen width: " + tileSize * maxScreenCol);
		System.out.println("Screen height: " + tileSize * maxScreenRow);
		System.out.println("Player speed: " + playerSpeed);
		System.out.println("Player default location: (" + playerX + "), (" + playerY + ")");
		System.out.println("Game FPS: " + FPS);

	}
	
}
