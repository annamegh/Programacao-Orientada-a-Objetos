package dados;

public class Pessoa {

	private String nome;
	private String cpf;
	private String dataNascimento;
	Emprestimo emprestimos[] = new Emprestimo[5];
	private int quantEmprestimos;
	
	public Pessoa() {
		
	}
	public Pessoa(String nome, String cpf, String nasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = nasc;
	}
	
	public Emprestimo[] getEmprestimo() {
		return emprestimos;
	}
	public void setEmprestimos(Emprestimo emprestimos) {
		if(this.quantEmprestimos < 5) {
		this.emprestimos[this.quantEmprestimos] = emprestimos; 
		this.quantEmprestimos++;
		}
		else System.out.println("Limite de obras emprestadas atingido.");
	}
	public int getQuantEmprestimos() {
		return quantEmprestimos;
	}
	public void setQuantEmprestimos(int quantEmprestimos) {
		this.quantEmprestimos = quantEmprestimos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Data de nascimento: " + dataNascimento + ", Quantidade de obras emprestadas: " + quantEmprestimos + ".";
	}
	
	public boolean equals (Object objeto) {
		Pessoa pessoa;
		if( ! (objeto instanceof Pessoa)){
			return false;
		}
		pessoa = (Pessoa) objeto;
		if(pessoa.equals(pessoa)){
			return true;
		}
		return false;
	}
	
}
