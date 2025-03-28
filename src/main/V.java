package main;

import java.io.File;

public class V {

	// The purpose of this class is to allow for variables to be set, such as the
	// game title or the sizes of the window

	final static String gameTitle = "SpaceInvaders";
	final static int FPS = 60;

	// SCREEN SETTINGS:

	final static int originalTileSize = 16; // X by X size time (16 is default)
	final static int scale = 3; // Scaling is necesarry when the tiles are too small
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 12; // these choose how many tiles are on each row/column of the game screen
	public final static int tileSize = originalTileSize * scale; // actual tile size on the game screen
	public final static int screenWidth = tileSize * maxScreenCol;
	public final static int screenHeight = tileSize * maxScreenRow;
	
	// WORLD SETTINGS
	
	public final static int maxWorldCol = 18; // max amount of rows and columns for a map
	public final static int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public static String defaultMap = new File(System.getProperty("user.dir"), "maps/Clear.txt").getAbsolutePath(); //the starting map for the player
	
	// PLAYER SETTINGS:
	
	public static int playerX = tileSize * 8; // X value of default player location
	public static int playerY = tileSize * (maxWorldRow - 4); // Y value of default player location. Always 4 below the max limit.
	public static int screenX = tileSize * 7;  // X value of player default location on screen
	public static int screenY = tileSize * 8;  // Y value of player default location on screen
	public static int playerSpeed = 6; // This value will be added/subtracted from the player's location with every respective key input
	public static String direction = "idle";

}
