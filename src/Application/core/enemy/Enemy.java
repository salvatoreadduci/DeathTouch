package Application.core.enemy;

public class Enemy {
	//Class of enemy
	public final static int BOSS = 0;
	public final static int BASE = 1;
	public final static int UPPER = 2;
	//Type of enemy
	public final static int DEMON = 0;
	public final static int ZOMBIE = 1;
	public final static int ORC = 2;
	
	int x;
	int y;
	int lastX;
	int attack;
	int life;
	int type;
	float speed;
	boolean isRight;

	public Enemy(int x, int y, int type) {
		this.x = x;
		this.y = y;
		lastX = x-1;
		isRight = true;
		speed = 1f;
		attack = 5;
		this.type = type;
		selectLife();
	}
	
	private void selectLife() {
		switch(type) {
		case Enemy.BASE: life = 1; break;
		case Enemy.UPPER: life = 2; break;
		case Enemy.BOSS: life = 3;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getLife() {
		return life;
	}
	
	public void lostLife() {
		life--;
	}
	
	public void wallTouched() {
		speed *= -1;
	}
	
	public boolean isRight() {
		return isRight;
	}
	
	public void computeTrajectory() {
		if (lastX < x) 
			isRight = true;
		else 
			isRight = false;
		lastX = x;
		if (type == BASE) {
			x = (int) (lastX + (5 * speed));
		}if (type == UPPER) {
			y = (int) (y + (5 * speed));
		}if (type == BOSS) {
			x = (int) (lastX + (10 * speed));
			y = (int) (y + (10 * speed));
		}
	}
}
