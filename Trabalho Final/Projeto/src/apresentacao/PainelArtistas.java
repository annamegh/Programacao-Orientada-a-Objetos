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

import dados.Artista;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class PainelArtistas extends JPanel {
	
	private JList<Artista> artistas;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private JButton addArtistaButton;
	private JButton excluirArtistaButton;
	private JLabel j;
	
	public PainelArtistas(MenuPrincipal menu, Gerenciador g) {
		
		panel.setLayout(null);
		panel.setBounds(20, 30, 390, 500);		
		this.setBackground(Color.PINK.darker());
		this.setLayout(null);
		this.setBackground(getBackground());

		excluirArtistaButton = new JButton("Exlcuir Artista");
		excluirArtistaButton.setBounds(20, 530, 120, 20);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpExclui("Nome do Artista:", "Excluir", g, menu);
			}
		};
		excluirArtistaButton.addActionListener(buttonListener3);
		excluirArtistaButton.setBackground(Color.PINK.darker().darker());
		excluirArtistaButton.setForeground(Color.WHITE);
		
		addArtistaButton = new JButton("Novo Artista");
		addArtistaButton.setBounds(140, 530, 120, 20);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpAdd("Nome da Artista:", "Adicionar", g, menu);
			}
		};
		addArtistaButton.addActionListener(buttonListener4);
		addArtistaButton.setBackground(Color.PINK.darker().darker());
		addArtistaButton.setForeground(Color.WHITE);
		
		j = new JLabel("Artistas cadastrados:");
		j.setBounds(20, 10, 150, 20);
		j.setForeground(Color.WHITE);
		
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
			artistas.setBounds(5, 0, 390, 18 * g.getArtistas(g.getU()).size());
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		artistas.setBackground(Color.PINK.darker().darker());
		artistas.setForeground(Color.WHITE);
		scroll = new JScrollPane(artistas);
		scroll.setBounds(0, 0, 390, 500);
		
		artistas.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				menu.dispose();
				new ConteudoArtista(g, artistas.getSelectedValue());
			}
		});
		
		panel.add(scroll);
		this.add(j);
		this.add(excluirArtistaButton);
		this.add(addArtistaButton);
		this.add(panel);
	}
	
	
	
	public void popUpAdd (String s1, String s2, Gerenciador g, MenuPrincipal menu) {
		JPanel panel = new JPanel();
		
		JFrame popUp = new JFrame(s2);
		popUp.setSize(300, 170);
		popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUp.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		JLabel j = new JLabel(s1);
		j.setBounds(30, 20, 100, 25);
		j.setForeground(Color.WHITE);
		
		JLabel fail = new JLabel("");
		fail.setBounds(30, 60, 150, 25);
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
					
					Artista a = new Artista();
					a.setNome(t);
					try {
						g.cadastrarArtista(a, g.getU());
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
			
		panel.add(j);
		panel.add(text1);
		panel.add(button);
		panel.add(button2);
		panel.add(fail);
		
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
		
		JComboBox<Artista> comboBox1 = new JComboBox<Artista>();
		try {
			for(Artista a: g.getArtistas(g.getU())) {
				comboBox1.addItem(a);
			}
		} catch (SelectException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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
					g.excluirArtista( (Artista) comboBox1.getSelectedItem(), g.getU());
					menu.dispose();
					new MenuPrincipal();
					popUp.dispose();
				}catch (DeleteException e1) {
					fail.setText("Artista não encontrado.");
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
