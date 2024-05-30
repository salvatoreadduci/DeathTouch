package Application.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import Application.config.Settings;
import Application.core.Game;
import Application.core.collider.collisionHandler;
import Application.core.enemy.EnemyHandler;
import Application.core.map.Block;
import Application.core.map.MapHandler;
import Application.menu.GameModePanel;
import Application.view.character.AttackView;
import Application.view.character.CharacterView;
import Application.view.enemy.EnemyView;
import Application.view.map.ImageStore;

public class RandomPanel extends JPanel{

	private static final long serialVersionUID = -1840881188790373806L;
	
	static AttackView av;
	static EnemyHandler eh;
	static Game gm;
	static ImageStore is;
	static MapHandler mh;
	static CharacterView cv;
	static collisionHandler ch;
	
	int blockSize;
	
	public RandomPanel() {
		blockSize = Settings.BLOCK_SIZE;
		instanceUpdate();
	}
	
	public static void instanceUpdate() {
		eh = EnemyHandler.getInstance();
		gm = Game.getInstance();
		is = ImageStore.getImageStore();
		mh = MapHandler.getInstance();
		cv = CharacterView.getInstance();
		ch = collisionHandler.getInstance(); 
		av = new AttackView();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gm.getCamera().followObject(gm.getCharacter().getX() + cv.getDimX()/2, gm.getCharacter().getY() + cv.getDimY()/2);
		g.translate(gm.getCamera().getCamX(), gm.getCamera().getCamY()); //follow
		g.setColor(Color.red);
		mapComponent(g);
		enemyComponent(g);
		attackComponent(g);
		characterComponent(g);
		g.drawString("Highscore: " + Settings.highScore, gm.getCharacter().getX() +50 - Settings.WINDOW_WIDTH/2, gm.getCharacter().getY() +95 - Settings.WINDOW_HEIGHT/2);
		g.drawString(Settings.name, gm.getCharacter().getX() +50 - Settings.WINDOW_WIDTH/2, gm.getCharacter().getY() +75 - Settings.WINDOW_HEIGHT/2);
	}
		
	private void mapComponent(Graphics g) {
		Block[][] map = mh.getMap();
			map = mh.getMap();
		for(int i = 0; i < mh.getWidth(); i++) {
			for(int j = 0; j < mh.getHeight(); j++) {
				boolean isIMajor = i * blockSize > gm.getCharacter().getX() - Settings.WINDOW_WIDTH/2;
				boolean isIMinor = i * blockSize < gm.getCharacter().getX() + Settings.WINDOW_WIDTH/2;
				boolean isJMajor = j * blockSize > gm.getCharacter().getY() - Settings.WINDOW_HEIGHT/2 + blockSize/2;
				boolean isJMinor = j * blockSize < gm.getCharacter().getY() + Settings.WINDOW_HEIGHT/2 - blockSize/2;
				if ( isIMajor &&  isIMinor && isJMinor && isJMajor ) {
					switch (map[i][j].getType()) {
				
					case Block.EMPTY:
						g.drawImage(is.background, i * blockSize, j * blockSize, blockSize, blockSize, null);
						break;
				
					case Block.FLOOR:
						g.drawImage(is.currentFloor, i * blockSize, j * blockSize, blockSize, blockSize, null);
						break;
					
					case Block.WALL_TOP:
						g.drawImage(is.wallImgTop,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
				
					case Block.WALL_LEFT:
						g.drawImage(is.wallImgLeft,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
					
					case Block.WALL_RIGHT:
						g.drawImage(is.wallImgRight,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
				
					case Block.WALL_DOWN:
						g.drawImage(is.wallImgDown,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
					
					case Block.WALL_DANGLEDX:
						g.drawImage(is.anglesx,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
						
					case Block.WALL_DANGLESX:
						g.drawImage(is.angledx,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
					
					case Block.WALL_TANGLEDX:
						g.drawImage(is.wallImgRight,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
						
					case Block.WALL_TANGLESX:
						g.drawImage(is.wallImgLeft,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
						
					case Block.OBSTACLE:
						g.drawImage(is.obstacleImg,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
						
					case Block.DOOR:
						g.drawImage(is.bridge,i* blockSize, j * blockSize, blockSize, blockSize, null);
						break;
	
					}
				}
			}
		}
	}
	
	private void characterComponent(Graphics g) {
		if(cv.getDir())
			g.drawImage(cv.getCurrentImage(), gm.getCharacter().getX(), gm.getCharacter().getY(), cv.getDimX(), cv.getDimY(), null);
		else
			g.drawImage(cv.getCurrentImage(), gm.getCharacter().getX() + cv.getDimX(), gm.getCharacter().getY(), -cv.getDimX(), cv.getDimY(), null);
	}
	
	private void enemyComponent(Graphics g) {
		for (int i = 0; i < eh.getEnemySize(); i++) {
			boolean isIMajor = eh.getEnemy(i).getX() > gm.getCharacter().getX() - Settings.WINDOW_WIDTH/2;
			boolean isIMinor = eh.getEnemy(i).getX() < gm.getCharacter().getX() + Settings.WINDOW_WIDTH/2;
			boolean isJMajor = eh.getEnemy(i).getY() > gm.getCharacter().getY() - Settings.WINDOW_HEIGHT/2;
			boolean isJMinor = eh.getEnemy(i).getY() < gm.getCharacter().getY() + Settings.WINDOW_HEIGHT/2;
			if ( isIMajor &&  isIMinor && isJMinor && isJMajor) {
				EnemyView ev = eh.getEnemyView(i);
				if (eh.getEnemy(i).isRight())
					g.drawImage(ev.getCurrentImage(), eh.getEnemy(i).getX(), eh.getEnemy(i).getY(), ev.getDimX(), ev.getDimY(), null); //enemyView render
				else
					g.drawImage(ev.getCurrentImage(), eh.getEnemy(i).getX() + ev.getDimX(), eh.getEnemy(i).getY(), -ev.getDimX(), ev.getDimY(), null);
			}
		}
	}
	
	private void attackComponent(Graphics g) {
		for (int i = 0; i < gm.getAttacks().size();i++) {
			g.drawImage(av.getImage(), gm.getAttacks().get(i).getX(), gm.getAttacks().get(i).getY(),av.getDimX(), av.getDimY(), null);
		}
	}
	
	public void update() {
		CharacterView.getInstance().update();
		eh.update();
		Game.getInstance().update();
		ch.update();
		repaint();
		Toolkit.getDefaultToolkit().sync();
	}
}
