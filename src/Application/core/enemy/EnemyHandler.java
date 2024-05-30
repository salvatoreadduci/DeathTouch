package Application.core.enemy;

import java.util.ArrayList;

import Application.config.Settings;
import Application.core.Game;
import Application.core.map.Block;
import Application.core.map.MapHandler;
import Application.core.map.Room;
import Application.view.character.CharacterView;
import Application.view.enemy.EnemyView;

public class EnemyHandler {
	
	ArrayList<Enemy> enemies;
	ArrayList<EnemyView> enemiesView;
	MapHandler mh = MapHandler.getInstance();
	public float maxEnemies;
	
	private static EnemyHandler _instance = new EnemyHandler();
	
	public static EnemyHandler getInstance() {
		return _instance;
	}
	
	private EnemyHandler() {
		mh = MapHandler.getInstance();
		enemies = new ArrayList<Enemy>();
		enemiesView = new ArrayList<EnemyView>();
		roomSpawn();
	}
	
	public void newGame() {
		_instance = new EnemyHandler();
	}
	
	public Enemy getEnemy(int idx) {
		return enemies.get(idx);
	}
	
	public EnemyView getEnemyView(int idx) {
		return enemiesView.get(idx);
	}
	
	public int getEnemySize() {
		return enemies.size();
	}
	
	public void addEnemy(Enemy e, EnemyView ev) {
		enemies.add(e);
		enemiesView.add(ev);
	}

	public void deleteEnemy(int idx) {
		switch(enemies.get(idx).type) {
		case Enemy.BOSS:
			Settings.highScore ++;
		case Enemy.UPPER:
			Settings.highScore ++;
		case Enemy.BASE:
			Settings.highScore ++;
		}
		enemies.remove(idx);
		enemiesView.remove(idx);
	}
	
	public void update() {
		for (int i = 0; i < enemiesView.size(); i++) {
			enemies.get(i).computeTrajectory();
			enemiesView.get(i).update();
		}
		if ((enemies.size()/maxEnemies) < Settings.percentageCompletion)
			Game.getInstance().newLevelRandom();
	}

	public void roomSpawn() {
		Block[][] map = mh.getMap();
		for (int i = 0; i < mh.getRoomSize(); i++) {
			int enemies = random(Settings.minRandom,Settings.maxRandom);
			while(enemies > 0) {
				Room room = mh.getRoom(i);
				int randX = random(room.getPosX()+2, room.getWidth()-2);
				int randY = random(room.getPosY()+2, room.getHeight());
				if (map[randX][randY].getType() == Block.FLOOR && checkEnemy(randX, randY)) {
					int classe = random(0,3);
					EnemyView enemyView = new EnemyView(viewChooser(classe, random(0,3)), 4);
					int offsetX = offset(enemyView.getDimX());
					int offsetY = offset(enemyView.getDimY());
					Enemy enemy = new Enemy(randX*Settings.BLOCK_SIZE-offsetX,  randY*Settings.BLOCK_SIZE-offsetY, classe);
					addEnemy(enemy, enemyView);
					enemies--;
				}
			}
		}
		maxEnemies = enemies.size();
	}

	
	private int offset(int value) {
		return value - Settings.BLOCK_SIZE;
	}
	

	private boolean checkEnemy(int x, int y) {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getX() == x && enemies.get(i).getY() == y)
				return false;
		}
		return true;
	}
	
	private String viewChooser(int classe, int type) {
		switch(type) {
		case Enemy.DEMON:
			switch(classe) {
				case Enemy.BASE:
					return "imp";
				case Enemy.UPPER:
					return "chort";
				case Enemy.BOSS:
					return "big_demon";		
			}
			break;
		case Enemy.ORC:
			switch(classe) {
				case Enemy.BASE:
					return "goblin";
				case Enemy.UPPER:
					return "orc_warrior";
				case Enemy.BOSS:
					return "ogre";	
			}
			break;
		case Enemy.ZOMBIE:
			switch(classe) {
				case Enemy.BASE:
					return "tiny_zombie";
				case Enemy.UPPER:
					return "ice_zombie";
				case Enemy.BOSS:
					return "big_zombie";	
			}
		}		
		return "imp";
	}
	
	
	private int random(int min, int max) {
		return (int) (max*Math.random() + min);
	}
	
}