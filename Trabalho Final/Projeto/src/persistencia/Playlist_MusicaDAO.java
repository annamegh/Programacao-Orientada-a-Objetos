package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Musica;
import dados.Playlist;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class Playlist_MusicaDAO {

	private static Playlist_MusicaDAO instance = null;
	private PreparedStatement insert;
	private PreparedStatement select;
	private PreparedStatement delete;
	private PreparedStatement selectMusicasDaPlaylist;
	
	public static Playlist_MusicaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
	if (instance == null)
		instance = new Playlist_MusicaDAO();
		return instance;
	}
	
	private Playlist_MusicaDAO() throws ClassNotFoundException , SQLException , SelectException {
		Connection conn = DatabaseConnection.getConnection();
		insert = conn.prepareStatement("insert into playlist_musica  " + "(musicaID, playlistID) values (?, ?)");
		select = conn.prepareStatement("select * from playlist_musica where musicaID = ? AND playlistID = ? ");
		delete = conn.prepareStatement("delete from playlist_musica  where musicaID = ? AND playlistID = ?");
		selectMusicasDaPlaylist = conn.prepareStatement("select * from playlist_musica where playlistID = ?");
	}
	
	public void insert (Musica m, Playlist p) throws InsertException, SelectException {
		try {
			insert.setInt(1, m.getId());
			insert.setInt(2, p.getId());
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new InsertException("Erro ao inserir musica na playlist");
		}
	}
	
	public Musica select (int musica_id, int playlist_id,int usuario_id) throws SelectException, ClassNotFoundException {
		ResultSet rs;
		Musica m = null;
		try {
			select.setInt(1, musica_id);
			select.setInt(2, playlist_id);
			rs = select.executeQuery();
			if (rs.next()) {
				m = MusicaDAO.getInstance().select(musica_id, usuario_id);

			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar musica na playlists");
		}
		return m;
	}
	
	public void delete (Musica m, Playlist p) throws DeleteException {
		try {
			delete.setInt(1, m.getId());
			delete.setInt(2, p.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DeleteException("Erro ao deletar musica na playlists");
		}
	} 
	
	public List<Musica> selectMusicasDaPlaylist (Playlist p, Usuario u) throws SelectException, ClassNotFoundException {
		List<Musica> m = new ArrayList<Musica>();
		Musica mt;
		
		try {
			selectMusicasDaPlaylist.setInt(1, p.getId());
			ResultSet rs = selectMusicasDaPlaylist.executeQuery();
			while(rs.next()) {
				mt = MusicaDAO.getInstance().select(rs.getInt("musicaID"), u.getId());
				m.add(mt);
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar as músicas da playlist");
		}
		return m;
	}
}
