package Application.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SingleButtonInitPanel extends JButton{

	private String ButtonName;
	
	public SingleButtonInitPanel(String s) {
		super(s);
		ButtonName = s;
		 this.setBackground(Color.LIGHT_GRAY);
		 this.setHorizontalTextPosition(SwingConstants.CENTER);
		 this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), 15));
		 this.setForeground(new Color(64,64,64));
	}
	
	public String getName() {
		return ButtonName;
	}
}
