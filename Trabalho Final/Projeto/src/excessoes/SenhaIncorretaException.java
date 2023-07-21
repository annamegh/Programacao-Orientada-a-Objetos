package excessoes;

public class SenhaIncorretaException extends Exception {

	public SenhaIncorretaException () {
		
	}
	
	public SenhaIncorretaException (String mensagem) {
		super(mensagem);
	}
	
}
