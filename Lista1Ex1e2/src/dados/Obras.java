package dados;

public class Obras {

	private String titulo;
	private String tipo;
	private String autor;
	private String editora;
	private int ano;
	private String edicao;
	private String genero;
	private int codigo;
	private boolean emprestado = false;
	
	public Obras(String titulo, String autor, String editora, int ano, String edicao) {
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.ano = ano;
		this.edicao= edicao;
	}
	
	public Obras(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}
	
	public Obras(){
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipoDaObra() {
		return tipo;
	}
	public void setTipoDaObra(String tipoDaObra) {
		this.tipo = tipoDaObra;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public boolean isEmprestado() {
		return emprestado;
	}
	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}	
	public String toString() {
		return "Titulo: " + titulo + ", tipo de obra: " + tipo + ", Autor: " + autor + ", Editora: " + editora + ", Ano: " + ano
				+ ", Edição: " + edicao + ", Código: " + codigo + "Emprestado: " + emprestado + ".";
	}
	
	public boolean equals (Object objeto) {
		Obras obra;
		if( ! (objeto instanceof Obras)){
			return false;
		}
		obra = (Obras) objeto;
		if(obra.equals(obra)){
			return true;
		}
		return false;
	}
	
}
