package dados;

public class Usuario {

	private String nome;
	private String senha;
	private int id;
//	private List<Musica> musicas = new ArrayList<Musica>();
//	private List<Playlist> playlists = new ArrayList<Playlist>();
//	private List<Artista> artistas = new ArrayList<Artista>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
/*	public List<Musica> getMusicas() {
		return musicas;
	}
	
	public boolean cadastrarMusica (Musica musica) {
		return musicas.add(musica);
	}
	
	public boolean excluirMusica (Musica musica) {
		if(musicas.contains(musica)) {
			musicas.remove(musica);
			
			for(Artista a: artistas) {
				if(a.getMusicasA().contains(musica)) {
					a.excluirMusicaArtista(musica);
				}
			}
			
			for(Playlist p: playlists) {
				if(p.getMusicasP().contains(musica)) {
					p.excluirMusicaP(musica);
				}
			}
			return true;
		}
		return false;
	}
*/	
/*	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public boolean cadastrarPlaylist (Playlist playlist) {
		return playlists.add(playlist);
	}
	
	public boolean excluirPlaylist (Playlist playlist) {
		if(playlists.contains(playlist)) {
			playlists.remove(playlist);

			return true;
		}
		return false;
	}
*/	
/*	public List<Artista> getArtistas() {
		return artistas;
	}

	public boolean cadastrarArtista (Artista artista) {
		return artistas.add(artista);
	}
	
	public boolean excluirArtista (Artista artista) {
		if(artistas.contains(artista)) {
			artistas.remove(artista);
			
			for(Musica m: musicas) {
				if(m.getArtistas().contains(artista)) {
					m.excluirArtistaMusica(artista);
				}
			}
			return true;
		}
		return false;
	}
*/	
	public String toString() {
		return nome;
	}
}