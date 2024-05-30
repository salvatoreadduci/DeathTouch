package Application.MapEditor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Application.view.map.ImageStore;

public class ToolsPanel extends JPanel {
	
	private ImageStore is = ImageStore.getImageStore();
	
	public ToolsPanel() {
		super();
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		String s1 = "Floor";
		String s2 = "Obstacle";
		String s3 = "Top_Wall";
		String s4 = "Left_Wall";
		String s5 = "Right_Wall";
		String s6 = "Down_Wall";
		String s7 = "Empty";
		String s8 = "Right_Down_Angle";
		String s9 = "Left_Down_Angle";
		String sA = "Right_Up_Angle";
		String sB = "Left_Up_Angle";
		String sC = "Door";
		
		SingleWorkTool tool1 = new SingleWorkTool(is.currentFloor, s1);
		add(tool1);
		
		SingleWorkTool tool2 = new SingleWorkTool(is.obstacleImg, s2);
		add(tool2);
		
		SingleWorkTool tool3 = new SingleWorkTool(is.wallImgTop, s3);
		add(tool3);
		
		SingleWorkTool tool4 = new SingleWorkTool(is.wallImgLeft, s4);
		add(tool4);
		
		SingleWorkTool tool5 = new SingleWorkTool(is.wallImgRight, s5);
		add(tool5);
		
		SingleWorkTool tool6 = new SingleWorkTool(is.wallImgDown, s6);
		add(tool6);
		
		SingleWorkTool tool7 = new SingleWorkTool(is.background, s7);
		add(tool7);
		
		SingleWorkTool tool8 = new SingleWorkTool(is.angledx, s8);
		add(tool8);
		
		SingleWorkTool tool9 = new SingleWorkTool(is.anglesx, s9);
		add(tool9);
		
		SingleWorkTool tool10 = new SingleWorkTool(is.wallImgRight, sA);
		add(tool10);
		
		SingleWorkTool tool11 = new SingleWorkTool(is.wallImgLeft, sB);
		add(tool11);
		
		SingleWorkTool tool12 = new SingleWorkTool(is.bridge, sC);
		add(tool12);
	}
}
