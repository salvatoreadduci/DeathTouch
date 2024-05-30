package Application.config;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {

	//DIMENSIONS
	public final static int MAP_WIDTH = 5000;
	public final static int MAP_HEIGHT = 3000;
	public static int WINDOW_WIDTH = 800;
	public static int WINDOW_HEIGHT = 800;
	
	//BLOCKSIZES
	public final static int BLOCK_SIZE = 30;
	public final static int BLOCK_SIZE_EDITOR = 10;
	public final static int BLOCK_SIZE_EDITORMENU = 50;
	
	public static int ID_ROOM = 0;
	public final static String resLoc = "/Application/resources/";
	public static boolean isPause = false;
	
	//Character information
	public static String clas = "elf_m";
	public static String name = " ";
	public static int highScore = 0;
	
	//Enemy spawn Options
	public final static float percentageCompletion = 0.10f;
	public final static int minRandom = 10;
	public final static int maxRandom = 15;
	public final static float multiplicatorCustom = 20f;
	
	//GM 0 = random 1 = custom
	public static int GameMode = 0;
	public static String fileRandom = " ";
	
	//Change the resolution of the window at fullscreen
	public static void controlresolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Settings.WINDOW_WIDTH = (int) screenSize.getWidth();
		Settings.WINDOW_HEIGHT = (int) screenSize.getHeight();
	}
	
	public static void menuResolution() {
		WINDOW_WIDTH = 800;
		WINDOW_HEIGHT = 850;
	}
}
