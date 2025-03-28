package main;

import entity.Entity;

public class BounceCollisionChecker {
	
	GamePanel gp;
	
	public BounceCollisionChecker(GamePanel gp) {
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
		
		String tileNum1 = null, tileNum2 = null;

		switch (gp.player.bounceDirection) {
		case "left":
			entityLeftCol = (entityLeftWorldX - gp.player.bounceDistance)/V.tileSize;  // This makes sure there is distance enough for a bounce to occur
			try {
				tileNum1 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityTopRow]);
			} catch (Exception e) {
			}
			try {
				tileNum2 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityBottomRow]);
			} catch (Exception e) {
			}
			if (tileNum1 != null && tileNum2 != null) {  // the whole try catch and null system here is to prevent bounce tiles from being checked outside the world
				if (gp.tm.tile[Integer.parseInt(tileNum1)].collision == true || gp.tm.tile[Integer.parseInt(tileNum2)].collision == true) {
					entity.leftBounceCollision = true;
				}
				if (gp.tm.tile[Integer.parseInt(tileNum1)].death == true || gp.tm.tile[Integer.parseInt(tileNum2)].death == true) {
					entity.death = true;
				}
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + gp.player.bounceDistance)/V.tileSize;
			try {
				tileNum1 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityTopRow]);
			} catch (Exception e) {
			}
			try {
				tileNum2 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityBottomRow]);
			} catch (Exception e) {
			}
			if (tileNum1 != null && tileNum2 != null) {
				if (gp.tm.tile[Integer.parseInt(tileNum1)].collision == true || gp.tm.tile[Integer.parseInt(tileNum2)].collision == true) {
					entity.rightBounceCollision = true;
				}
				if (gp.tm.tile[Integer.parseInt(tileNum1)].death == true || gp.tm.tile[Integer.parseInt(tileNum2)].death == true) {
					entity.death = true;
				}
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + gp.player.bounceDistance)/V.tileSize;
			try {
				tileNum1 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityBottomRow]);
			} catch (Exception e) {
			}
			try {
				tileNum2 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityBottomRow]);
			} catch (Exception e) {
			}
			if (tileNum1 != null && tileNum2 != null) {
				if (gp.tm.tile[Integer.parseInt(tileNum1)].collision == true || gp.tm.tile[Integer.parseInt(tileNum2)].collision == true) {
					entity.downBounceCollision = true;
				}
				if (gp.tm.tile[Integer.parseInt(tileNum1)].death == true || gp.tm.tile[Integer.parseInt(tileNum2)].death == true) {
					entity.death = true;
				}
			}
			break;
		case "idle":
			try {
				tileNum1 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityLeftCol][entityBottomRow]);
			} catch (Exception e) {
			}
			try {
				tileNum2 = Integer.toString(gp.tm.maps.get(gp.tm.mapIndex)[entityRightCol][entityBottomRow]);
			} catch (Exception e) {
			}
			entityBottomRow = (entityBottomWorldY + gp.player.bounceDistance)/V.tileSize;
			if (tileNum1 != null && tileNum2 != null) {
				if (gp.tm.tile[Integer.parseInt(tileNum1)].collision == true || gp.tm.tile[Integer.parseInt(tileNum2)].collision == true) {
					entity.downBounceCollision = true;
				}
				if (gp.tm.tile[Integer.parseInt(tileNum1)].death == true || gp.tm.tile[Integer.parseInt(tileNum2)].death == true) {
					entity.death = true;
				}
			}
			break;
		}
	}
}
