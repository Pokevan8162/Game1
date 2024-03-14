package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	KeyboardInput ki = new KeyboardInput();
	Thread gameThread;
	public Player player = new Player(this, ki);
	TileManager tm = new TileManager(this);


	public GamePanel() {

		this.setPreferredSize(new Dimension(V.screenWidth, V.screenHeight)); // set original size of the window
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves game rendering performance
		this.addKeyListener(ki);
		this.setFocusable(true); // this allows GamePanel to be "focused" to recieve key input (like clicking on a tab so it knows to be typed on)

	}

	public void startGameThread() {

		gameThread = new Thread(this); // this = GamePanel class
		gameThread.start(); // automatically calls the run method
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/V.FPS; // 0.0167 seconds if FPS = 60
		double nextDrawTime = System.nanoTime() + drawInterval;
		long currTime;
		long lastTime = System.nanoTime();
		// long currTime = System.currentTimeMillis(); // This is less precise
		long timer = 0;
		int drawCount = 0; //these two variables display FPS

		while (gameThread != null) {
			
			currTime = System.nanoTime();
			timer += (currTime - lastTime);
			lastTime = currTime;
			
			// 1. Update information (character position, etc)
			// 2. Draw the screen with the updated information
			// Happens as many FPS there are
			update();
			repaint();
			drawCount++;
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			// This system calculates how much time is left after a frame is processed. If there is time left after processing,
			// then wait until the next frame SHOULD happen. If the frame processing takes more than the interval, then continue processing
			// asap. This is the sleep method. The second method is the delta or accumulator method, found at: 
			// https://www.youtube.com/watch?v=VpH33Uw-_0E&list=RDCMUCS94AD0gxLakurK-6jnqV1w&start_radio=1&rv=VpH33Uw-_0E&t=4 28:25
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime(); // This is used to let the thread sleep while nothing happens
				remainingTime = remainingTime/1000000; // This makes remainingTime a millisecond
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void update() {
		
		player.update();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; // Changes Graphics g to Graphics2D class. 2D has a bit more functions
		
		tm.draw(g2);  // Make sure this is written before player so player is above it
		player.draw(g2);

		g2.dispose();
	}
}
