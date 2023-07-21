package dados;

public class Playlist {

	private int id;
	private String nome;
//	List<Musica> musicasP = new ArrayList<Musica>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Playlist() {
		
	}
	public Playlist(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
/*	public List<Musica> getMusicasP() {
		return musicasP;
	}

	public boolean cadastrarMusicaP (Musica musica) {
		if(! musicasP.contains(musica))
			return musicasP.add(musica);
		else 
			return false;
	}
	
	public boolean excluirMusicaP (Musica musica) {
		if ( musicasP.contains(musica)) {
			musicasP.remove(musicasP.indexOf(musica));
			return true;
		}
		else return false;
	}
*/	
	public String toString() {
		return nome;
	}
}
