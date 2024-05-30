package Application;

import javax.swing.JPanel;

import Application.MapEditor.MapViewPanel;
import Application.view.CustomPanel;
import Application.view.RandomPanel;

public class GameLoop implements Runnable{

	RandomPanel gp;
	CustomPanel cp;
	MapViewPanel mv;
	int frequency = 75;
	boolean running = true;
	
	public GameLoop(RandomPanel gp) {
		this.gp = (RandomPanel) gp;
	}
	
	public GameLoop(CustomPanel cp) {
		this.cp = cp;
	}
	
	public GameLoop(MapViewPanel mv) {
		this.mv = mv;
	}
	
	public void close() {
		running = false;
	}
	
	@Override
	public void run() {
		while(running) {
			if (gp != null)
				gp.update();
			else if (cp != null)
				cp.update();
			else if (mv != null)
				mv.repaint();
			try {
				Thread.sleep(frequency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}