package Application.core.enemy;

import java.util.ArrayList;

import Application.config.Settings;
import Application.core.map.Block;
import Application.core.map.CustomMapHandler;
import Application.core.map.MapHandler;
import Application.core.map.Room;
import Application.view.enemy.EnemyView;

public class CustomEnemyHandler {
	
	ArrayList<Enemy> enemies;
	ArrayList<EnemyView> enemiesView;
	CustomMapHandler cmh = CustomMapHandler.getInstance();
	int maxEnemies;
	
	public CustomEnemyHandler() {
		cmh = CustomMapHandler.getInstance();
		enemies = new ArrayList<Enemy>();
		enemiesView = new ArrayList<EnemyView>();
		Spawn();
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
		enemies.remove(idx);
		enemiesView.remove(idx);
	}
	
	public void update() {
		for (int i = 0; i < enemiesView.size(); i++) {
			enemies.get(i).computeTrajectory();
			enemiesView.get(i).update();
		}
	}

	public void Spawn() {
		Block[][] map = cmh.getCustomMap();
		int numFloors = 0;
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j].getType() == Block.FLOOR)
					numFloors++;
		int enemies = random(0,(int) (numFloors / Settings.multiplicatorCustom));
			while(enemies > 0) {
				int randX = random(0, map.length);
				int randY = random(0, map[0].length);
				if (map[randX][randY].getType() == Block.FLOOR) {
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