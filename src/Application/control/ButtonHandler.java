package Application.control;

import java.util.ArrayList;

import Application.core.Game;

public class ButtonHandler {
	ArrayList<Integer> buttonPressed;
	//0 - UP
	//1 - DOWN
	//2 - LEFT
	//3 - RIGHT
	//4 - N
	
	private static ButtonHandler _instance = new ButtonHandler();
	
	public static ButtonHandler getInstance() {
		return _instance;
	}

	private ButtonHandler() {
		buttonPressed = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++)
			buttonPressed.add(new Integer(0));
	}
	
	public int getButton(int idx) {
		return buttonPressed.get(idx);
	}
	
	public void setButton(int idx, int value) {
		buttonPressed.set(idx, value);
	}
	
	public boolean verifyMovementButtons() {
		int tmp = 0;
		for (int i = 0; i < 4; i++)
			tmp += buttonPressed.get(i);
		if (tmp == 0) return true;
		return false;
	}
	
	public int buttonsSize() {
		return buttonPressed.size();
	}
	
	public void addButton() {
		buttonPressed.add(new Integer(0));
	}
	
	public void reset() {
		for (int i = 0; i < buttonPressed.size(); i++)
			buttonPressed.set(i, 0);
	}
}
