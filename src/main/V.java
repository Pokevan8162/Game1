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
	public final static int screenWidth = tileSize * maxScreenCol;
	public final static int screenHeight = tileSize * maxScreenRow;
	
	// PLAYER SETTINGS:
	
	public static int playerX = tileSize * 20; // X value of default player location
	public static int playerY = tileSize * 20; // Y value of default player location
	public static int playerSpeed = 4; // This value will be added/subtracted from the player's location with every respective key input
	public static String direction = "down";
	
	// WORLD SETTINGS
	
	public final static int maxWorldCol = 50; // max amount of rows and columns for a map
	public final static int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public static String defaultMap = "/maps/Map02/"; //the starting map for the player

	public static void main(String[] args) {

		System.out.println("Game Title: " + gameTitle);
		System.out.println("Size of each tile: " + originalTileSize * scale);
		System.out.println("Screen width: " + tileSize * maxScreenCol);
		System.out.println("Screen height: " + tileSize * maxScreenRow);
		System.out.println("Player speed: " + playerSpeed);
		System.out.println("Player default location: (" + playerX + "), (" + playerY + ")");
		System.out.println("Game FPS: " + FPS);
		System.out.println("Max world row size: " + maxWorldRow);
		System.out.println("Max world row size: " + maxWorldCol);
		System.out.println("Path of default map: " + defaultMap);

	}
	
}
