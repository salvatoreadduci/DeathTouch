package Application.core.map;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import Application.config.Settings;
import Application.core.Game;
import Application.core.enemy.EnemyHandler;

public class MapHandler {

	ArrayList<Room> rooms;
	private Block[][] map;
	private int width;
	private int height;
	
	private static MapHandler _instance = new MapHandler();
	
	public static MapHandler getInstance() {
		return _instance;
	}
	
	public void newGame() {
		_instance = new MapHandler();
	}
	
	private MapHandler() {
		this.width = Settings.MAP_WIDTH/Settings.BLOCK_SIZE;
		this.height = Settings.MAP_HEIGHT/Settings.BLOCK_SIZE;
		map = new Block[width][height];
		rooms = new ArrayList<Room>();
		
		initMap();
	}
	
	private void initMap() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height ; j++) {
				map[i][j] = new Block(i, j, Block.EMPTY);
			}
		}
		
		//spawn room
		int h = 50, w = 20;
		int blockSize = Settings.BLOCK_SIZE;
		for(int i = 1; i < w; i++) {
			for(int j = 30; j < h ; j++) {
				if(i == 1 &&  j != h-1 && j != 1) {
					map[i][j] = new Block(i, j, Block.WALL_LEFT);
				}else if(i == w-1 && j == h-1)
					map[i][j] = new Block(i, j, Block.WALL_DANGLEDX);
				
				else if(j == h-1 && i == 1)
					map[i][j] = new Block(i, j, Block.WALL_DANGLESX);
				
				else if(j == 30 && i == w-1) {
					map[i][j] = new Block(i, j, Block.WALL_TANGLEDX);
				}else if(i == 1 && j == 30) {
					map[i][j] = new Block(i, j, Block.WALL_TANGLESX);
				}else if(i == w-1 && j != h-1 && j != 1) {
					map[i][j] = new Block(i, j, Block.WALL_RIGHT);
				}else if(j == 30) {
					map[i][j] = new Block(i, j, Block.WALL_TOP);
				}else if(j == h-1 && i != 1) {
					map[i][j] = new Block(i, j, Block.WALL_DOWN);
				}else
					map[i][j] = new Block(i, j, Block.FLOOR);
			}
		}
		
		Random r = new Random();
		int nRooms = r.nextInt(4)+3;
		
		for(int i = 1; i <= nRooms; ) {
			if(generateRooms() == true)
				i++;
		}
		generateDoors();
		verifyObstacles();
	}

	private void verifyObstacles() {
		for (int i = 1; i < map.length-1; i++) {
			for (int j = 1; j < map[i].length-1; j++) {
				int type = map[i][j].getType();
				if (map[i-1][j].getType() == Block.DOOR && type == Block.OBSTACLE) map[i][j].setType(Block.FLOOR);
				if (map[i+1][j].getType() == Block.DOOR && type == Block.OBSTACLE) map[i][j].setType(Block.FLOOR);
				if (map[i][j-1].getType() == Block.DOOR && type == Block.OBSTACLE) map[i][j].setType(Block.FLOOR);
				if (map[i][j+1].getType() == Block.DOOR && type == Block.OBSTACLE) map[i][j].setType(Block.FLOOR);	
			}
		}
		
	}

	private boolean generateRooms() {
		
		
		int x = Randomx();
		int y = RandomY();
		Room room = new Room(x,y);
		Block[][] temp = room.getBlocks();
		
			
		if(check(room, x, y)  == true) {
			for(int i = x, a = 0; i < room.getWidth()+x; i++, a++) {
				for(int j = y, b = 0; j < room.getHeight()+y; j++, b++) {
						map[i][j].setType(temp[a][b].getType());
				}	
			}

			rooms.add(room);
			return true;
		}
		
		return false;
	}

	private void generateDoors() {
		
		ArrayList<Integer> distance2 = new ArrayList<Integer>();
		for(int i = 0; i < width; i++) {
			int distance = 0;
			
			for(int j = 0; j < height; j++) {
				
				if(map[i][j].getType() == Block.WALL_RIGHT) {
					
					
					for(int x = i+1; x < width; x++) {
						
						if(map[x][j].getType() == Block.WALL_RIGHT || map[x][j].getType() == Block.WALL_DANGLESX || map[x][j].getType() == Block.WALL_TANGLESX || map[x][j].getType() == Block.WALL_TOP)
							break;
						
						if(map[x][j].getType() == Block.WALL_LEFT) {
							
							if(distance == 0 || distance != x) {
								distance = x;
								map[x][j].setType(Block.DOOR);
								map[i][j].setType(Block.DOOR);
								generateCorridors(i, j, distance, true);
								break;
							}
						}
					}
				
				}
			
				if(map[i][j].getType() == Block.WALL_DOWN) {
		
					for(int x = j+1; x < height; x++) {
						
						if(map[i][x].getType() == Block.WALL_DOWN || map[i][x].getType() == Block.WALL_DANGLESX || map[i][x].getType() == Block.WALL_DANGLEDX) {
							distance2.clear();
							break;
						}
							
						
						if(map[i][x].getType() == Block.WALL_TOP) {
							
							if(distance2.isEmpty() || !distance2.contains(x)) {
								distance2.add(x);
								map[i][x].setType(Block.DOOR);
								map[i][j].setType(Block.DOOR);
								generateCorridors(i, j, distance2.get(distance2.size()-1), false);
								break;
	
							}
							
							else
								break;
						}
					}
				
				}
				
				
			}
			
			
		}
}


	private void generateCorridors(int i, int j , int x, boolean b) {
		int blockSize = Settings.BLOCK_SIZE;
		if(b == true) {
			while(i < x) {
				
				map[i][j].setType(Block.DOOR);
				i++;
			}
		}
		
		else if (b == false) {
			while(j < x) {
				map[i][j].setType(Block.DOOR);
				j++;
			}
		}
	}
	
	private int Randomx() {
		Random r = new Random();
		int x = r.nextInt(width)%width;
		return x;
	}
	
	private int RandomY() {
		
		Random r = new Random();
		int y = r.nextInt(height)%height;
		return y;
	}
	
	private boolean check(Room room, int x, int y) {
		if(x + room.getWidth() < width-1 && y + room.getHeight() < height-1) {	
			for(int i = x; i < room.getWidth()+x; i++) {
				for(int j = y; j < room.getHeight()+y; j++) {
		
					if(map[i][j].getType() != Block.EMPTY)
						return false;
				}
			}
		
			return true;
		}
		
		else
			return false;
	}
	
	public Block[][] getMap() {
		return map;
	}
	
	public int getTypeSize(int type) {
		int sum = 0;
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j].type == type)
					sum++;
		return sum;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getRoomSize() {
		return rooms.size();
	}

	public Room getRoom(int idx) {
		return rooms.get(idx);
	}
}
