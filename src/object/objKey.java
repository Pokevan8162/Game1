package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objKey extends SuperObject {
	
	public objKey () {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Key_1.png"));
			
		} catch (IOException e) {
			
		}
	}

}
