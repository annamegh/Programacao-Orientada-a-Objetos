package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dados.Musica;
import dados.Playlist;
import excessoes.DeleteException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class ConteudoMusicaDaPlaylist extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel j1;
	private JLabel j2;
	private JLabel j3;
	private JButton voltarButton;
	private JButton playButton;
	private JButton excluirMusicaButton;
	
	public ConteudoMusicaDaPlaylist(Gerenciador g, Playlist p, Musica m) {
		
		this.setSize(410, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBackground(Color.PINK.darker());
		panel.setLayout(null);
		
		this.add(panel);
		
		j1 = new JLabel("Música: ");
		j1.setBounds(30, 0, 320, 80);
		j1.setForeground(Color.white);
		
		j2 = new JLabel(m.getNome());
		j2.setBounds(90, 0, 320, 80);
		j2.setForeground(Color.white);
		
		j3 = new JLabel("Artista: ");
		j3.setBounds(30, 30, 320, 80);
		j3.setForeground(Color.white);
		
		try {
			for(int i = 0; i < g.getArtistasDaMusica(m).size(); i++) {
				JLabel j = new JLabel(g.getArtistasDaMusica(m).get(i).getNome());
				j.setBounds(90, 30 + i * 20, 320, 80);
				j.setForeground(Color.white);
				panel.add(j);
			}
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		playButton = new JButton("Play");
		playButton.setBounds(190, 150, 70, 25);
		playButton.setBackground(Color.PINK);
		playButton.setForeground(Color.WHITE);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Play(m);
			}
		};
		playButton.addActionListener(buttonListener1);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(290, 150, 80, 25);
		voltarButton.setBackground(Color.PINK);
		voltarButton.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConteudoPlaylist(g, p);
				dispose();
			}
		};
		voltarButton.addActionListener(buttonListener2);
		
		excluirMusicaButton = new JButton("Exlcuir da Playlist");
		excluirMusicaButton.setBounds(20, 150, 140, 25);
		excluirMusicaButton.setBackground(Color.PINK);
		excluirMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.excluirMusicaP(m, p);
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				new ConteudoPlaylist(g, p);
			}
		};
		excluirMusicaButton.addActionListener(buttonListener3);
		
		panel.add(j1);
		panel.add(j2);
		panel.add(j3);
		panel.add(playButton);
		panel.add(voltarButton);
		panel.add(excluirMusicaButton);
		
		this.setVisible(true);
		this.setResizable(false);
	}
}

