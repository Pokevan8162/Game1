package main;

import javax.swing.JFrame;

public class Main {
	
	//CurrEpisode = https://www.youtube.com/watch?v=Ny_YHoTYcxo&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=6

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

		gamePanel.startGameThread();
	}
}
