package dados;

public class Emprestimo {

	private String dataEmprestimo;
	private String dataDevolucao;
	private int codigoReserva;
	Obras obra = new Obras();
	Multa multa = new Multa();
	
	public Emprestimo() {
		
	}
	public Emprestimo(String dataE, String dataD, Obras obra, int codigo) {
		this.dataEmprestimo = dataE;
		this.dataDevolucao = dataD;
		this.codigoReserva = codigo;
		
		obra.setEmprestado(true);
		this.obra = obra;		
	}
	
	public String getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public int getCodigoReserva() {
		return codigoReserva;
	}
	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	public Obras getObra() {
		return obra;
	}
	public void setObra(Obras obra) {
		obra.setEmprestado(true);
		this.obra = obra;
	}
	public Multa getMulta() {
		return multa;
	}
	public void setMulta(Multa multa) {
		this.multa = multa;
	}
	
	public String toString() {
		return "Emprestimo: " + dataEmprestimo + ", Devolução: " + dataDevolucao + ", Código da Reserva: "
				+ codigoReserva + ", Obra: " + obra.getTitulo() + ", atraso na devolução: " + multa.getAtraso() + " dias.";
	}
	
	public boolean equals (Object objeto) {
		Emprestimo emprestimo;
		if( ! (objeto instanceof Emprestimo)){
			return false;
		}
		emprestimo = (Emprestimo) objeto;
		if(emprestimo.equals(emprestimo)){
			return true;
		}
		return false;
	}
	
	
}
