package Application.core.map;

public class Block {

	public final static char EMPTY = '0';
	public final static char FLOOR = '1';
	public final static char WALL_TOP = '2';
	public final static char WALL_LEFT= '3';
	public final static char WALL_RIGHT= '4';
	public final static char WALL_DOWN = '5';
	public final static char WALL_DANGLEDX = '6';
	public final static char WALL_DANGLESX = '7';
	public final static char WALL_TANGLEDX = '8';
	public final static char WALL_TANGLESX = '9';
	public final static char OBSTACLE = 'A';
	public final static char DOOR = 'B';
	
	
	int x;
	int y;
	char type;
	
	public Block(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}