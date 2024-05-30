package Application.menu;

 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

 

import Application.config.Settings;
import Application.core.Game;

 

public class PausePanel extends JPanel {
    
    private JButton resume;
    private JButton restart;
    private JButton quit;
    private Image background;
    private Image pause;
    private int width;
    private int height;
    
    private ArrayList<JButton> buttons;
    
    public PausePanel() {
        
        super();
        
        Settings.controlresolution();
        width = Settings.WINDOW_WIDTH;
        height = Settings.WINDOW_HEIGHT;
        loadImages();
        
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        repaint();
        
        buttons = new ArrayList<JButton>();
        
        resume = new SingleButtonInitPanel("Resume");
        restart = new SingleButtonInitPanel("Restart");
        quit = new SingleButtonInitPanel("Quit");
        
        buttons.add(resume);
        buttons.add(restart);
        buttons.add(quit);
        
        add(resume);
        add(restart);
        add(quit);
        
        resume.setBounds((width/2)-90, (height/2)-10, 180, 50);
        restart.setBounds((width/2)-90, (height/2)+60, 180, 50);
        quit.setBounds((width/2)-90, (height/2)+130, 180, 50);
        
        for (int i = 0; i < buttons.size(); i++) {
            int index = i;
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
                    case "Resume" :
                        Settings.isPause = false;
                        PanelHandler.setCurrent("GamePanel");
                        break;
                    case "Restart":
                    	Settings.isPause = false;
                    	if (Settings.GameMode == 0)
                    		Game.getInstance().newGame(null);
                    	else 
                    		Game.getInstance().newGame(Settings.fileRandom);
                        break;
                    case "Quit":
                        PanelHandler.setCurrent("Init");
                        break;
                }
                buttons.get(index).setForeground(new Color(64,64,64));
            }
            });
        }
    }
    
    private void loadImages() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/pausefinal.png"));
            pause = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/pauseImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
            g.drawImage(pause,(this.getSize().width)/4, -120, (this.getSize().width)/2, (this.getSize().height), null);
        }
    }
}
 