package apresentacao;

import dados.Emprestimo;
import dados.Multa;
import dados.Obras;
import dados.Pessoa;
import negocio.SistemaBiblioteca;

public class Main {
	
	public static void main (String[] args) {
		
		//construtor vazio
		Obras o1 = new Obras();
		o1.setTitulo("1984");
		o1.setAutor("George Orwell");
		o1.setAno(2009);
		o1.setEditora("Companhia das Letras");
		o1.setEdicao("1ª");
		
		Pessoa p1 = new Pessoa();
		p1.setNome("Maria Joaquina");
		p1.setCpf("200");
		p1.setDataNascimento("15/06/2003");
		
		Emprestimo e1 = new Emprestimo();
		e1.setDataEmprestimo("20/08/2022");
		e1.setDataDevolucao("4/09/2022");
		e1.setCodigoReserva(1001);
		e1.setObra(o1);
		
		Multa m1 = new Multa();
		m1.setAtraso(2);
		m1.setValor();
		
		e1.setMulta(m1);
		p1.setEmprestimos(e1);
		
		System.out.println(p1.toString());
		System.out.println(e1.toString());
		
		SistemaBiblioteca sistema1 = new SistemaBiblioteca();
		sistema1.setPessoas(p1);
		sistema1.setObras(o1);
		
		//construtor com valores
		Pessoa p2 = new Pessoa("Julia", "100", "13/08/1996");
		Obras o2 = new Obras("Percy Jackson", "Rick Riordan", "Intrínseca", 2015, "1ª");
		Emprestimo e2 = new Emprestimo("10/08/2022", "30/08/2022", o2, 1002);
		Multa m2 = new Multa(7);
		
		e2.setMulta(m2);
		p2.setEmprestimos(e2);
		
		System.out.println(p2.toString());
		System.out.println(e2.toString());
		
		SistemaBiblioteca sistema2 = new SistemaBiblioteca(p2, o2);
		sistema2.setPessoas(p1);
		sistema2.setObras(o1);
		
		System.out.println(sistema2.toString());
		
		//Dá pra fazer funções de cadastrar pessoas, cadastrar livros, emprestar livros, etc...
		
	}
}
