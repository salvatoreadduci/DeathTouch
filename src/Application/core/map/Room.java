package Application.core.map;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import Application.config.Settings;

public class Room {
	
	private Block[][] blocks;
	private int width;
	private int height;
	private int X;
	private int Y;
	private int id;
	private int blockSize = Settings.BLOCK_SIZE;
	
	public Room(int X, int Y) {
		this.id = Settings.ID_ROOM++;		
		this.width = setWidth();
		this.height = setHeight();
		this.blocks = new Block[width][height];
		this.X = X;
		this.Y = Y;
		initRoom();
	}

	public int getId() {
		return id;
	}

	private void initRoom() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < blocks[i].length; j++) {
				
				if(i == 0 &&  j != height-1 && j != 0) {
					blocks[i][j] = new Block(i, j, Block.WALL_LEFT);
				}else if(i == width-1 && j == height-1)
					blocks[i][j] = new Block(i, j, Block.WALL_DANGLEDX);
				
				else if(j == height-1 && i == 0)
					blocks[i][j] = new Block(i, j, Block.WALL_DANGLESX);
				
				else if(i == width-1 && j != height-1 && j != 0) {
					blocks[i][j] = new Block(i, j, Block.WALL_RIGHT);
				}else if(j == 0 && i == width-1) {
					blocks[i][j] = new Block(i, j, Block.WALL_TANGLEDX);
				}else if(i == 0 && j == 0) {
					blocks[i][j] = new Block(i, j, Block.WALL_TANGLESX);
				}else if(j == 0) {
					blocks[i][j] = new Block(i, j, Block.WALL_TOP);
				}else if(j == height-1 && i != 0) {
					blocks[i][j] = new Block(i, j, Block.WALL_DOWN);
				}else
					blocks[i][j] = new Block(i, j, Block.FLOOR);
			}
		}
		
		generateObstacles();
	}
	
	private void generateObstacles() {
		Random r = new Random();
		int x = 40, numBlocks = r.nextInt(x)/2;
		
		for(int i = 0; i < numBlocks; i++) {
			int temp1 = r.nextInt(width);
			int temp2 = r.nextInt(height);
			
			if(blocks[temp1][temp2].getType() == Block.FLOOR) {
				blocks[temp1][temp2].setType(Block.OBSTACLE);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	private int setWidth() {
		
		Random r = new Random();
		int x = r.nextInt(50)+20;
		return x; 
	}
	
	private int setHeight() {
		
		Random r = new Random();
		int y = r.nextInt(50)+20;
		return y;
	}
	
	public int getPosX() {
		return X;
	}
	
	public int getPosY() {
		return Y;
	}

	public Block[][] getBlocks() {
		return blocks;
	}
}
