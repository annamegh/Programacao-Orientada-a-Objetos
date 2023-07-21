package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial  {
	
	private JFrame frame;
	private JPanel tela;
	private JLabel label1;
	private JLabel label2;
	private JButton cadastroButton;
	private JButton loginButton;

	public TelaInicial(){
		
		tela = new JPanel();
		tela.setBackground(Color.PINK);
		
		frame = new JFrame();
		frame.setSize(250, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(tela);
				
		tela.setLayout(null);
				
		label1 = new JLabel("Cadastrar novo usuário:");
		label1.setBounds(45, 20, 150, 25);
		label1.setForeground(Color.WHITE);
		
		cadastroButton = new JButton("Cadastro");
		cadastroButton.setBounds(45, 45, 140, 25);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getCadastrarUsuario().setVisibleTrue();
			}
		};
		cadastroButton.addActionListener(buttonListener1);
		cadastroButton.setBackground(Color.PINK.darker());
		cadastroButton.setForeground(Color.WHITE);
		
		label2 = new JLabel("Realizar o login:");
		label2.setBounds(45, 90, 100, 25);
		label2.setForeground(Color.WHITE);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(45, 115, 140, 25);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getLogin().setVisibleTrue();				
			}
		};
		loginButton.addActionListener(buttonListener2);
		loginButton.setBackground(Color.PINK.darker());
		loginButton.setForeground(Color.WHITE);
		
		tela.add(label1);
		tela.add(cadastroButton);
		tela.add(label2);
		tela.add(loginButton);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
		
	public void setVisibleTrue() {
		frame.setVisible(true);
	}
	
	public void setVisibleFalse() {
		frame.setVisible(false);
	}

}
