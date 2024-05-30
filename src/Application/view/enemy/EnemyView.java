package Application.view.enemy;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Application.core.enemy.Enemy;

public class EnemyView {
	private ArrayList<Image> run;
	private ArrayList<Image> idle;
	
	Image currentImage;
	int dimX;
	int dimY;
	int frames;
	private boolean move;
	private boolean isRight;
	private int index;
	
	public EnemyView(String name, int frames) {
		run = new ArrayList<Image>();
		idle = new ArrayList<Image>();
		move = false;
		index = 0;
		this.frames = frames;
		for (int i = 0; i < frames; i++) {		
			try {
				Image img = ImageIO.read(getClass().getResourceAsStream("/Application/resources/"+ name +"_run_anim_f"+i+".png"));
				run.add(img);
				Image idl = ImageIO.read(getClass().getResourceAsStream("/Application/resources/"+ name +"_idle_anim_f"+i+".png"));
				idle.add(idl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		currentImage = run.get(0);
		dimX = currentImage.getWidth(null)*2;
		dimY = currentImage.getHeight(null)*2;
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
		index++;
		if (index >= frames)
			index = 0;
		if (move) {
			currentImage = run.get(index);
		} else {
			currentImage = idle.get(index);
		}
	}
	
    public Rectangle getBounds(int x, int y) {
    	int initX = x;
    	int initY = y+20;
        return new Rectangle(initX, initY, dimX, dimY);
    }
}
