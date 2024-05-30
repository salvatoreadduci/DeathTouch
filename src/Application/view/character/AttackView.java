package Application.view.character;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AttackView {

	int dimX;
	int dimY;
	Image attackImg;
	
	public AttackView() {
		try {
			attackImg = ImageIO.read(getClass().getResourceAsStream("/Application/resources/weapon_axe.png"));

		}catch(IOException e){
			e.printStackTrace();
		}
		dimX = attackImg.getWidth(null);
		dimY = attackImg.getHeight(null);
	}
	
	public Image getImage() {
		return attackImg;
	}
	
	public int getDimX() {
		return dimX;
	}
	
	public int getDimY() {
		return dimY;
	}
	
    public Rectangle getBounds(int x, int y) {
        return new Rectangle(x, y, dimX, dimY);
    }
}