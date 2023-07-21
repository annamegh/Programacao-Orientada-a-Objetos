package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Artista;
import dados.Musica;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class Artista_MusicaDAO {

	private static Artista_MusicaDAO instance = null;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement selectMusicasDoArtista;
	private PreparedStatement selectArtistasDaMusica;
	
	public static Artista_MusicaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
	if (instance == null)
		instance = new Artista_MusicaDAO();
		return instance;
	}
	
	private Artista_MusicaDAO() throws ClassNotFoundException , SQLException , SelectException {
		Connection conn = DatabaseConnection.getConnection();
		insert = conn.prepareStatement("insert into artista_musica  " + "(musicaID, artistaID) values (?, ?)");
		delete = conn.prepareStatement("delete from artista_musica  where musicaID = ? AND artistaID = ?");
		selectMusicasDoArtista = conn.prepareStatement("select * from artista_musica  where artistaID = ?");
		selectArtistasDaMusica = conn.prepareStatement("select * from artista_musica  where musicaID = ?");
	}
	
	public void insert (Musica m, Artista a) throws InsertException, SelectException {
		try {
			insert.setInt(1, m.getId());
			insert.setInt(2, a.getId());
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new InsertException("Erro ao inserir artista na musica");
		}
	}
	
	public void delete (Musica m, Artista a) throws DeleteException {
		try {
			delete.setInt(1, m.getId());
			delete.setInt(2, a.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DeleteException("Erro ao deletar artista da música");
		}
	} 
	
	public List<Musica> selectMusicasDoArtista (Artista a, Usuario u) throws SelectException, ClassNotFoundException {
		List<Musica> m = new ArrayList<Musica>();
		Musica mt;
		
		try {
			selectMusicasDoArtista.setInt(1, a.getId());
			ResultSet rs = selectMusicasDoArtista.executeQuery();
			while(rs.next()) {
				mt = MusicaDAO.getInstance().select(rs.getInt("musicaID"), u.getId());
				m.add(mt);
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar as músicas do artista");
		}
		
		return m;
	}
	
	public List<Artista> selectArtistasDaMusica (Musica m, Usuario u) throws SelectException, ClassNotFoundException {
		List<Artista> a = new ArrayList<Artista>();
		Artista at;
		
		try {
			selectArtistasDaMusica.setInt(1, m.getId());
			ResultSet rs = selectArtistasDaMusica.executeQuery();
			while(rs.next()) {
				at = ArtistaDAO.getInstance().select(rs.getInt("artistaID"), u.getId());
				a.add(at);
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar os artista da musica");
		}
		
		return a;
	}
}
