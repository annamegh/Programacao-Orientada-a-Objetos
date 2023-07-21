package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dados.Playlist;
import dados.Usuario;
import excessoes.InsertException;
import excessoes.SelectException;

public class CadastrarUsuario{
	
	private  JFrame frame;
	private  JPanel panel;
	private  JLabel sucess;
	private  JTextField userText;
	private  JPasswordField passwordText;
	private  JPasswordField confirmPasswordText;
	private JButton button;
	private JButton voltarButton;

	public CadastrarUsuario() {

		panel = new JPanel();
		panel.setBackground(Color.PINK);
		
		frame = new JFrame();
		frame.setSize(350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		panel.add(addLabel(20, 10, 200, 25, "Digite seus dados:"));
		panel.add(addLabel(50, 40, 150, 25, "Usuario"));
		panel.add(addLabel(50, 80, 150, 25, "Senha"));	
		panel.add(addLabel(20, 120, 150, 25, "Confirmar Senha"));
		
		userText = new JTextField();
		userText.setBounds(120, 40, 165, 25);
		userText.setBackground(Color.PINK.darker());
		userText.setForeground(Color.WHITE);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(120, 80, 165, 25);
		passwordText.setBackground(Color.PINK.darker());
		passwordText.setForeground(Color.WHITE);
		
		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(120, 120, 165, 25);
		confirmPasswordText.setBackground(Color.PINK.darker());
		confirmPasswordText.setForeground(Color.WHITE);
		
		button = new JButton("Cadastrar");
		button.setBounds(50, 170, 100, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( userText.getText().length() > 0 && passwordText.getText().length() > 0) {
					String user = userText.getText();
					String password = passwordText.getText();
					String confirm = confirmPasswordText.getText();

					if( password.equals(confirm)) {
						Usuario us = new Usuario();
						us.setNome(user);
						us.setSenha(password);
						
						try {
							int code = Main.getGerenciador().cadastrarUsuario(us);
							us.setId(code);
						} catch (InsertException | SelectException e1) {
							System.out.println(e1.getMessage());
						}
						
						Playlist favoritas = new Playlist();
						favoritas.setNome("Favoritas");
						try {
							Main.getGerenciador().cadastrarPlaylist(favoritas, us);
						} catch (InsertException | SelectException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						sucess.setText("");
						frame.setVisible(false);
						userText.setText("");
						passwordText.setText("");
						confirmPasswordText.setText("");
						frame.invalidate();
						frame.validate();
						frame.repaint();
						Main.getTelaInicial().setVisibleTrue();
						
					}
					else 
						sucess.setText("Senhas diferentes.");
				}
				else 
					sucess.setText("Campo não preenchido.");
				
			}
		};
		button.addActionListener(buttonListener1);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(180, 170, 70, 25);
		voltarButton.setBackground(Color.PINK.darker());
		voltarButton.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				userText.setText("");
				passwordText.setText("");
				confirmPasswordText.setText("");
				sucess.setText("");
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		voltarButton.addActionListener(buttonListener2);
		
		sucess = new JLabel("");
		sucess.setBounds(120, 140, 300, 25);
		sucess.setForeground(Color.WHITE);
		
		sucess.setText(null);
		
		panel.add(userText);
		panel.add(passwordText);
		panel.add(confirmPasswordText);
		panel.add(button);
		panel.add(voltarButton);
		panel.add(sucess);
		
		frame.setResizable(false);
		frame.setVisible(false);
	}
	
	public JLabel addLabel(int x, int y, int c, int l, String text) {
		JLabel j = new JLabel(text);
		j.setBounds(x, y, c, l);
		j.setForeground(Color.WHITE);
		
		return j;
	}

	public void setVisibleTrue() {
		frame.setVisible(true);
	}
	
	public void setVisibleFalse() {
		frame.setVisible(false);
	}

}
