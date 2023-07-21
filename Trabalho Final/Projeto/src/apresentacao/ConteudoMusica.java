package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class ConteudoMusica extends JFrame {

	private JPanel panel = new JPanel();
	private JList<Artista> artistas;
	private JLabel j1;
	private JLabel j2;
	private JLabel j3;
	private JButton excluirMusicaButton;
	private JButton playButton;
	private JButton favButton;
	private JButton menuButton;
	private JButton addButton;
	private JButton remvButton;
	
	public ConteudoMusica(Gerenciador g, Musica m) {
		
		this.setSize(355, 260);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBackground(Color.PINK.darker());
		panel.setLayout(null);
		
		this.add(panel);
		
		j1 = new JLabel("Música: ");
		j1.setBounds(30, 0, 320, 80);
		j1.setForeground(Color.WHITE);
		
		j2 = new JLabel(m.getNome());
		j2.setBounds(90, 0, 320, 80);
		j2.setForeground(Color.WHITE);
		
		j3 = new JLabel("Artista: ");
		j3.setBounds(30, 30, 320, 80);
		j3.setForeground(Color.WHITE);
		
		DefaultListModel<Artista> list = new DefaultListModel<Artista>();
		try {
			for(Artista a: g.getArtistas(g.getU())) {
				list.addElement(a);
			}
		} catch (SelectException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		artistas = new JList<Artista>(list);
		try {
			artistas.setBounds(90, 60, 200, 18 * g.getArtistasDaMusica(m).size());
		} catch (ClassNotFoundException | SelectException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		artistas.setForeground(Color.WHITE);
		artistas.setBackground(Color.PINK.darker());
		artistas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				dispose();
				new ConteudoArtista(g, artistas.getSelectedValue());
			}
		});
		
		excluirMusicaButton = new JButton("X");
		excluirMusicaButton.setBounds(15, 150, 45, 25);
		excluirMusicaButton.setBackground(Color.PINK);
		excluirMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.excluirMusica(m, g.getU());
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				new MenuPrincipal();
			}
		};
		excluirMusicaButton.addActionListener(buttonListener3);
		
		playButton = new JButton("Play");
		playButton.setBounds(125, 185, 60, 25);
		playButton.setBackground(Color.PINK);
		playButton.setForeground(Color.WHITE);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Play(m);
			}  
		};
		playButton.addActionListener(buttonListener1);
		
		favButton = new JButton("<3");
		favButton.setBounds(255, 150, 70, 25);
		favButton.setBackground(Color.PINK);
		favButton.setForeground(Color.WHITE);
		ActionListener favButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAddNaPlaylist(g, m);
			}
		};
		favButton.addActionListener(favButtonListener);
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(255, 185, 70, 25);
		menuButton.setBackground(Color.PINK);
		menuButton.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuPrincipal();
				dispose();
			}
		};
		menuButton.addActionListener(buttonListener2);
		
		addButton = new JButton("+artista");
		addButton.setBounds(155, 150, 85, 25);
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAddArtista(g, m);
			}
		});
		
		addButton.setBackground(Color.PINK);
		addButton.setForeground(Color.WHITE);
		
		remvButton = new JButton("-artista");
		remvButton.setBounds(75, 150, 85, 25);
		remvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpRemoveArtista(g, m);
			}
		});
		
		remvButton.setBackground(Color.PINK);
		remvButton.setForeground(Color.WHITE);
		
		panel.add(j1);
		panel.add(j2);
		panel.add(j3);
		panel.add(artistas);
		panel.add(playButton);
		panel.add(menuButton);
		panel.add(favButton);
		panel.add(excluirMusicaButton);
		panel.add(addButton);
		panel.add(remvButton);
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void popUpAddNaPlaylist (Gerenciador g, Musica m) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Adicionar Música");
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j = new JLabel("Música: " + m.getNome());
		j.setBounds(20, 20, 100, 25);
		j.setForeground(Color.WHITE);
		
		JComboBox<Playlist> comboBox1 = new JComboBox<Playlist>();
		try {
			for(Playlist p: g.getPlaylists(g.getU())) {
				comboBox1.addItem(p);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 45, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JLabel fail = new JLabel("");
		fail.setBounds(20, 60, 100, 25);
		fail.setForeground(Color.WHITE);
		
		JButton button = new JButton("Adicionar");
		button.setBounds(20, 90, 100, 25);
		button.setBackground(Color.PINK);
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.cadastrarMusicaP(m, (Playlist) comboBox1.getSelectedItem());
				} catch (InsertException | SelectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				popUp.dispose();
				dispose();
				new ConteudoMusica(g, m);
			}
		};
		button.addActionListener(buttonListener);
		
		JButton button2 = new JButton("Voltar");
		button2.setBounds(170, 90, 80, 25);
		button2.setBackground(Color.PINK);
		button2.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		};
		button2.addActionListener(buttonListener2);
			
		panel.add(j);
		panel.add(fail);
		panel.add(comboBox1);
		panel.add(button);
		panel.add(button2);
		
		popUp.setResizable(false);
		popUp.setVisible(true);
	}
	
	public void popUpAddArtista (Gerenciador g, Musica m) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Adicionar Artista");
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j1 = new JLabel("Música: " + m.getNome());
		j1.setBounds(20, 20, 100, 25);
		j1.setForeground(Color.WHITE);
		
		JComboBox<Artista> comboBox1 = new JComboBox<Artista>();
		try {
			for(Artista a: g.getArtistas(g.getU())) {
				comboBox1.addItem(a);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 45, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JLabel fail = new JLabel("");
		fail.setBounds(20, 60, 220, 25);
		fail.setForeground(Color.WHITE);
		
		JButton button2 = new JButton("Adicionar");
		button2.setBounds(20, 90, 100, 25);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		button2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					g.cadastrarMusicaArtista(m, (Artista) comboBox1.getSelectedItem());
					popUp.dispose();
					dispose();
					new ConteudoMusica(g, m);
				} catch (InsertException | SelectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fail.setText("O artista já foi adicionado à música.");
				}		
			}
		});
		
		JButton button3 = new JButton("Voltar");
		button3.setBounds(170, 90, 80, 25);
		button3.setBackground(Color.PINK.darker());
		button3.setForeground(Color.WHITE);
		button3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		});
		
		panel.add(j1);
		panel.add(fail);
		panel.add(comboBox1);
		panel.add(button2);
		panel.add(button3);
		
		popUp.setResizable(false);
		popUp.setVisible(true);
	}
	
	public void popUpRemoveArtista (Gerenciador g, Musica m) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Remover Artista");
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j1 = new JLabel("Música: " + m.getNome());
		j1.setBounds(20, 20, 100, 25);
		j1.setForeground(Color.WHITE);
		
		JComboBox<Artista> comboBox1 = new JComboBox<Artista>();
		try {
			for(Artista a: g.getArtistasDaMusica(m)) {
				comboBox1.addItem(a);
			}
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 45, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JLabel fail = new JLabel("");
		fail.setBounds(20, 60, 100, 25);
		fail.setForeground(Color.WHITE);
		
		JButton button2 = new JButton("Remover");
		button2.setBounds(20, 90, 100, 25);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		button2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.excluirMusicaArtista(m, (Artista) comboBox1.getSelectedItem());
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dispose();
				new ConteudoMusica(g, m);			
			}
		});
		
		JButton button3 = new JButton("Voltar");
		button3.setBounds(170, 90, 80, 25);
		button3.setBackground(Color.PINK.darker());
		button3.setForeground(Color.WHITE);
		button3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		});
		
		panel.add(j1);
		panel.add(fail);
		panel.add(comboBox1);
		panel.add(button2);
		panel.add(button3);
		
		popUp.setResizable(false);
		popUp.setVisible(true);
	}
}
