package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Playlist;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class PlaylistDAO {

	private static PlaylistDAO instance = null;
	private PreparedStatement selectNewId;
	private PreparedStatement select;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement selectAll;
	
	public static PlaylistDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
	if (instance == null)
		instance = new PlaylistDAO();
		return instance;
	}
	
	private PlaylistDAO() throws ClassNotFoundException , SQLException , SelectException {
		Connection conn = DatabaseConnection.getConnection();
		selectNewId = conn.prepareStatement( "select nextval ('playlistID')" ) ;
		select = conn.prepareStatement("select * from playlist where playlistID = ? AND usuarioID = ?");
		selectAll = conn.prepareStatement("select * from playlist where usuarioID = ?");
		insert = conn.prepareStatement("insert into playlist " + "(playlistID, nome, usuarioID) values (?, ?, ?)");
		delete = conn.prepareStatement("delete from playlist where playlistID = ? AND usuarioID = ?");
	}
	
	private int selectNewId() throws SelectException {
		try {
			ResultSet rs = selectNewId.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch( SQLException e) {
			throw new SelectException("Erro ao buscar novo ID na tabela arista");
		}
		
		return 0;
	}

	public Playlist select (int artista_id, int usuario_id) throws SelectException {
		ResultSet rs;
		Playlist p = null;
		try {
			select.setInt(1, artista_id);
			select.setInt(2, usuario_id);
			rs = select.executeQuery();
			if (rs.next()) {
				p = new Playlist();
				p.setId(rs.getInt("playlistID"));
				p.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar playlist");
		}
		return p;
	}
	
	public void insert (Playlist p, Usuario u) throws InsertException, SelectException{
		try {
			insert.setInt(1, selectNewId());
			insert.setString(2, p.getNome());
			insert.setInt(3, u.getId());
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new InsertException("Erro ao inserir playlist");
		}
	}
	
	public void delete (Playlist p, Usuario u) throws DeleteException {
		try {
			delete.setInt(1, p.getId());
			delete.setInt(2, u.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DeleteException("Erro ao deletar playlist");
		}
	} 
	
	public List<Playlist> selectAll (Usuario u) throws SelectException {
		List<Playlist> p = new ArrayList<Playlist>();
		Playlist pt;
		
		try {
			selectAll.setInt(1, u.getId());
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()) {
				pt = new Playlist();
				pt.setId(rs.getInt("playlistID"));
				pt.setNome(rs.getString("nome"));
				
				p.add(pt);
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar todas as playlists");
		}
		return p;
	}
}
