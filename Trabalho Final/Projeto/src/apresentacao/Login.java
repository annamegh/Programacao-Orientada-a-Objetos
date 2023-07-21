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

import excessoes.SelectException;
import excessoes.SenhaIncorretaException;
import excessoes.UsuarioInvalidoException;

public class Login {
	
	private  JPanel panel;
	private  JFrame frame;
	private  JLabel sucess;
	private  JTextField userText;
	private  JPasswordField passwordText;
	private JButton button;
	private JButton voltarButton;
	

	public Login(){

		panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setLayout(null);
		
		frame = new JFrame();
		frame.setSize(320, 210);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.add(addLabel(30, 10, 200, 25, "Digite seus dados:"));
		panel.add(addLabel(30, 40, 80, 25, "Usuario"));
		panel.add(addLabel(30, 80, 80, 25, "Senha"));	
		
		userText = new JTextField();
		userText.setBounds(90, 40, 165, 25);
		userText.setBackground(Color.PINK.darker());
		userText.setForeground(Color.WHITE);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(90, 80, 165, 25);
		passwordText.setBackground(Color.PINK.darker());
		passwordText.setForeground(Color.WHITE);
		
		sucess = new JLabel("");
		sucess.setBounds(100, 100, 300, 25);
		sucess.setForeground(Color.WHITE);	
		
		button = new JButton("Login");
		button.setBounds(50, 130, 80, 25);
		button.setBackground(Color.PINK.darker());
		button.setForeground(Color.WHITE);
		button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userText.getText();
				String password = passwordText.getText();

				try {
					Main.getGerenciador().logarUsuario(user, password);
					
					userText.setText("");
					passwordText.setText("");
					sucess.setText("");
					frame.setVisible(false);
					
					new MenuPrincipal();
				}
				catch (SenhaIncorretaException e1){
					sucess.setText(e1.getMessage());
				}
				catch (UsuarioInvalidoException e2) {
					sucess.setText(e2.getMessage());
				} catch (SelectException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(150, 130, 80, 25);
		voltarButton.setBackground(Color.PINK.darker());
		voltarButton.setForeground(Color.WHITE);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				userText.setText("");
				passwordText.setText("");
				sucess.setText("");
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		voltarButton.addActionListener(buttonListener2);	

		panel.add(userText);
		panel.add(passwordText);
		panel.add(button);
		panel.add(voltarButton);
		panel.add(sucess);
		
		frame.setResizable(false);
		frame.setVisible(false);
		
	}

	public JLabel addLabel(int x, int y, int c, int l, String text) {
		JLabel j = new JLabel(text);
		j.setBounds(x, y, c, l);
		j.setForeground(Color.white);
		
		return j;
	}
	
	public void setVisibleTrue() {
		frame.setVisible(true);
	}
	
	public void setVisibleFalse() {
		frame.setVisible(false);
	}

}

