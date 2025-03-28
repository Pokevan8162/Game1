package main;

import object.objKey;

public class AssetController {
	
	GamePanel gp;
	
	public AssetController(GamePanel gp) {
		this.gp = gp;
	}
	
	public void putObject() {
		gp.obj[0] = new objKey();
		gp.obj[0].worldX = 23 * V.tileSize;
		gp.obj[0].worldY = 7 * V.tileSize;
		
		gp.obj[0] = new objKey();
		gp.obj[0].worldX = 25 * V.tileSize;
		gp.obj[0].worldY = 7 * V.tileSize;
	}

}
