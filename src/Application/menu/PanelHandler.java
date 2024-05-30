package Application.menu;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Application.config.Settings;

public class PanelHandler {
	
	private static HashMap<String, JPanel> allWindows = new HashMap<String, JPanel>();
	private static JFrame frame;
	
	public static void init(JFrame f) {
		frame = f;
	}
	
	public static void add(String name, JPanel panel) {
		allWindows.put(name, panel);
	}
	
	public static void setCurrent(String name) {
		if (name == "GamePanel" || name == "MapEditor" || name == "CustomMode") {
			Settings.controlresolution();
		} else if (name == "Init") {
			Settings.menuResolution();
		}
		allWindows.get(name).setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.setContentPane(allWindows.get(name));
		frame.pack();
		frame.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.revalidate();
		allWindows.get(name).setFocusable(true);
		allWindows.get(name).requestFocus();
	}
	
	public static void remove(String name) {
		allWindows.remove(name);
	}
	
	public static void closeFrame() {
		System.exit(0);
	}
	
}
