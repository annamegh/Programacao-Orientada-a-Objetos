package apresentacao;

import negocio.Gerenciador;

public class Main {

	private static Gerenciador gerenciador = new Gerenciador();
	private static TelaInicial tela = new TelaInicial();
	private static CadastrarUsuario cadastrar = new CadastrarUsuario();
	private static Login login = new Login();
		
	public static void main(String[] args) {
		
	}
	
	public static Gerenciador getGerenciador() {
		return gerenciador;
	}
	
	public static CadastrarUsuario getCadastrarUsuario() {
		return cadastrar;
	}
	
	public static Login getLogin() {
		return login;
	}
	
	public static TelaInicial getTelaInicial() {
		return tela;
	}

}
