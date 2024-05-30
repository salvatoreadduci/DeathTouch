package Application.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Application.config.Settings;
import Application.core.Game;
import Application.core.collider.Collider;
import Application.core.collider.CustomCollisionHandler;
import Application.core.collider.collisionHandler;
import Application.core.enemy.CustomEnemyHandler;
import Application.core.enemy.EnemyHandler;
import Application.core.map.Block;
import Application.core.map.CustomMapHandler;
import Application.core.map.MapHandler;
import Application.menu.GameModePanel;
import Application.view.character.AttackView;
import Application.view.character.CharacterView;
import Application.view.enemy.EnemyView;
import Application.view.map.ImageStore;

public class CustomPanel extends JPanel{

		private static final long serialVersionUID = -1840881188790373806L;
		
		AttackView av;
		CustomEnemyHandler ceh;
		Game gm;
		ImageStore is;
		CharacterView cv;
		CustomCollisionHandler cch;
		CustomMapHandler cm;
		GameModePanel gmp;
		
		int blockSize;
		
		public CustomPanel() {
			blockSize = Settings.BLOCK_SIZE;
			gm = Game.getInstance();
			is = ImageStore.getImageStore();
			cv = CharacterView.getInstance();
			cm = CustomMapHandler.getInstance();
			gmp = GameModePanel.getInstance();
			ceh = new CustomEnemyHandler();
			cch = new CustomCollisionHandler(ceh);
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
		}
		
		private void mapComponent(Graphics g) {
			Block[][] map = cm.getCustomMap();
				
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
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
			for (int i = 0; i < ceh.getEnemySize(); i++) {
				boolean isIMajor = ceh.getEnemy(i).getX() > gm.getCharacter().getX() - Settings.WINDOW_WIDTH/2;
				boolean isIMinor = ceh.getEnemy(i).getX() < gm.getCharacter().getX() + Settings.WINDOW_WIDTH/2;
				boolean isJMajor = ceh.getEnemy(i).getY() > gm.getCharacter().getY() - Settings.WINDOW_HEIGHT/2;
				boolean isJMinor = ceh.getEnemy(i).getY() < gm.getCharacter().getY() + Settings.WINDOW_HEIGHT/2;
				if ( isIMajor &&  isIMinor && isJMinor && isJMajor) {
					EnemyView ev = ceh.getEnemyView(i);
					if (ceh.getEnemy(i).isRight())
						g.drawImage(ev.getCurrentImage(), ceh.getEnemy(i).getX(), ceh.getEnemy(i).getY(), ev.getDimX(), ev.getDimY(), null); //enemyView render
					else
						g.drawImage(ev.getCurrentImage(), ceh.getEnemy(i).getX() + ev.getDimX(), ceh.getEnemy(i).getY(), -ev.getDimX(), ev.getDimY(), null);
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
			ceh.update();
			Game.getInstance().update();
			cch.update();
			repaint();
			Toolkit.getDefaultToolkit().sync();
		}
}

