
public class Main {

	public static void main (String[] args) {
		
		Sorteador s = new Sorteador();
		
		s.geraPessoas();
		s.getPessoas()[0].setNome("Maria");
		s.getPessoas()[1].setNome("João");
		s.getPessoas()[2].setNome("Ana");
		s.getPessoas()[3].setNome("Kaue");
		s.getPessoas()[4].setNome("José");
		
		Pessoa p = new Pessoa();
		p = s.sortearProximo();
		System.out.println("1ª pessoa sorteada: " + p.getNome());
		
		p = s.sortearProximo();
		System.out.println("2ª pessoa sorteada: " + p.getNome());
		
		p = s.sortearProximo();
		System.out.println("3ª pessoa sorteada: " + p.getNome());
		
		p = s.sortearProximo();
		System.out.println("4ª pessoa sorteada: " + p.getNome());
		
		p = s.sortearProximo();
		System.out.println("5ª pessoa sorteada: " + p.getNome());
		
	}
}
