package Application.core.map;

public class Info {

	private int id;
	private int startingX;
	private int startingY;
	private int endingX;
	private int endingY;
	
	public Info(int id, int startingX, int startingY, int endingX, int endingY) {
		this.id = id;
		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = endingX;
		this.endingY = endingY;
	}

	public int getId() {
		return id;
	}

	public int getStartingX() {
		return startingX;
	}

	public int getStartingY() {
		return startingY;
	}

	public int getEndingX() {
		return endingX;
	}

	public int getEndingY() {
		return endingY;
	}	
}
