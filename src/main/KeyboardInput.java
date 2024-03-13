package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	
	public boolean upPress, downPress, rightPress, leftPress;

	@Override
	public void keyTyped(KeyEvent e) {  //we dont use this at all
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); //returns number of key that was pressed
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPress = true;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPress = true;
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPress = true;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPress = true;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPress = false;
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPress = false;
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPress = false;
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPress = false;
		}
		
	}

	
	
}
