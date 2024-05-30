package Application.core.character;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Application.config.Settings;
import Application.view.character.CharacterView;

public class Attack {

	int realX;
	int realY;
	int moveX;
	int moveY;
	int m;
	int q;
	double speed;
	
	public Attack(int realX, int realY, int endX,int endY) {
		this.realX = realX + CharacterView.getInstance().getDimX()/2; //Punto iniziale dell'attacco
		this.realY = realY + CharacterView.getInstance().getDimY()/2;
		moveX = endX - Settings.WINDOW_WIDTH/2; //Vettore di direzione
		moveY = endY - Settings.WINDOW_HEIGHT/2;
		speed = 0.05;
	}
	
	public int getX() {
		return realX;
	}
	public int getY() {
		return realY;
	}
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(int s){
		speed = s;
	}
	
	//Al passare del tempo l'attacco si muove tramite il vettore di direzione (Come sono pazzi questi olandesi - cit.)
	public void computeTrajectory() {
		realX = (int) (realX + (moveX * speed));
		realY = (int) (realY + (moveY * speed));
	}
}