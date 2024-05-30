package Application.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SingleCharacterPanel extends JPanel {

	public JLabel CharacterImage;
	public JTextField CharacterName;
	public String name;
	public int idNumber;
	
	public SingleCharacterPanel(BufferedImage img, String str, int id) {
		
		this.idNumber = id;
		this.name = str;
		this.setLayout(new BorderLayout());
		CharacterImage = new JLabel(new ImageIcon(img.getScaledInstance(img.getWidth()*6, img.getHeight()*6, Image.SCALE_SMOOTH)));
		CharacterImage.setFocusable(true);
		this.add(CharacterImage, BorderLayout.CENTER);
		this.setBackground(new Color(0,0,0,0));
		
		CharacterName = new JTextField(str);
		CharacterName.setVisible(true);
		CharacterName.setEditable(false);
		CharacterName.setFont(new Font("Serif",3, 20));
		CharacterName.setForeground(new Color (255, 255, 255));
		CharacterName.setBackground(new Color(192,192,192));
		CharacterName.setHorizontalAlignment(JTextField.CENTER);
		this.add(CharacterName, BorderLayout.SOUTH);
		this.setFocusable(true);
	}
}
