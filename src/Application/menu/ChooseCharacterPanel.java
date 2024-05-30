package Application.menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Application.config.Settings;
import Application.view.character.CharacterView;

public class ChooseCharacterPanel extends JPanel {
    private JButton CR;
    private JButton back;
    
    private String res;
    
    private Image background;
    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img6;
    
	String s1 = "elf_m";
	String s2 = "elf_f";
	String s3 = "knight_m";
	String s4 = "knight_f";
	String s5 = "wizzard_m";
	String s6 = "wizzard_f";
    
    private ArrayList<JTextField> textes;
    private ArrayList<SingleCharacterPanel> CharactersPanels;
    
    public ChooseCharacterPanel() {
        super();
        setPreferredSize(new Dimension(800, 800));
        res = " ";
        loadImages();
        repaint();
        
        CharactersPanels = new ArrayList<SingleCharacterPanel>();
        textes = new ArrayList<JTextField>();
        
        this.setLayout(null);
        
        back = new JButton("back");
		back.setBackground(Color.LIGHT_GRAY);
		back.setContentAreaFilled(true);
		back.setForeground(new Color(64,64,64));
		this.add(back);
		back.setBounds(200, 730 , 100, 40);
		
		 back.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelHandler.setCurrent("Init");
				back.setForeground(new Color(64,64,64));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				back.setForeground(new Color(153,153,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				back.setForeground(new Color(64,64,64));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
		
        CR = new JButton("Confirm my choice!");
		CR.setBackground(Color.LIGHT_GRAY);
		CR.setContentAreaFilled(true);
		CR.setForeground(new Color(64,64,64));
		this.add(CR);
		CR.setBounds(450, 720 , 200, 60);
		
		CR.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (res == " ") {
					JOptionPane.showMessageDialog(null,"You have not chosen any character!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					Settings.clas = res;
					CharacterView.getInstance().changeCharacter();
					PanelHandler.setCurrent("ChooseName");
				}
				
				CR.setForeground(new Color(64,64,64));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				CR.setForeground(new Color(153,153,0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				CR.setForeground(new Color(64,64,64));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		 });
		
		JPanel up = new JPanel();
		this.add(up);
		up.setBounds(50, 30, 700, 650);
		up.setBackground(new Color(0,0,0,0));
		up.setLayout(new GridLayout(3, 3, 10, 10));
		
		SingleCharacterPanel panel1 = new SingleCharacterPanel(img1, s1, 1);
			textes.add(panel1.CharacterName);
			up.add(panel1);
			CharactersPanels.add(panel1);

		SingleCharacterPanel panel2 = new SingleCharacterPanel(img2, s2, 2);
			textes.add(panel2.CharacterName);
			up.add(panel2);
			CharactersPanels.add(panel2);
			
		SingleCharacterPanel panel3 = new SingleCharacterPanel(img3, s3, 3);
			textes.add(panel3.CharacterName);
			up.add(panel3);
			CharactersPanels.add(panel3);
			
		SingleCharacterPanel panel4 = new SingleCharacterPanel(img4, s4, 4);
			textes.add(panel4.CharacterName);
			up.add(panel4);
			CharactersPanels.add(panel4);
			
		SingleCharacterPanel panel5 = new SingleCharacterPanel(img5, s5, 5);
			textes.add(panel5.CharacterName);
			up.add(panel5);
			CharactersPanels.add(panel5);

		SingleCharacterPanel panel6 = new SingleCharacterPanel(img6, s6, 6);
			textes.add(panel6.CharacterName);
			up.add(panel6);
			CharactersPanels.add(panel6);
			
		addActions();
    }
	
    public void loadImages() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "background_InitPanel.png"));
            img1= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s1 + "_idle_anim_f0.png"));
            img2= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s2 + "_idle_anim_f0.png"));
            img3= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s3 + "_idle_anim_f0.png"));
            img4= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s4 + "_idle_anim_f0.png"));
            img5= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s5 + "_idle_anim_f0.png"));
            img6= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + s6 + "_idle_anim_f0.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
        }
    }
    
    public String getCharacterChoice() {
    	return res;
    }
    
    public void changeTFields(int num) {
    	for (int i = 0; i < textes.size(); i++) {
    		if (i != num-1) {
    			textes.get(i).setForeground(new Color (255, 255, 255));
    		}
    	}
    }
    
    public void addActions() {
    	for (int i = 0; i < CharactersPanels.size();i++) {
    		int index = i;
    		CharactersPanels.get(index).CharacterImage.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {
					CharactersPanels.get(index).CharacterName.setBorder(null);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					CharactersPanels.get(index).CharacterName.setBorder(BorderFactory.createLineBorder(new Color(0,139,139)));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					res = CharactersPanels.get(index).name;
					CharactersPanels.get(index).CharacterName.setForeground(new Color (0,139,139));
					changeTFields(CharactersPanels.get(index).idNumber);
				}
			});
    	}
    }
}
    
   