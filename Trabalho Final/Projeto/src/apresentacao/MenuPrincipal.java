package apresentacao;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import negocio.Gerenciador;

public class MenuPrincipal extends JFrame{

	private JTabbedPane tabbedPanel;
	
	Gerenciador g = Main.getGerenciador();
	
	public MenuPrincipal() {
		
		this.setBackground(Color.PINK);
		UIManager.put("TabbedPane.selected", Color.PINK.darker());

		this.setSize(450, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPanel = new JTabbedPane();
		tabbedPanel.add("Musicas", new PainelMusicas(this, g));
		tabbedPanel.add("Artistas", new PainelArtistas(this, g));
		tabbedPanel.add("Playlists", new PainelPlayLists(this, g));
		tabbedPanel.add("Opções", new PainelOpcoes(this, g));
		tabbedPanel.setBackground(Color.PINK);
		tabbedPanel.setForeground(Color.white);
		this.add(tabbedPanel);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
