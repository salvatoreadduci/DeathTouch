package Application.menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Application.config.Settings;
public class InitPanel extends JPanel{
    
    private ArrayList<JButton> buttons;
    private Image background;
    private BufferedImage title;
    
    public InitPanel() {
        
        super();
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(null);
        loadImages();
        repaint();
        
         JLabel labelTitle = new JLabel(new ImageIcon(title.getScaledInstance(800,400, Image.SCALE_SMOOTH)));
         this.add(labelTitle);
         labelTitle.setBounds(-20,-50, 800, 400);;  
        
        buttons = new ArrayList<JButton>();
        
         JButton SP = new SingleButtonInitPanel("Singleplayer");
         JButton ME = new SingleButtonInitPanel("Map Editor");
         JButton GR = new SingleButtonInitPanel("Info");
         JButton Q = new SingleButtonInitPanel("Quit");
         
         buttons.add(SP);
         buttons.add(ME);
         buttons.add(GR);
         buttons.add(Q);
         
         this.add(SP);
         this.add(ME);
         this.add(GR);
         this.add(Q);
         
         SP.setBounds(290, 300, 220, 60);
         ME.setBounds(290, 370, 220, 60);
         GR.setBounds(290, 440, 220, 60);
         Q.setBounds(290, 510, 220, 60);
         addActions();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
        }
    }
    
    public void loadImages(){
        try {
            background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/background_InitPanel.png"));
            title= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/title.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addActions(){
        for (int i = 0; i < buttons.size(); i++) {
            int index = i;
             buttons.get(index).addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        switch(buttons.get(index).getName()) {
                        case "Singleplayer":
                            PanelHandler.setCurrent("ChooseCharacter");
                            break;
                        case "Multiplayer - WiP!":
                            break;
                        case "Map Editor":
                            PanelHandler.setCurrent("MapEditor");
                            break;
                        case "Info":
                            PanelHandler.setCurrent("Rules");
                            break;
                        case "Quit":
                            PanelHandler.closeFrame();
                            break;
                        }
                        
                        buttons.get(index).setForeground(new Color(64,64,64));
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        buttons.get(index).setForeground(new Color(153,153,0));
                    }
                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        buttons.get(index).setForeground(new Color(64,64,64));
                    }
                    @Override
                    public void mousePressed(MouseEvent arg0) {}
                    @Override
                    public void mouseReleased(MouseEvent arg0) {}
                 });
        }
    }
}