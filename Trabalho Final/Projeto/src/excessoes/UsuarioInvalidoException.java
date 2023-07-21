package excessoes;

public class UsuarioInvalidoException extends Exception {

	public UsuarioInvalidoException () {
		
	}
	
	public UsuarioInvalidoException (String mensagem) {
		super(mensagem);
	}
}
