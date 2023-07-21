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
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dados.Musica;
import dados.Playlist;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class ConteudoPlaylist extends JFrame {

	private JList<Musica> musicas;
	private JPanel panel1;
	private JPanel panel2;
	private JScrollPane scroll;
	private JLabel j;
	private JButton excluirMusicaButton;
	private JButton addMusicaButton;
	private JButton voltarButton;
	
	public ConteudoPlaylist(Gerenciador g, Playlist p) {
				
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.PINK.darker());
		
		this.setSize(450, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(20, 30, 390, 530);		
		panel2.setBackground(Color.PINK.darker());
		
		excluirMusicaButton = new JButton("Exlcuir Musica");
		excluirMusicaButton.setBounds(20, 560, 120, 20);
		excluirMusicaButton.setBackground(Color.PINK.darker().darker());
		excluirMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpExclui("Nome da Música:", "Excluir", g, p);
			}
		};
		excluirMusicaButton.addActionListener(buttonListener3);
		
		addMusicaButton = new JButton("Add Musica");
		addMusicaButton.setBounds(140, 560, 120, 20);
		addMusicaButton.setBackground(Color.PINK.darker().darker());
		addMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAdd("Nome da música:", "Adicionar", g, p);
			}
		};
		addMusicaButton.addActionListener(buttonListener4);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(330, 560, 80, 20);
		voltarButton.setBackground(Color.PINK.darker().darker());
		voltarButton.setForeground(Color.WHITE);
		ActionListener buttonListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuPrincipal();
			}
		};
		voltarButton.addActionListener(buttonListener5);
				
		j = new JLabel("Playlist " + p.getNome() + ":");
		j.setBounds(20, 10, 150, 25);
		j.setForeground(Color.WHITE);
		
		DefaultListModel<Musica> list = new DefaultListModel<Musica>();
		try {
			for(Musica m: g.getMusicasP(p)) {
				list.addElement(m);
			}
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		musicas = new JList<Musica>(list);
		try {
			musicas.setBounds(0, 0, 390, 20 * g.getMusicasP(p).size());
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		musicas.setBackground(Color.PINK.darker().darker());
		musicas.setForeground(Color.WHITE);
		scroll = new JScrollPane(musicas);
		scroll.setBounds(0, 0, 390, 530);
		
		musicas.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				new ConteudoMusicaDaPlaylist(g, p, musicas.getSelectedValue());
			}
		});
		
		panel2.add(scroll);
		panel1.add(j);
		panel1.add(excluirMusicaButton);
		panel1.add(addMusicaButton);
		panel1.add(voltarButton);
		panel1.add(panel2);
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	
	public void popUpAdd (String s1, String s2, Gerenciador g, Playlist p) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame(s2);
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j = new JLabel(s1);
		j.setBounds(20, 20, 100, 25);
		j.setForeground(Color.WHITE);

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
		fail.setBounds(20, 60, 180, 25);
		fail.setForeground(Color.WHITE);
		
		JButton button = new JButton(s2);
		button.setBounds(20, 90, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					g.cadastrarMusicaP((Musica) comboBox1.getSelectedItem(), p);
					popUp.dispose();
					dispose();
					new ConteudoPlaylist(g, p);
				} catch (InsertException | SelectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fail.setText("A música já está na playlist.");
				}
			}
		};
		button.addActionListener(buttonListener);
		
		JButton button2 = new JButton("Voltar");
		button2.setBounds(170, 90, 80, 25);
		button2.setBackground(Color.PINK.darker());
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
	
	
	public void popUpExclui (String s1, String s2, Gerenciador g, Playlist p) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame(s2);
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j = new JLabel(s1);
		j.setBounds(20, 20, 100, 25);
		j.setForeground(Color.WHITE);
		
		JLabel fail = new JLabel("");
		fail.setBounds(20, 60, 220, 25);
		fail.setForeground(Color.WHITE);
		
		JComboBox<Musica> comboBox1 = new JComboBox<Musica>() ;
		try {
			for(Musica m: g.getMusicasP(p)) {
				comboBox1.addItem(m);
			}
		} catch (ClassNotFoundException | SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 45, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JButton button = new JButton(s2);
		button.setBounds(20, 80, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					g.excluirMusicaP((Musica) comboBox1.getSelectedItem(), p);
					dispose();
					new ConteudoPlaylist(g, p);
					popUp.dispose();
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		button.addActionListener(buttonListener);
		
		JButton button2 = new JButton("Voltar");
		button2.setBounds(160, 80, 80, 25);
		button2.setBackground(Color.PINK.darker());
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
}
