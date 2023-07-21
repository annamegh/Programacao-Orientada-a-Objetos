package dados;


public class Musica {

	private int id;
	private String nome;
	private String arquivo;
//	List<Artista> artistasM = new ArrayList<Artista>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
/*	public List<Artista> getArtistas() {
		return artistasM;
	}
	
	public String[] getArtistasMString() {
		String[] s = new String[artistasM.size()];
		
		for(int i = 0; i < artistasM.size(); i++) {
			s[i] = artistasM.get(i).getNome();
		}
		return s;
	}

	public boolean cadastrarArtistaMusica( Artista artista) {
		if(! artistasM.contains(artista))
			return artistasM.add(artista);
		else
			return false;
	}
	
	public boolean excluirArtistaMusica (Artista artista) {
		if ( artistasM.contains(artista)) {
			artistasM.remove(artistasM.indexOf(artista));
			return true;
		}
		else return false;
	}
*/	
	public String toString() {
		return nome;
	}
}
