package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	
	public boolean upPress, downPress, rightPress, leftPress, showFPS;
	public boolean idle = true;
	public boolean stopPlayer = false;

	@Override
	public void keyTyped(KeyEvent e) {  //we dont use this at all
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); //returns number of key that was pressed
	
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPress = true;
			idle = false;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPress = true;
			idle = false;
		}
	
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPress = true;
			idle = false;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPress = true;
			idle = false;
		}
		if (code == KeyEvent.VK_MINUS) {
			if (showFPS == false) {
				showFPS = true;
			} else {
				showFPS = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPress = false;
			idle = true;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPress = false;
			idle = true;
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPress = false;
			idle = true;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPress = false;
			idle = true;
		}
		
	}

	
	
}
