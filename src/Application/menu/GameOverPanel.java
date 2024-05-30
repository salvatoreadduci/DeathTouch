package Application.menu;

 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

 

import Application.config.Settings;
import Application.core.Game;

 

public class GameOverPanel extends JPanel {

 

    private JButton yes;
    private JButton no;
    private JTextField continuee;
    private Image background;
    private Image endgame;
    private int width;
    private int height;
    
    private ArrayList<JButton> buttons;
    
    public GameOverPanel() {
        
        super();
        
        Settings.controlresolution();
        width = Settings.WINDOW_WIDTH;
        height = Settings.WINDOW_HEIGHT;
        
        loadImages();
        
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        repaint();
        
        buttons = new ArrayList<JButton>();
        
        yes = new SingleButtonInitPanel("Yes");
        no = new SingleButtonInitPanel("No");
        continuee = new JTextField("Play Again?");
        
        buttons.add(yes);
        buttons.add(no);
    
        yes.setBounds((width/2) - 120, (height/2) - 50, 100, 50);
        no.setBounds((width/2) + 20, (height/2) - 50, 100, 50);
        
        continuee.setOpaque(false);
        continuee.setFont(new Font("Serif",3, 23));
        continuee.setHorizontalAlignment(0);
        continuee.setForeground(new Color (255, 255, 255));    
        continuee.setEditable(false);
        continuee.setFocusable(false);
        this.add(continuee);
        continuee.setBounds((width/2)-100, (height/2) -150, 200, 50);
        
        for (int i = 0; i < buttons.size(); i++) {
            int index = i;
            this.add(buttons.get(index));
            buttons.get(index).addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(index).setForeground(new Color(64,64,64));
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(index).setForeground(new Color(153,153,0));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(buttons.get(index).getName()) {
                    case "Yes" :                    	
                    	if (Settings.GameMode == 0)
                    		Game.getInstance().newGame(null);
                    	else 
                    		Game.getInstance().newGame(Settings.fileRandom);
                        break;
                    case "No":
                        PanelHandler.setCurrent("Init");
                        break;
                }
                buttons.get(index).setForeground(new Color(64,64,64));
            }
            });
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
        if (endgame != null) {
            g.drawImage(endgame, (this.getSize().width)/4, 100, (this.getSize().width)/2, (this.getSize().height)/3, null); 
        }
        }
    }
    
    public void loadImages() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/pausefinal.png"));
            endgame = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}