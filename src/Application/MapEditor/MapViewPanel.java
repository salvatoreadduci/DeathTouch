package Application.MapEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Application.config.Settings;
import Application.core.map.Block;
import Application.view.map.ImageStore;


public class MapViewPanel extends JPanel{

	private ImageStore is = ImageStore.getImageStore();
	private MapCreator mc = MapCreator.getInstance();
	private Block[][] m = mc.getMap();
	
	public MapViewPanel() {
		super();
		setFocusable(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() / Settings.BLOCK_SIZE_EDITOR;
				int y = e.getY() / Settings.BLOCK_SIZE_EDITOR;
				
				if ((x > 1 && x < 20-1) && (y > 30 && y < 50-1)) {
					JOptionPane.showMessageDialog(null,"Spawn Room isn't editable!","Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				m[x][y].setType(MapEditorPanel.getInstance().getRes());
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				
				switch (m[i][j].getType()) {
				
				case Block.EMPTY:
					g.drawImage(is.background, i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
				
				case Block.FLOOR:
					g.drawImage(is.currentFloor, i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.WALL_TOP:
					g.drawImage(is.wallImgTop,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
				
				case Block.WALL_LEFT:
					g.drawImage(is.wallImgLeft,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.WALL_RIGHT:
					g.drawImage(is.wallImgRight,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
				
				case Block.WALL_DOWN:
					g.drawImage(is.wallImgDown,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.WALL_DANGLEDX:
					g.drawImage(is.anglesx,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.WALL_DANGLESX:
					g.drawImage(is.angledx,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
				
				case Block.WALL_TANGLEDX:
					g.drawImage(is.wallImgRight,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.WALL_TANGLESX:
					g.drawImage(is.wallImgLeft,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.OBSTACLE:
					g.drawImage(is.obstacleImg,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
					
				case Block.DOOR:
					g.drawImage(is.bridge,i * Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, null);
					break;
				}
				g.setColor(new Color(64,64,64));
				g.drawRect(i* Settings.BLOCK_SIZE_EDITOR, j * Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR, Settings.BLOCK_SIZE_EDITOR);
				}
			}
		}
	
	public void update() {
		repaint();
	}
	}
