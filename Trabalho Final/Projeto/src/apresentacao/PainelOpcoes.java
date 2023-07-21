package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import excessoes.DeleteException;
import negocio.Gerenciador;

public class PainelOpcoes extends JPanel {
	
	private JLabel j1;
	private JLabel j2;
	private JButton sairButton;
	private JButton excluirUsuarioButton;

	public PainelOpcoes(JFrame frame, Gerenciador g) {
		
		this.setLayout(null);
		this.setBackground(Color.PINK.darker());
		
		j1 = new JLabel("Sair do programa:");
		j1.setBounds(10, 10, 120, 25);
		j1.setForeground(Color.white);
		j2 = new JLabel("Excluir Usuário:");
		j2.setBounds(220, 10, 100, 25);
		j2.setForeground(Color.white);
		
		sairButton = new JButton("Sair");
		sairButton.setBounds(120, 10, 60, 25);
		sairButton.setBackground(Color.PINK.darker().darker());
		sairButton.setForeground(Color.WHITE);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getGerenciador().sairUsuario();
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		sairButton.addActionListener(buttonListener1);
		
		
		excluirUsuarioButton = new JButton("Exlcuir");
		excluirUsuarioButton.setBackground(Color.PINK.darker().darker());
		excluirUsuarioButton.setForeground(Color.WHITE);
		excluirUsuarioButton.setBounds(320, 10, 80, 25);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					Main.getGerenciador().excluirUsuario(g.getU());
				} catch (DeleteException e1) {
					e1.printStackTrace();
				}
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		excluirUsuarioButton.addActionListener(buttonListener2);
		
		this.add(j1);
		this.add(sairButton);
		this.add(j2);
		this.add(excluirUsuarioButton);
	}
	
}
