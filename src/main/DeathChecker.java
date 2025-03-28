package main;

import entity.Entity;

public class DeathChecker {
	
	GamePanel gp;
	
	public DeathChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) { //determines if the edges the hitbox clash with the painted area of a tile with collision
		
		int entityLeftWorldX = entity.worldX + entity.hitbox.x;
		int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
		int entityTopWorldY = entity.worldY + entity.hitbox.y;
		int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;
		
		// this gets the relative position of the hitbox
		
		int entityLeftCol = entityLeftWorldX/V.tileSize;
		int entityRightCol = entityRightWorldX/V.tileSize;
		int entityTopRow = entityTopWorldY/V.tileSize;
		int entityBottomRow = entityBottomWorldY/V.tileSize;
		
		// this gets the relative column and row position of the hitbox
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/V.tileSize; // here we almost predict where the entity will be after he is moved
			tileNum1 = gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityTopRow]; // this checks the very top edge of the hitbox
			if (gp.tm.tile[tileNum1].death == true || gp.tm.tile[tileNum2].death == true) { // if the hitbox top edge values touch a tile that the player wants to move onto that has collision,
				entity.death = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/V.tileSize;
			tileNum1 = gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityBottomRow];
			if (gp.tm.tile[tileNum1].death == true || gp.tm.tile[tileNum2].death == true) {
				entity.death = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/V.tileSize;
			tileNum1 = gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityBottomRow];
			if (gp.tm.tile[tileNum1].death == true || gp.tm.tile[tileNum2].death == true) {
				entity.death = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/V.tileSize;
			tileNum1 = gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityTopRow];
			tileNum2 = gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityBottomRow];
			if (gp.tm.tile[tileNum1].death == true || gp.tm.tile[tileNum2].death == true) {
				entity.death = true;
			}
			break;
		case "idle":
			entityTopRow = (entityTopWorldY - entity.speed)/V.tileSize;
			tileNum1 = gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityTopRow];
			if (gp.tm.tile[tileNum1].death == true || gp.tm.tile[tileNum2].death == true) {
				entity.death = true;
			}
			break;
		}
	}

}
