package negocio;
import dados.Obras;
import dados.Pessoa;

public class SistemaBiblioteca {

	private Pessoa pessoas[] = new Pessoa[50];
	private Obras obras[] = new Obras[100];
	private int quantPessoas;
	private int quantObras;
	
	public SistemaBiblioteca() {
		
	}
	public SistemaBiblioteca(Pessoa p, Obras o) {
		this.pessoas[this.quantPessoas] = p;
		this.quantPessoas++;
		
		this.obras[this.quantObras] = o;
		this.quantObras++;
	}
	
	public Pessoa[] getPessoas() {
		return pessoas;
	}
	public void setPessoas(Pessoa pessoas) {
		if(this.quantPessoas < 50) {
			this.pessoas[this.quantPessoas] = pessoas;
			this.quantPessoas++;
		}
		else System.out.println("Limite de pessoas cadastradas atingido.");
		
	}
	public Obras[] getObras() {
		return obras;
	}
	public void setObras(Obras obras) {
		if(this.quantObras < 100) {
			this.obras[this.quantObras ] = obras;
			this.quantObras ++;
		}
		else System.out.println("Limite de obras cadastradas atingido.");
	}
	public int getQuantPessoas() {
		return quantPessoas;
	}
	public void setQuantPessoas(int quantPessoas) {
		this.quantPessoas = quantPessoas;
	}
	public int getQuantObras() {
		return quantObras;
	}
	public void setQuantObras(int quantObras) {
		this.quantObras = quantObras;
	}
	
	public String toString() {
		String s = "";
		s+= "Obras: \n";
		for(int i=0; i< this.quantObras; i++) {
			s += obras[i].toString() + "\n";
		}
		s+= "Pessoas: \n";
		for(int i=0; i< this.quantPessoas; i++) {
			s += pessoas[i].toString() + "\n";
		}
		return s;
	}
	
	public boolean equals (Object objeto) {
		SistemaBiblioteca sistemaB;
		if( ! (objeto instanceof SistemaBiblioteca)){
			return false;
		}
		sistemaB = (SistemaBiblioteca) objeto;
		if(sistemaB.equals(sistemaB)){
			return true;
		}
		return false;
	}
	
	
}
