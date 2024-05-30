package Application.MapEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Application.GameLoop;
import Application.MapEditor.DownPanelEditor;
import Application.core.map.Block;


public class MapEditorPanel extends JPanel {
	
    private char res;
    
	private static MapEditorPanel instance = null;
	
    private MapEditorPanel() {
    	super();
    	setPreferredSize(new Dimension(800, 800));
		setLayout(new BorderLayout());
		res = Block.EMPTY;
		
		MapViewPanel left = new MapViewPanel();
		add(left, BorderLayout.CENTER);
		
		ToolsPanel right = new ToolsPanel();
		JScrollPane rightScroll = new JScrollPane(right);
		add(rightScroll, BorderLayout.EAST);
		
		DownPanelEditor down = new DownPanelEditor();
		this.add(down, BorderLayout.SOUTH);
		
		GameLoop gl = new GameLoop(left);
		Thread t = new Thread(gl);
		t.start();
    }
		

	public static MapEditorPanel getInstance() {
		if (instance == null) {
			instance = new MapEditorPanel();
		}
		return instance;
	}

	public void setRes(char r) {
		res = r;
	}

	public char getRes() {
		return res;
	}
}