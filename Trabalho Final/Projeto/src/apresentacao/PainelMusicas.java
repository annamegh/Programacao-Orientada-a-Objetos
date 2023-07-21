package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import dados.Artista;
import dados.Musica;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class PainelMusicas extends JPanel {
	
	private JList<Musica> musicas;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private JButton excluirMusicaButton;
	private JButton addMusicaButton;
	private JButton gerenciarButton;
	private JLabel j;
	
	public PainelMusicas(MenuPrincipal menu, Gerenciador g) {
			
		panel.setLayout(null);
		panel.setBounds(20, 30, 390, 500);	
		panel.setBackground(Color.PINK.darker().darker());
		this.setBackground(Color.PINK.darker());
		this.setLayout(null);

		excluirMusicaButton = new JButton("Exlcuir Musica");
		excluirMusicaButton.setBounds(20, 530, 120, 20);
		excluirMusicaButton.setBackground(Color.PINK.darker().darker());
		excluirMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpExclui("Nome da Música:", "Excluir", g, menu);
			}
		};
		excluirMusicaButton.addActionListener(buttonListener3);
		
		addMusicaButton = new JButton("Nova Musica");
		addMusicaButton.setBounds(140, 530, 120, 20);
		addMusicaButton.setBackground(Color.PINK.darker().darker());
		addMusicaButton.setForeground(Color.WHITE);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAdd("Nome da música:", "Adicionar", g, menu);
			}
		};
		addMusicaButton.addActionListener(buttonListener4);
		
		gerenciarButton = new JButton("Gerenciar Música");
		gerenciarButton.setBounds(260, 530, 150, 20);
		gerenciarButton.setBackground(Color.PINK.darker().darker());
		gerenciarButton.setForeground(Color.WHITE);
		ActionListener buttonListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpGerenciar(g, menu);
			}
		};
		gerenciarButton.addActionListener(buttonListener5);
		
		j = new JLabel("Músicas cadastradas:");
		j.setBounds(20, 10, 150, 20);
		j.setForeground(Color.WHITE);
		
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
			musicas.setBounds(0, 0, 390, 18 * g.getMusicas(g.getU()).size());
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		musicas.setForeground(Color.WHITE);
		musicas.setBackground(Color.PINK.darker().darker());
		scroll = new JScrollPane(musicas);
		scroll.setBounds(0, 0, 390, 500);
		
		musicas.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				menu.dispose();
				new ConteudoMusica(g, musicas.getSelectedValue());
				}
		});
		panel.add(scroll);
		this.add(j);
		this.add(excluirMusicaButton);
		this.add(addMusicaButton);
		this.add(gerenciarButton);
		this.add(panel);
	}
	
	
	
	public void popUpAdd (String s1, String s2, Gerenciador g, MenuPrincipal menu) {
		JPanel panel = new JPanel();
		JFileChooser chooser = new JFileChooser();
		
		JFrame popUp = new JFrame(s2);
		popUp.setSize(300, 200);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j = new JLabel(s1);
		j.setBounds(30, 20, 100, 25);
		j.setForeground(Color.WHITE);
		
		JTextField text1 = new JTextField();
		text1.setBounds(30, 40, 220, 25);
		
		JLabel fail = new JLabel("");
		fail.setBounds(30, 100, 150, 20);
		fail.setForeground(Color.WHITE);
		
		JButton button1 = new JButton("Selecionar arquivo");
		button1.setBounds(30, 70, 220, 25);
		button1.setBackground(Color.PINK.darker());
		button1.setForeground(Color.WHITE);
		button1.setBackground(Color.PINK.darker());
		button1.setForeground(Color.WHITE);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					chooser.getSelectedFile();
				}
			}
		});
		
		
		JButton button2 = new JButton(s2);
		button2.setBounds(30, 120, 100, 25);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(text1.getText().length() > 0) {
					String t = text1.getText(); 
					
					if(chooser.getSelectedFile() != null) {
						Musica m = new Musica();
						m.setNome(t);
						m.setArquivo(chooser.getSelectedFile().toString());
						System.out.println(chooser.getSelectedFile().toString());
						
						try {
							g.cadastrarMusica(m, g.getU());
							popUp.dispose();
							menu.dispose();
							new MenuPrincipal();
						} catch (InsertException | SelectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						fail.setText("Arquivo não selecionado.");
					}
				}
				else
					fail.setText("Campo não preenchido.");
			}
		};
		button2.addActionListener(buttonListener2);
		
		JButton button3 = new JButton("Voltar");
		button3.setBounds(160, 120, 90, 25);
		button3.setBackground(Color.PINK.darker());
		button3.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		};
		button3.addActionListener(buttonListener3);
			
		panel.add(j);
		panel.add(fail);
		panel.add(text1);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		popUp.setResizable(false);
		popUp.setVisible(true);
	}
	
	
	public void popUpGerenciar (Gerenciador g, MenuPrincipal menu) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame("Gerenciar Música");
		popUp.setSize(400, 230);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j1 = new JLabel("Escolha a música:");
		j1.setBounds(30, 20, 120, 25);
		j1.setForeground(Color.WHITE);
		
		JComboBox<Musica> comboBox1 = new JComboBox<Musica>();
		try {
			for(Musica m: g.getMusicas(g.getU())) {
				comboBox1.addItem(m);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox1.setBounds(40, 45, 300, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1) ;
		
		JLabel j2 = new JLabel("Escolha o artista:");
		j2.setBounds(30, 80, 120, 25);
		j2.setForeground(Color.WHITE);
		
		JComboBox<Artista> comboBox2 = new JComboBox<Artista>();
		try {
			for(Artista a: g.getArtistas(g.getU())) {
				comboBox2.addItem(a);
			}
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox2.setBounds(40, 105, 300, 20);
		comboBox2.setBackground(Color.WHITE);
		panel.add(comboBox2) ;
		
		JLabel fail = new JLabel("");
		fail.setBounds(30, 120, 150, 25);
		fail.setForeground(Color.WHITE);
		
		JButton button1 = new JButton("Excluir Artista");
		button1.setBounds(20, 140, 130, 25);
		button1.setBackground(Color.PINK.darker());
		button1.setForeground(Color.WHITE);
		button1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.excluirMusicaArtista( (Musica) comboBox1.getSelectedItem(), (Artista) comboBox2.getSelectedItem());
				} catch (DeleteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				popUp.dispose();
				menu.dispose();
				new MenuPrincipal();
			}
		});
		
		
		JButton button2 = new JButton("Cadastrar Artista");
		button2.setBounds(150, 140, 140, 25);
		button2.setBackground(Color.PINK.darker());
		button2.setForeground(Color.WHITE);
		button2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					g.cadastrarMusicaArtista((Musica) comboBox1.getSelectedItem(), (Artista) comboBox2.getSelectedItem());
				} catch (InsertException | SelectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				popUp.dispose();
				menu.dispose();
				new MenuPrincipal();			
			}
		});
		
		JButton button3 = new JButton("Voltar");
		button3.setBounds(280, 140, 80, 25);
		button3.setBackground(Color.PINK.darker());
		button3.setForeground(Color.WHITE);
		button3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.dispose();
			}
		});
		
		panel.add(j1);
		panel.add(j2);
		panel.add(fail);
		panel.add(comboBox1);
		panel.add(comboBox2);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
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
		fail.setBounds(20, 60, 220, 25);
		fail.setForeground(Color.WHITE);
		
		JComboBox<Musica> comboBox1 = new JComboBox<Musica>();
		try {
			for(Musica m: g.getMusicas(g.getU())) {
				comboBox1.addItem(m);
			}
		} catch (SelectException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboBox1.setBounds(40, 50, 200, 20);
		comboBox1.setBackground(Color.WHITE);
		panel.add(comboBox1);
		
		JButton button = new JButton(s2);
		button.setBounds(20, 90, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					g.excluirMusica((Musica) comboBox1.getSelectedItem(), g.getU());
					menu.dispose();
					new MenuPrincipal();
					popUp.dispose();
				} catch (DeleteException e1) {
					e1.printStackTrace();
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
