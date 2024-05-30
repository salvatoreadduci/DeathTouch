package Application.MapEditor;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Application.config.Settings;
import Application.core.map.Block;


	public class MapCreator{
		
		private static MapCreator instance = null;
		private Block[][] map;
		private int width = Settings.MAP_WIDTH / Settings.BLOCK_SIZE;
		private int height = Settings.MAP_HEIGHT / Settings.BLOCK_SIZE;
		
		private MapCreator() {
			map = new Block[width][height];
			initializeMap();
		}
		
		public static MapCreator getInstance() {
			if (instance == null) {
				instance = new MapCreator();
			}
			return instance;
		}
		
		public Block[][] getMap() {
			return map;
		}
		
		private void initializeMap() {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					map[i][j] = new Block(i,j,Block.EMPTY);
				}
			}
			int h = 50, w = 20;
		       
	        for(int i = 1; i < w; i++) {
	            for(int j = 30; j < h ; j++) {
	                if(i == 1 &&  j != h-1 && j != 1)
	                    map[i][j] = new Block(i, j, Block.WALL_LEFT);
	               
	                else if(i == w-1 && j == h-1)
	                    map[i][j] = new Block(i, j, Block.WALL_DANGLEDX);
	               
	                else if(j == h-1 && i == 1)
	                    map[i][j] = new Block(i, j, Block.WALL_DANGLESX);
	               
	                else if(j == 30 && i == w-1)
	                    map[i][j] = new Block(i, j, Block.WALL_TANGLEDX);
	               
	                else if(i == 1 && j == 30)
	                    map[i][j] = new Block(i, j, Block.WALL_TANGLESX);
	               
	                else if(i == w-1 && j != h-1 && j != 1)
	                    map[i][j] = new Block(i, j, Block.WALL_RIGHT);
	               
	                else if(j == 30)
	                    map[i][j] = new Block(i, j, Block.WALL_TOP);
	               
	                else if(j == h-1 && i != 1)
	                    map[i][j] = new Block(i, j, Block.WALL_DOWN);
	               
	                else
	                    map[i][j] = new Block(i, j, Block.FLOOR);
	            }
	        }
		}	
}
