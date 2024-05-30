package Application.view.map;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import Application.config.Settings;

public class ImageStore {

	private static ImageStore imageStore = null;
	public Image currentFloor;
	public Image obstacleImg;
	public Image wallImgTop;
	public Image wallImgLeft;
	public Image wallImgRight;
	public Image wallImgDown;
	public Image background;
	public Image angledx;
	public Image anglesx;
	public Image bridge;
	
	private ImageStore() {
			try {
				 currentFloor = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"floor_1.png"));
				 obstacleImg = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"floor_spikes_anim_f3.png"));
				 wallImgTop = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"wall_corner_front_left.png"));
				 wallImgLeft= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"sinistra.png"));
				 wallImgRight = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"destra.png"));
				 wallImgDown = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"wall_mid.png"));
				 background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"sfondo.png"));
				 angledx = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"angolodx.png"));
				 anglesx = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"angolosx.png"));
				 bridge = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc +"floor_2.png"));
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static ImageStore getImageStore() {
		if(imageStore == null)
			imageStore = new ImageStore();
		return imageStore;
	}
}
