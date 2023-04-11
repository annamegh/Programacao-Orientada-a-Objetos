import java.util.Random;

public class Sorteador {

	private Pessoa pessoas[] = new Pessoa[5];
	private int quant = 5;
	
	public void geraPessoas() {
	//gera objetos da classe pessoa e passa eles para a array pessoas
		for(int i=0; i< pessoas.length; i++) {
			Pessoa p = new Pessoa();
			pessoas[i] = p;
		}
	}
	
	
	public Pessoa sortearProximo () {
	//Sorteia uma pessoa para retornar e exclui ela da lista de pessoas
		
		if (quant > 0) {
			Random random = new Random();
			int n = random.nextInt(quant);
			
			Pessoa p = new Pessoa();
			p = pessoas[n];
			
			for(int i=n; i< pessoas.length - 1; i++) {
				pessoas[i] = pessoas[i+1];
			}
			pessoas[quant-1] = null;
			quant --;
			
			return p;
		}
		else return null;
	}

	public Pessoa[] getPessoas() {
		return pessoas;
	}

	public int getQuant() {
		return quant;
	}
	
}
