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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dados.Musica;
import dados.Playlist;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class PainelPlayLists extends JPanel {

	private JList<Playlist> playlists;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private JButton excluirButton;
	private JButton addButton;
	private JLabel j;
	
	public PainelPlayLists(MenuPrincipal menu, Gerenciador g) {
		
		panel.setLayout(null);
		panel.setBounds(20, 30, 390, 500);
		
		this.setLayout(null);
		this.setBackground(Color.PINK.darker());

		excluirButton = new JButton("Exlcuir Playlist");
		excluirButton.setBounds(20, 530, 120, 20);
		excluirButton.setBackground(Color.PINK.darker().darker());
		excluirButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpExclui("Nome da Playlist:", "Excluir", g, menu);
			}
		};
		excluirButton.addActionListener(buttonListener3);
		
		addButton = new JButton("Nova Playlist");
		addButton.setBounds(140, 530, 120, 20);
		addButton.setBackground(Color.PINK.darker().darker());
		addButton.setForeground(Color.WHITE);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAdd("Nome da Playlist:", "Adicionar", g, menu);
			}
		};
		addButton.addActionListener(buttonListener4);
		
		j = new JLabel("Playlists cadastradas:");
		j.setBounds(20, 10, 150, 20);
		j.setForeground(Color.WHITE);
		
		DefaultListModel<Playlist> list = new DefaultListModel<Playlist>();
		try {
			for(Playlist p: g.getPlaylists(g.getU())) {
				list.addElement(p);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		playlists = new JList<Playlist>(list);
		
		try {
			playlists.setBounds(5, 0, 390, 18 * g.getPlaylists(g.getU()).size());
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		playlists.setForeground(Color.WHITE);
		playlists.setBackground(Color.PINK.darker().darker());
		scroll = new JScrollPane(playlists);
		scroll.setBounds(0, 0, 390, 500);
		
		playlists.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				menu.dispose();
				new ConteudoPlaylist(g, playlists.getSelectedValue());
			}
		});
		
		panel.add(scroll);
		this.add(j);
		this.add(excluirButton);
		this.add(addButton);
		this.add(panel);
		
	}
	
	public void popUpAdd (String s1, String s2, Gerenciador g, MenuPrincipal menu) {
		JPanel panel1 = new JPanel();
		
		JFrame popUp = new JFrame(s2);
		popUp.setSize(300, 180);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel1);
		panel1.setLayout(null);
		panel1.setBackground(Color.PINK);
		
		JLabel j = new JLabel(s1);
		j.setBounds(30, 20, 100, 25);
		j.setForeground(Color.WHITE);
		
		JLabel fail = new JLabel("");
		fail.setBounds(30, 60, 150, 20);
		fail.setForeground(Color.WHITE);
		
		JTextField text1 = new JTextField();
		text1.setBounds(30, 40, 220, 25);
		
		JButton button = new JButton(s2);
		button.setBounds(30, 80, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(text1.getText().length() > 0) {
					String t = text1.getText(); 
					
					Playlist p = new Playlist();
					p.setNome(t);
					try {
						g.cadastrarPlaylist(p, g.getU());
						popUp.dispose();
						menu.dispose();
						new MenuPrincipal();
					} catch (InsertException | SelectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					fail.setText("Campo não preenchido.");
			}
		};
		button.addActionListener(buttonListener);
		
		JButton button2 = new JButton("Voltar");
		button2.setBounds(170, 80, 80, 25);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		};
		button2.addActionListener(buttonListener2);
			
		panel1.add(j);
		panel1.add(text1);
		panel1.add(button);
		panel1.add(button2);
		panel1.add(fail);
		popUp.setResizable(false);
		popUp.setVisible(true);
	}
	
	public void popUpExclui (String s1, String s2, Gerenciador g, MenuPrincipal menu) {
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
		
		JLabel fail = new JLabel("");
		fail.setBounds(20, 60, 150, 25);
		fail.setForeground(Color.WHITE);
		
		JComboBox<Playlist> comboBox1 = new JComboBox<Playlist>();
		try {
			for(Playlist p: g.getPlaylists(g.getU())) {
				comboBox1.addItem(p);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 50, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JButton button = new JButton(s2);
		button.setBounds(20, 90, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					g.excluirPlaylist( (Playlist) comboBox1.getSelectedItem(), g.getU());
					menu.dispose();
					new MenuPrincipal();
					popUp.dispose();
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fail.setText("Playlist não encontrada.");
				}
			}
		};
		button.addActionListener(buttonListener);
		
		JButton button2 = new JButton("Voltar");
		button2.setBounds(160, 90, 100, 25);
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
