package Application.menu;

 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

 

import Application.config.Settings;

 

public class RulesPanel extends JPanel {
    
    private JButton back;
    private JTextArea text;
    private Image background;
    private Image keyword;
    private Image mouse;
    
    public RulesPanel() {
        setPreferredSize(new Dimension(800, 800));
        loadImages();
        repaint();
        this.setLayout(null);
        
        text = new JTextArea();
        text.setEditable(false);
        text.setFocusable(false);
        text.setBackground(Color.lightGray);
        text.setFont(new Font("Serif",3, 19));
        text.setForeground(new Color (64,64,64));    
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("Info_DeathTouch.txt"));
            while(br.ready()) {
                String line = br.readLine();
                text.append(line + System.lineSeparator());
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        JScrollPane scroll = new JScrollPane(text);
        this.add(scroll);
        scroll.setBounds(20, 400, 680, 220);
        
        back =  new SingleButtonInitPanel("back");
        this.add(back);
        back.setBounds(350, 700, 100, 50);
        back.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {
                back.setForeground(new Color(64,64,64));
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setForeground(new Color(153,153,0));
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PanelHandler.setCurrent("Init");
            }
        });

 

    }
    
    public void loadImages(){
        try {
            background = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/background_InitPanel.png"));
            keyword = ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/Keyf.png"));
            mouse= ImageIO.read(getClass().getResourceAsStream(Settings.resLoc + "/mousef.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null && keyword != null && mouse != null) {
            g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null); 
            g.drawImage(keyword, 40, -30, 550, 500, null);
            g.drawImage(mouse, 600, 90, 160, 250, null);
        }
    }
}