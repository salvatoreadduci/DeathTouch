package Application.core;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import Application.GameLoop;
import Application.config.Settings;
import Application.control.AttackController;
import Application.control.ButtonHandler;
import Application.control.MovementController;
import Application.core.character.Character;
import Application.core.collider.collisionHandler;
import Application.core.enemy.EnemyHandler;
import Application.core.map.Block;
import Application.core.map.CustomMapHandler;
import Application.core.map.MapHandler;
import Application.core.map.Room;
import Application.menu.PanelHandler;
import Application.view.CustomPanel;
import Application.view.RandomPanel;
import Application.view.character.CharacterView;
import Application.core.character.Attack;

public class Game {
	
	Character character;
	Camera cam;
	MapHandler map;
	ArrayList<Attack> attacks;
	
	GameLoop gameLoop;
	Thread t;
	
	private static Game _instance = new Game();
	
	public static Game getInstance() {
		return _instance;
	}
	
	private Game() {
		character = new Character();
		cam = new Camera();
		character.setSpeed(10);
		attacks = new ArrayList<Attack>();
		Block[][] map = MapHandler.getInstance().getMap();
		end:
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) 
				if (map[i][j].getType() == Block.FLOOR ) {
					int offsetX = CharacterView.getInstance().getDimX()-Settings.BLOCK_SIZE;
					int offsetY = CharacterView.getInstance().getDimY()-Settings.BLOCK_SIZE;
					character.setPosition(i*Settings.BLOCK_SIZE-offsetX, j*Settings.BLOCK_SIZE-offsetY);
					break end;
				}
		}
		resetCharacter();
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public Camera getCamera() {
		return cam;
	}
	
	public MapHandler getMap() {
		return map;
	}
	
	public 	ArrayList<Attack> getAttacks(){
		return attacks;
	}
	
	public void deleteAttack(int idx) {
		if (attacks.size() > 0 && idx < attacks.size())
			attacks.remove(idx);
	}
	
	public void move() {
		ButtonHandler btn = ButtonHandler.getInstance();
		character.setY(character.getY() - character.getSpeed()*btn.getButton(0));
		character.setY(character.getY() + character.getSpeed()*btn.getButton(1));
		character.setX(character.getX() - character.getSpeed()*btn.getButton(2));
		character.setX(character.getX() + character.getSpeed()*btn.getButton(3));
	}
	
	public void Dead() {
		Settings.highScore = 0;
		resetCharacter();
		PanelHandler.setCurrent("GameOver");
	}
	
	public void spikeDeath() {
		Settings.highScore = 0;
		resetCharacter();
	}
	
	public void newLevelRandom() {
		MapHandler.getInstance().newGame();
		EnemyHandler.getInstance().newGame();
		collisionHandler.getInstance().newGame();
		RandomPanel.instanceUpdate();
		resetCharacter();
	}
	
	public void newGame(String nomeFile) {
		Settings.highScore = 0;
		ButtonHandler.getInstance().reset();
		if (Settings.GameMode == 0) {
			if (gameLoop != null)
				gameLoop.close();
			PanelHandler.remove("GamePanel");
			RandomPanel rap = new RandomPanel();
			rap.addKeyListener(new MovementController(rap));
			rap.addMouseListener(new AttackController());
			rap.setBackground(new Color(34,34,34));
			PanelHandler.add("GamePanel",rap);
			gameLoop = new GameLoop(rap);
			t = new Thread(gameLoop);
			t.start();
			Game.getInstance().newLevelRandom();
			PanelHandler.setCurrent("GamePanel");
		}else {
			if (gameLoop != null)
				gameLoop.close();
			PanelHandler.remove("CustomPanel");
			CustomMapHandler.getInstance().initMap(nomeFile);
			CustomPanel cp = new CustomPanel();
			cp.addKeyListener(new MovementController(cp));
			cp.addMouseListener(new AttackController());
			cp.setBackground(new Color(34,34,34));
			PanelHandler.add("CustomMode", cp);
			gameLoop = new GameLoop(cp);
			t = new Thread(gameLoop);
			t.start();
			PanelHandler.setCurrent("CustomMode");
			
		}
	}
	
	public void update() {
		move();
		for (int i = 0; i < attacks.size(); i++) {
			attacks.get(i).computeTrajectory();
		}
	}

	public void resetCharacter() {
		character.setX(10*Settings.BLOCK_SIZE);
		character.setY(37*Settings.BLOCK_SIZE);
	}
	
	public void attack(int mouseX, int mouseY) {
		if (attacks.size() < 3) {
			Attack att = new Attack(character.getX(), character.getY(), mouseX, mouseY);
			attacks.add(att);
		}
	}
}