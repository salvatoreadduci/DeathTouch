package Application.core.collider;

import java.awt.Rectangle;

import Application.config.Settings;

public class Collider {
	Rectangle bound;
	int blockX;
	int blockY;
	
	public Collider(int x, int y) {
		blockX = x;
		blockY = y;
		bound = new Rectangle (x*Settings.BLOCK_SIZE, y*Settings.BLOCK_SIZE, Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
	}
	
	public Rectangle getBound() {
		return bound;
	}
	
	public int getBlockX() {
		return blockX;
	}
	
	public int getBlockY() {
		return blockY;
	}
	
	public int getX() {
		return bound.x;
	}
	
	public int getY() {
		return bound.y;
	}
	
	public int getWidth() {
		return bound.width;
	}
	
	public int getHeight() {
		return bound.height;
	}
}
