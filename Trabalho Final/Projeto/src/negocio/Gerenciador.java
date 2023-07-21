package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import excessoes.SenhaIncorretaException;
import excessoes.UsuarioInvalidoException;
import persistencia.ArtistaDAO;
import persistencia.Artista_MusicaDAO;
import persistencia.MusicaDAO;
import persistencia.PlaylistDAO;
import persistencia.Playlist_MusicaDAO;
import persistencia.UsuarioDAO;

public class Gerenciador {

	private UsuarioDAO usuarioDAO; 
	private ArtistaDAO artistaDAO;
	private MusicaDAO musicaDAO;
	private Artista_MusicaDAO artista_musicaDAO;
	private PlaylistDAO playlistDAO;
	private Playlist_MusicaDAO playlist_musicaDAO;
	
	//List<Usuario> usuarios = new ArrayList<Usuario>();
	private Usuario u;
	
	public Gerenciador() {
		try {
			usuarioDAO = UsuarioDAO.getInstance();
			artistaDAO = ArtistaDAO.getInstance();
			musicaDAO = MusicaDAO.getInstance();
			artista_musicaDAO = Artista_MusicaDAO.getInstance();
			playlistDAO = PlaylistDAO.getInstance();
			playlist_musicaDAO = Playlist_MusicaDAO.getInstance();
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		} catch (SelectException e3) {
			System.out.println(e3.getMessage());
		}
	}
	
	public List<Usuario> getUsuarios () throws SelectException{
		return usuarioDAO.selectAll();
	}
	
	public Usuario getU () {
		return this.u;
	}
	
	public int cadastrarUsuario (Usuario usuario) throws InsertException, SelectException {
		return usuarioDAO.insert(usuario);
	}
	
	public void excluirUsuario (Usuario usuario) throws DeleteException {
		usuarioDAO.delete(usuario);
	}
	
	public void logarUsuario (String nome, String senha) throws UsuarioInvalidoException, SenhaIncorretaException, SelectException {
		Usuario usuario = null;
		
		for (Usuario i: getUsuarios()) {
			if (i.getNome().equals(nome)) {
				usuario = i;
			}
		}
		if( usuario == null) 
			throw new UsuarioInvalidoException("Usuário não encontrado.");
	
		if (usuario.getSenha().equals(senha)) {
			this.u = usuario;
		}
		else 
			throw new SenhaIncorretaException("Senha incorreta.");
	}
	
	public void sairUsuario () {
		this.u = null;
	}
	
	public List<Musica> getMusicas (Usuario u) throws SelectException {
		return musicaDAO.selectAll(u);
	}
	
	public void cadastrarMusica (Musica musica, Usuario usuario) throws InsertException, SelectException {
		musicaDAO.insert(musica, usuario);
	}
	
	public void excluirMusica (Musica musica, Usuario usuario) throws DeleteException {
		musicaDAO.delete(musica, usuario);
	}
	
	public List<Playlist> getPlaylists (Usuario u) throws SelectException {
		return playlistDAO.selectAll(u);
	}
	
	public void cadastrarPlaylist (Playlist playlist, Usuario usuario) throws InsertException, SelectException {
		playlistDAO.insert(playlist, usuario);
	}
	
	public void excluirPlaylist (Playlist playlist, Usuario usuario) throws DeleteException {
		playlistDAO.delete(playlist, usuario);
	}
	
	public List<Artista> getArtistas (Usuario u) throws SelectException {
		return artistaDAO.selectAll(u);
	}
	
	public void cadastrarArtista (Artista artista, Usuario usuario) throws InsertException, SelectException {
		artistaDAO.insert(artista, usuario);
	}
	
	public void excluirArtista (Artista artista, Usuario usuario) throws DeleteException {
		artistaDAO.delete(artista, usuario);
	}
	
	public void cadastrarMusicaArtista (Musica musica, Artista artista) throws InsertException, SelectException {
		artista_musicaDAO.insert(musica, artista);
	}
	
	public void excluirMusicaArtista (Musica musica, Artista artista) throws DeleteException {
		artista_musicaDAO.delete(musica, artista);
	}
	
	public List<Artista> getArtistasDaMusica(Musica m) throws ClassNotFoundException, SelectException {
		return artista_musicaDAO.selectArtistasDaMusica(m, u);
	}
	
	public List<Musica> getMusicasDoArtista(Artista a) throws ClassNotFoundException, SelectException {
		return artista_musicaDAO.selectMusicasDoArtista(a, u);
	}
	
	public void cadastrarMusicaP (Musica musica, Playlist playlist) throws InsertException, SelectException {
		playlist_musicaDAO.insert(musica, playlist);
	}
	
	public void excluirMusicaP (Musica musica, Playlist playlist) throws DeleteException {
		playlist_musicaDAO.delete(musica, playlist);
	}
	
	public List<Musica> getMusicasP(Playlist p) throws ClassNotFoundException, SelectException {
		return playlist_musicaDAO.selectMusicasDaPlaylist(p, u);
	}
}
