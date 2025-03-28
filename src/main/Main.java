package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import tile.MapManager;

public class Main {
	
	// Roadmap:
	// Menu where player chooses easy, hard, and PVE modes (PVE has Enemies to shoot with very little obstacles)
	// Score file that stores player top scores that is accessed at the main menu, also sectioned by modes as described before
	// Blaster powerup that destroys objects in a circle area where it lands
	// Teleport powerup (like tracer) that teleports the player in their chosen direction 2-3 tiles, leaving behind a blue effect and a sound
	// Invincibility powerup that disables collision for the player temporarily
	// Add up right, up left, down left, down right movement. make down movement go faster than the scroll speed. essentially allow for double movement instead of movement locking. mayube add a direction2 string?
	// Unlockable player skins, instant abilities, infinite abilities, save files so the player can save these skins and abilities, new modes, etc
	// Bugs:
	// Bouncing mechanic needs work. Could start by adding a down bounce mechanic if the player bounces downward into another bouncy ball

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle(V.gameTitle);

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack(); // makes this window sized to fit the preferred size set in the GamePanel constructor
		window.setLocationRelativeTo(null); // not specified = center of the screen
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		    	MapManager.deleteMaps();
		    	window.dispose();
		        System.exit(0);
		    }
		});

		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}