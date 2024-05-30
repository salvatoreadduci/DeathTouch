package Application.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Application.core.Game;

public class AttackController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int MousePositionX = e.getX();
		int MousePositionY = e.getY();
		Game.getInstance().attack(MousePositionX, MousePositionY);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
