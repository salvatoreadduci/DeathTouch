package Application.view.character;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Application.config.Settings;

public class CharacterView {

	private ArrayList<Image> run;
	private ArrayList<Image> idle;
	
	Image currentImage;
	int dimX;
	int dimY;
	private boolean move;
	private boolean isRight;
	private boolean isChanged = true;
	private int index;
	
	private static CharacterView _instance = new CharacterView();
	
	public static CharacterView getInstance() {
		return _instance;
	}
	
	private CharacterView() {
		run = new ArrayList<Image>();
		idle = new ArrayList<Image>();
		dimX = 32;
		dimY = 56;
		move = false;
		changeCharacter();
		currentImage = run.get(0);
	}
	
	
	public void changeCharacter() {
		isChanged = false;
		run.clear();
		idle.clear();
		index = 0;
		for (int i = 0; i < 4; i++) {		
			try {
				Image img = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + Settings.clas + "_run_anim_f"+i+".png"));
				run.add(img);
				Image idl = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + Settings.clas + "_idle_anim_f"+i+".png"));
				idle.add(idl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		isChanged = true;
	}
	
	public boolean getDir() {
		return isRight;
	}
	
	public Image getCurrentImage() {
		return currentImage;
	}
	
	public int getDimX() {
		return dimX;
	}
	
	public int getDimY() {
		return dimY;
	}
	
	public void move(boolean direction) {
		isRight = direction;
		move = true;
	}
	
	public void move() {
		move = true;
	}
	
	public void stop() {
		move = false;
	}
	
	public void update() {
		if (isChanged) {
			index++;
			if (index >= 4)
				index = 0;
			if (move) {
				currentImage = run.get(index);
			} else {
				currentImage = idle.get(index);
			}
		}
	}
		
    public Rectangle getBounds(int x, int y) {
        return new Rectangle(x+4, y+20, dimX-10, dimY-26);
    }
    
    public Rectangle getBoundGround(int x, int y) {
    	return new Rectangle(x+4, y+(dimY-10), dimX-10, dimY/4);
    }
}