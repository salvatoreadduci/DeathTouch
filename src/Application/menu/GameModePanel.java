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

import Application.config.Settings;
import Application.control.AttackController;
import Application.control.MovementController;
import Application.core.Game;
import Application.core.map.CustomMapHandler;
import Application.view.CustomPanel;
import Application.view.RandomPanel;

public class GameModePanel extends JPanel {

	private JButton back;
	private Image background;
	private JButton random;
	private JButton custom;
	private String nomeFile;
	
	private static GameModePanel instance = null;
	
	private GameModePanel() {
        super();
        setPreferredSize(new Dimension(800, 800));

		try {
			background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/background_NamePanel.png"));
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		repaint();
		
		this.setLayout(null);
		
        back = new JButton("back");
		back.setBackground(Color.LIGHT_GRAY);
		back.setContentAreaFilled(true);
		this.add(back);
		back.setBounds(290, 560 , 180, 50);
		back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelHandler.setCurrent("ChooseName");
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
		
		random = new JButton("Random Mode");
		random.setBackground(Color.LIGHT_GRAY);
		random.setContentAreaFilled(true);
		this.add(random);
		random.setBounds(290, 440, 180, 50);
		random.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Settings.GameMode = 0;
				Game.getInstance().newGame(null);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				random.setForeground(new Color(153,153,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				random.setForeground(new Color(0,0,0));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
		 
		custom = new JButton("Custom Mode");
		custom.setBackground(Color.LIGHT_GRAY);
		custom.setContentAreaFilled(true);
		this.add(custom);
		custom.setBounds(290, 500, 180, 50);
		custom.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Settings.GameMode = 1;
				Game.getInstance().newGame(Settings.fileRandom);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				custom.setForeground(new Color(153,153,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				custom.setForeground(new Color(0,0,0));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
        }
    }
    
    public static GameModePanel getInstance() {
    	if(instance == null) {
    		instance = new GameModePanel();
    	}
    	return instance;
    }
}
