package dados;

public class Artista {

	private int id;
	private String nome;
//	private ArrayList<Musica> musicasA = new ArrayList<Musica>();

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

/*	public ArrayList<Musica> getMusicasA() {
		return musicasA;
	}

	public boolean cadastrarMusicaArtista (Musica musica) {
		if(! musicasA.contains(musica))
			return musicasA.add(musica);
		else
			return false;
	}
	
	public boolean excluirMusicaArtista (Musica musica) {
		if ( musicasA.contains(musica)) {
			musicasA.remove(musicasA.indexOf(musica));
			return true;
		}
		else return false;
	}
*/	
	public String toString() {
		return nome;
	}
}
