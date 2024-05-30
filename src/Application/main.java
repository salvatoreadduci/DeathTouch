package Application;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Application.control.AttackController;
import Application.control.MovementController;
import Application.core.Game;
import Application.view.CustomPanel;
import Application.view.RandomPanel;
import Application.menu.ChooseCharacterPanel;
import Application.menu.ChooseNamePanel;
import Application.menu.GameModePanel;
import Application.menu.GameOverPanel;
import Application.menu.InitPanel;
import Application.menu.PanelHandler;
import Application.menu.PausePanel;
import Application.menu.RulesPanel;
import Application.MapEditor.MapEditorPanel;
import Application.config.Settings;

public class main {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setResizable(false);
		f.setTitle("Death Touch");
		f.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		PausePanel pp = new PausePanel();
		GameOverPanel go = new GameOverPanel();
		JPanel ip = (InitPanel) new InitPanel();
		JPanel rp = (RulesPanel) new RulesPanel();
		JPanel cc = (ChooseCharacterPanel) new ChooseCharacterPanel();
		JPanel cn = (ChooseNamePanel) new ChooseNamePanel();
		MapEditorPanel me = MapEditorPanel.getInstance();
		GameModePanel gm = GameModePanel.getInstance();
		PanelHandler.add("PausePanel", pp);
		PanelHandler.add("GameOver",  go);
		PanelHandler.add("ChooseName",cn);
		PanelHandler.add("Init",ip);
		PanelHandler.add("Rules", rp);
		PanelHandler.add("ChooseCharacter", cc);
		PanelHandler.add("GameMode", gm);
		PanelHandler.add("MapEditor", me);
		PanelHandler.init(f);
		PanelHandler.setCurrent("Init");

		f.setFocusable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		}
}
