package Application.core;

import Application.config.Settings;

public class Camera {
	int camX;
	int camY;
	
	public Camera() {
		camX = 0;
		camY = 0;
	}
	
	public void followObject(int objectX, int objectY) {
		camX = (objectX - Settings.WINDOW_WIDTH /2) * -1;
		camY = (objectY - Settings.WINDOW_HEIGHT /2)* -1;
	}
	
	public int getCamX() {
		return camX;
	}
	
	public int getCamY() {
		return camY;
	}
}