package Application.core.collider;

import java.awt.Rectangle;
import java.util.ArrayList;

import Application.config.Settings;
import Application.control.ButtonHandler;
import Application.core.Game;
import Application.core.character.Attack;
import Application.core.enemy.CustomEnemyHandler;
import Application.core.enemy.Enemy;
import Application.core.map.Block;
import Application.core.map.CustomMapHandler;
import Application.core.map.Room;
import Application.view.character.AttackView;
import Application.view.character.CharacterView;
import Application.view.enemy.EnemyView;

public class CustomCollisionHandler {
	
	ArrayList<Collider> mapBounds;
	ArrayList<Collider> spikes;
	CharacterView cv;
	CustomEnemyHandler ceh;
	CustomMapHandler cmh;
	Game g;
	AttackView av;
	
	public CustomCollisionHandler(CustomEnemyHandler ceh) {
		mapBounds = new ArrayList<Collider>();
		spikes = new ArrayList<Collider>();
		cmh = CustomMapHandler.getInstance();
		createMapBounds();
		this.cv = CharacterView.getInstance();
		this.ceh = ceh;
		g = Game.getInstance();
		av = new AttackView();
	}
	
	public ArrayList<Collider> getmapbounds(){
		return mapBounds;
	}
	
	public void createMapBounds() {
		Block[][] map = cmh.getCustomMap();
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				Collider c = new Collider(i,j);
				switch(map[i][j].getType()) {
				case Block.OBSTACLE: 
					spikes.add(c);
					break;
				case Block.WALL_LEFT:
				case Block.WALL_RIGHT:
				case Block.WALL_DANGLEDX:
				case Block.WALL_DANGLESX:
				case Block.WALL_DOWN:
				case Block.WALL_TOP:
				case Block.WALL_TANGLEDX:
				case Block.WALL_TANGLESX:
					mapBounds.add(c);
					break;
				case Block.DOOR:
					if(i>1 && i < map.length)
						if (map[i-1][j].getType() == Block.DOOR && map[i+1][j].getType() == Block.DOOR) {
							if (map[i][j-1].getType() == Block.EMPTY)
								mapBounds.add(new Collider(i, j-1));
							if (map[i][j+1].getType() == Block.EMPTY)
								mapBounds.add(new Collider(i, j+1));
						}
					if (j>1 && j < map[i].length)
						if (map[i][j-1].getType() == Block.DOOR && map[i][j+1].getType() == Block.DOOR) {
							if (map[i-1][j].getType() == Block.EMPTY)
								mapBounds.add(new Collider(i, j-1));
							if (map[i+1][j].getType() == Block.EMPTY)
								mapBounds.add(new Collider(i, j+1));
						}
					break;
					
				}
				
				if (mapBounds != null && mapBounds.size() > 1)
					if (map[i][j].getType() == Block.DOOR && mapBounds.get(mapBounds.size()-1).blockX == i && mapBounds.get(mapBounds.size()-1).blockY == j)
						mapBounds.remove(mapBounds.size()-1);	
			}
	}
	
	public void checkCollisions() {
		checkAttacks();
		checkEnemies();
		checkMap();
	}
	
	private void checkAttacks() {
		Rectangle character = cv.getBounds(Game.getInstance().getCharacter().getX(), Game.getInstance().getCharacter().getY());
		if (g.getAttacks().size() > 0) {
			for (int j = 0; j < g.getAttacks().size(); j++) {
				Attack atk = g.getAttacks().get(j);
				Rectangle attack = av.getBounds(atk.getX(), atk.getY());
				for (int i = 0; i < ceh.getEnemySize(); i++) {
					EnemyView ev = ceh.getEnemyView(i);
					Enemy e = ceh.getEnemy(i);
					Rectangle enemies = ev.getBounds(e.getX(), e.getY());
					if (attack.intersects(enemies)) {
						e.lostLife();
						if (e.getLife() == 0)
							ceh.deleteEnemy(i);
						g.deleteAttack(j);
					}
				}
			}
		}
	}
	
	private void checkEnemies() {
		Rectangle character = cv.getBounds(Game.getInstance().getCharacter().getX(), Game.getInstance().getCharacter().getY());
		for (int i = 0; i < ceh.getEnemySize(); i++) {
			EnemyView ev = ceh.getEnemyView(i);
			Enemy e = ceh.getEnemy(i);
			Rectangle enemy = ev.getBounds(e.getX(), e.getY());
			if (ceh.getEnemy(i).getType() == Enemy.BASE || ceh.getEnemy(i).getType() == Enemy.UPPER) {
				enemy.setSize(enemy.width + 20, enemy.height + 20);
			}
			
			if (character.intersects(enemy)) { 
				g.Dead();
				break;
			}
			
			for (int j = 0; j < mapBounds.size(); j++)
				if (mapBounds.get(j).bound.intersects(enemy))
					e.wallTouched();
		}
	}
	
	private void checkMap() {
		Rectangle characterFoot = cv.getBoundGround(Game.getInstance().getCharacter().getX(), Game.getInstance().getCharacter().getY());
		for (int i = 0; i < mapBounds.size(); i++) {
			Rectangle bound = mapBounds.get(i).bound;
			if (bound.intersects(characterFoot)) {
				g.getCharacter().setPosition(g.getCharacter().getOldX(), g.getCharacter().getOldY());
				break;
			} else {
				g.getCharacter().setOldX(g.getCharacter().getX() + ButtonHandler.getInstance().getButton(2)*(g.getCharacter().getSpeed()) + ButtonHandler.getInstance().getButton(3)*(-g.getCharacter().getSpeed()));
				g.getCharacter().setOldY(g.getCharacter().getY() + ButtonHandler.getInstance().getButton(0)*(g.getCharacter().getSpeed()) + ButtonHandler.getInstance().getButton(1)*(-g.getCharacter().getSpeed()));
			}
		
			for (int j = 0; j < g.getAttacks().size(); j++) {
				Attack atk = g.getAttacks().get(j);
				Rectangle attack = av.getBounds(atk.getX(), atk.getY());
					if (attack.intersects(bound)) {
						g.deleteAttack(j);
					
				}
			}
		}

		for (int j = 0; j < spikes.size(); j++) {
			if (spikes.get(j).bound.intersects(characterFoot)) {
					g.Dead();
			}
		}	
	}
	
	public void update() {
		checkCollisions();
	}
}
