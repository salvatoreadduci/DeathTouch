package Application.MapEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Application.config.Settings;
import Application.core.map.Block;

public class SingleWorkTool extends JPanel{
	
		JLabel element;
		JButton button;

	public SingleWorkTool(Image toolImage, String toolName) {
		setLayout(new BorderLayout());
		JLabel element = new JLabel(new ImageIcon(toolImage.getScaledInstance(Settings.BLOCK_SIZE_EDITORMENU, Settings.BLOCK_SIZE_EDITORMENU, Image.SCALE_SMOOTH)));
		add(element,BorderLayout.WEST);
		JButton button = new JButton(toolName);
		button.setForeground(new Color(64,64,64));
		button.setBackground(Color.LIGHT_GRAY);
		button.setSize(new Dimension(button.getWidth(),30));
		add(button, BorderLayout.CENTER);
		button.setFocusable(true);
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {	
				button.setForeground(new Color(64,64,64));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153,153,0));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String blockType = button.getText();
				switch(blockType) {
					case "Floor":
						MapEditorPanel.getInstance().setRes(Block.FLOOR);
						break;
					case "Obstacle":
						MapEditorPanel.getInstance().setRes(Block.OBSTACLE);
						break;
					case "Top_Wall":
						MapEditorPanel.getInstance().setRes(Block.WALL_TOP);
						break;
					case "Left_Wall":
						MapEditorPanel.getInstance().setRes(Block.WALL_LEFT);
						break;
					case "Right_Wall":
						MapEditorPanel.getInstance().setRes(Block.WALL_RIGHT);
						break;
					case "Down_Wall":
						MapEditorPanel.getInstance().setRes(Block.WALL_DOWN);
						break;
					case "Empty":
						MapEditorPanel.getInstance().setRes(Block.EMPTY);
						break;
					case "Right_Down_Angle":
						MapEditorPanel.getInstance().setRes(Block.WALL_DANGLEDX);
						break;
					case "Left_Down_Angle":
						MapEditorPanel.getInstance().setRes(Block.WALL_DANGLESX);
						break;
					case "Right_Up_Angle":
						MapEditorPanel.getInstance().setRes(Block.WALL_TANGLEDX);
						break;
					case "Left_Up_Angle":
						MapEditorPanel.getInstance().setRes(Block.WALL_TANGLESX);
						break;
					case "Door":
						MapEditorPanel.getInstance().setRes(Block.DOOR);
						break;
				}
				System.out.println(MapEditorPanel.getInstance().getRes());
			}
		});
	}
}
