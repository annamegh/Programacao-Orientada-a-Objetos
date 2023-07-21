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
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class ConteudoArtista extends JFrame {

	private JPanel panel = new JPanel();
	private JList<Musica> musicas;
	private JLabel j1;
	private JLabel j2;
	private JLabel j3;
	private JButton voltarButton;
	private JButton addButton;
	private JButton remvButton;
	private JButton excluirArtistaButton;
	
	public ConteudoArtista(Gerenciador g, Artista a){
		
		this.setSize(355, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBackground(Color.PINK.darker());
		
		panel.setLayout(null);
		
		this.add(panel);
		
		j1 = new JLabel("Artista: ");
		j1.setBounds(30, 0, 320, 80);
		j1.setForeground(Color.white);
		
		j2 = new JLabel(a.getNome());
		j2.setBounds(90, 0, 320, 80);
		j2.setForeground(Color.white);
		
		j3 = new JLabel("Músicas: ");
		j3.setBounds(30, 30, 320, 80);
		j3.setForeground(Color.white);
		
		voltarButton = new JButton("Menu");
		voltarButton.setBounds(255, 150, 70, 25);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuPrincipal();
				dispose();
			}
		};
		voltarButton.addActionListener(buttonListener2);
		voltarButton.setBackground(Color.PINK);
		voltarButton.setForeground(Color.WHITE);
		
		addButton = new JButton("+música");
		addButton.setBounds(155, 150, 85, 25);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAddMusica(g, a);
			}
		};
		addButton.addActionListener(buttonListener1);
		addButton.setBackground(Color.PINK);
		addButton.setForeground(Color.WHITE);
		
		remvButton = new JButton("-música");
		remvButton.setBounds(75, 150, 85, 25);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpRemoveMusica(g, a);
			}
		};
		remvButton.addActionListener(buttonListener4);
		remvButton.setBackground(Color.PINK);
		remvButton.setForeground(Color.WHITE);
		
		excluirArtistaButton = new JButton("X");
		excluirArtistaButton.setBounds(15, 150, 45, 25);
		excluirArtistaButton.setBackground(Color.PINK);
		excluirArtistaButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.excluirArtista(a, g.getU());
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				new MenuPrincipal();
			}
		};
		excluirArtistaButton.addActionListener(buttonListener3);
		
		DefaultListModel<Musica> list = new DefaultListModel<Musica>();
		try {
			for(Musica m: g.getMusicas(g.getU())) {
				list.addElement(m);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		musicas = new JList<Musica>(list);
		try {
			musicas.setBounds(90, 60, 200, 18 * g.getMusicasDoArtista(a).size());
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		musicas.setForeground(Color.WHITE);
		musicas.setBackground(Color.PINK.darker());
		musicas.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				dispose();
				new ConteudoMusica(g, musicas.getSelectedValue());
				}
		});
		
		panel.add(j1);
		panel.add(j2);
		panel.add(j3);
		panel.add(musicas);
		panel.add(voltarButton);
		panel.add(excluirArtistaButton);
		panel.add(addButton);
		panel.add(remvButton);
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void popUpAddMusica (Gerenciador g, Artista a) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Adicionar Música");
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j1 = new JLabel("Artista: " + a.getNome());
		j1.setBounds(20, 20, 100, 25);
		j1.setForeground(Color.WHITE);
		
		JComboBox<Musica> comboBox1 = new JComboBox<Musica>() ;
		try {
			for(Musica m: g.getMusicas(g.getU())) {
				comboBox1.addItem(m);
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
				
				Musica m = (Musica) comboBox1.getSelectedItem();

				try {
					g.cadastrarMusicaArtista(m, a);
					popUp.dispose();
					dispose();
					new ConteudoArtista(g, a);
				} catch (InsertException | SelectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fail.setText("A música já foi adicionada ao artista.");	
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
	
	public void popUpRemoveMusica (Gerenciador g, Artista a) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Remover Música");
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j1 = new JLabel("Artista: " + a.getNome());
		j1.setBounds(20, 20, 100, 25);
		j1.setForeground(Color.WHITE);
		
		JComboBox<Musica> comboBox1 = new JComboBox<Musica>();
		try {
			for(Musica m: g.getMusicasDoArtista(a)) {
				comboBox1.addItem(m);
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
				
				Musica m = (Musica) comboBox1.getSelectedItem();
				
				try {
					g.excluirMusicaArtista(m, a);
					popUp.dispose();
					dispose();
					new ConteudoArtista(g, a);
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fail.setText("Erro.");	
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
}
