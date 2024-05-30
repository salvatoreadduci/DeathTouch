package Application.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Application.config.Settings;

public class ChooseNamePanel extends JPanel {
	
	private JButton back;
	private JButton confirm;
	private Image background;
	private String name; 
	private JTextArea information;
	private JTextField yourName;
	
	public ChooseNamePanel() {
		
        super();
        setPreferredSize(new Dimension(800, 800));

		try {
			background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/background_NamePanel.png"));
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		repaint();
		
		this.setLayout(null);
		name = " ";
		
        back = new JButton("back");
		back.setBackground(Color.LIGHT_GRAY);
		back.setContentAreaFilled(true);
		this.add(back);
		back.setBounds(200, 730 , 100, 40);
		
		 back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelHandler.setCurrent("ChooseCharacter");
				back.setForeground(new Color(0,0,0));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				back.setForeground(new Color(153,153,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				back.setForeground(new Color(0,0,0));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
		 
		 confirm = new JButton ("Confirm my choise!");
		 confirm.setBackground(Color.LIGHT_GRAY);
		 confirm.setContentAreaFilled(true);
		 confirm.setForeground(new Color(64,64,64));
		 this.add(confirm);
		 confirm.setBounds(450, 720 , 200, 60);
		 
		 confirm.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				name = yourName.getText();
				if(name.length() < 4 || name.length() > 12) {
					JOptionPane.showMessageDialog(null,"ERROR: the name of the account must have a minimum length of 4 characters" + System.lineSeparator() + "and a maximum of 12, therefore names that do not respect this condition will not be considered valid!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Settings.name = name;
					PanelHandler.setCurrent("GameMode");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				confirm.setForeground(new Color(153,153,0));
				confirm.setForeground(new Color(0,0,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				confirm.setForeground(new Color(0,0,0));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
		 
		
		 information = new JTextArea();
		 String str1 = "We are almost there!";
		 String str2 = "First, however, you have to choose a name for your character.";
		 String str3 = "Choose wisely, as it can't be changed";
		 String str4 = "later!";
		 information.append(str1 + System.lineSeparator() + str2 + System.lineSeparator() + str3 + System.lineSeparator() + str4 + System.lineSeparator());
		 information.setBackground(new Color(0,0,0,0));
		 information.setFont(new Font("Serif",3, 23));
		 information.setForeground(new Color (255, 255, 255));	
		 information.setEditable(false);
		 information.setFocusable(false);
		 this.add(information);
		 information.setBounds(100, 200, 600, 200);

		 
		 yourName = new JTextField();
		 yourName.setFont(new Font("Serif",3, 22));
		 yourName.setForeground(new Color (0,139,139));
		 yourName.setEditable(true);
		 yourName.setBorder(new LineBorder(Color.WHITE,5, true));
		 this.add(yourName);
		 yourName.setBounds(275, 450, 300, 60);
		 this.revalidate();
		
	}

	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
        }
    }
}
	
