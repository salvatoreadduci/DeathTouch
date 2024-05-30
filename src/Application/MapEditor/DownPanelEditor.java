package Application.MapEditor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Application.core.map.Block;
import Application.menu.PanelHandler;


public class DownPanelEditor extends JPanel {
	
	private JButton exit;
	private JButton save;
	private JButton play;
	
	private String nomeFile;
	
	private MapCreator mc = MapCreator.getInstance();
	private Block[][] m = mc.getMap();
	
	public DownPanelEditor() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1, 3));
		exit = new JButton("exit from creative mode");
		exit.setForeground(new Color(64,64,64));
		
		save = new JButton("save map");
		save.setForeground(new Color(64,64,64));

		add(exit);
		add(save);
		
		addActionsOnButton();
	}
	
	public void addActionsOnButton() {
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(new Color(64,64,64));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(new Color(153,153,0));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int scelta = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit from creative mode?");
				if (scelta == JOptionPane.YES_OPTION) {
					PanelHandler.setCurrent("Init");
				}
			}
		});
	
		save.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				save.setForeground(new Color(64,64,64));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				save.setForeground(new Color(153,153,0));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				save();
			}
		});
	}
	
	private void save() {
		try {
			JFrame parentFrame = new JFrame();
			  
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specifica un file dove salvare"); 
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File supportati", "map");
			fileChooser.addChoosableFileFilter(filter);
			
			int userSelection = fileChooser.showSaveDialog(parentFrame);
			  
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = new File(fileChooser.getSelectedFile() + ".map");
				BufferedWriter bw = new  BufferedWriter(new FileWriter(fileToSave,false));
				for (int i = 0; i < m.length; i++) {
					for (int j = 0; j < m[i].length; j++) {
						bw.write(m[i][j].getType());
					}
					bw.newLine();
				}
				bw.close();
				JOptionPane.showMessageDialog(null,"Salvato in" + System.lineSeparator() + fileToSave.getAbsolutePath(), "Salvato", JOptionPane.WARNING_MESSAGE);
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"C'è stato un errore durante il salvataggio!", "Errore", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
}