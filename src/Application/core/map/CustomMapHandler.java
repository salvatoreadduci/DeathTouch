package Application.core.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Application.config.Settings;
import Application.menu.PanelHandler;

public class CustomMapHandler {

	private static CustomMapHandler instance = null;
	private Block[][] customMap;
	private int width;
	private int height;
	private String nomeFile;
	
	private CustomMapHandler() {
		this.width = Settings.MAP_WIDTH/Settings.BLOCK_SIZE;
		this.height = Settings.MAP_HEIGHT/Settings.BLOCK_SIZE;
		customMap  = new Block[width][height];
	}
	
	public static CustomMapHandler getInstance() {
		if (instance == null) {
			instance = new CustomMapHandler();
		}
		return instance;
	}
	
	public Block[][] getCustomMap() {
		return customMap;
	}
	
	public void initMap(String nf) {
		nomeFile = nf;
		try {
			JFrame parentFrame = new JFrame();
			  
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specifica il file di gioco"); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File supportati", "map");
			fileChooser.addChoosableFileFilter(filter);
			int userSelection = fileChooser.showOpenDialog(parentFrame);
			  
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToRead = fileChooser.getSelectedFile();
				BufferedReader br = new BufferedReader(new FileReader(fileToRead));
				int cont = 0; 
				while(br.ready()) {
					String line = br.readLine();
					for (int i = 0; i < line.length(); i++) {
						char tmp = line.charAt(i);
						customMap[cont][i] = new Block(cont,i,tmp);
					}
					cont++;
				}
				br.close();
			} else if (userSelection == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null,"Annullato", "Errore", JOptionPane.WARNING_MESSAGE);
				PanelHandler.setCurrent("GameMode");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"C'è stato un errore durante il caricamento!", "Errore", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
}
