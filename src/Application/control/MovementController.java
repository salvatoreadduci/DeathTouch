package Application.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Application.config.Settings;
import Application.core.Game;
import Application.core.enemy.EnemyHandler;
import Application.core.map.MapHandler;
import Application.menu.PanelHandler;
import Application.view.CustomPanel;
import Application.view.RandomPanel;
import Application.view.character.CharacterView;

public class MovementController implements KeyListener {
	
	ButtonHandler btn = ButtonHandler.getInstance();
	CharacterView cv = CharacterView.getInstance();
	
	private RandomPanel gp;
	private CustomPanel cp;
	
	public MovementController (RandomPanel panel) {
		gp = panel;
	}
	
	public MovementController (CustomPanel panel) {
		cp = panel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case (KeyEvent.VK_D):
		case (KeyEvent.VK_RIGHT):
			btn.setButton(3, 1);
			cv.move(true);
			break;
		case (KeyEvent.VK_A):
		case (KeyEvent.VK_LEFT):
			btn.setButton(2, 1);
			cv.move(false);
			break;
		case (KeyEvent.VK_W):
		case (KeyEvent.VK_UP):
			btn.setButton(0, 1);
			cv.move();
			break;
		case (KeyEvent.VK_S):
		case (KeyEvent.VK_DOWN):
			btn.setButton(1, 1);
			cv.move();
			break;
		case (KeyEvent.VK_ESCAPE):
	        if (Settings.isPause == false) {
	        	Settings.isPause = !Settings.isPause;
	        }
			PanelHandler.setCurrent("PausePanel");
	        break;
		case (KeyEvent.VK_CANCEL):
			System.exit(0);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case (KeyEvent.VK_D):
			case (KeyEvent.VK_RIGHT): btn.setButton(3, 0); break;
			case (KeyEvent.VK_W):
			case (KeyEvent.VK_UP): btn.setButton(0, 0); break;
			case (KeyEvent.VK_A):
			case (KeyEvent.VK_LEFT): btn.setButton(2, 0); break;
			case (KeyEvent.VK_S):
			case (KeyEvent.VK_DOWN): btn.setButton(1, 0); break;
		}	
		if(btn.verifyMovementButtons()) cv.stop();
	}
}
