package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Artista;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class ArtistaDAO {

	private static ArtistaDAO instance = null;
	private PreparedStatement selectNewId;
	private PreparedStatement select;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement selectAll;
	
	public static ArtistaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
	if (instance == null)
		instance = new ArtistaDAO();
		return instance;
	}
	
	private ArtistaDAO() throws ClassNotFoundException , SQLException , SelectException {
		Connection conn = DatabaseConnection.getConnection();
		selectNewId = conn.prepareStatement( "select nextval ('artistaID')" ) ;
		select = conn.prepareStatement("select * from artista where artistaID = ? AND usuarioID = ?");
		selectAll = conn.prepareStatement("select * from artista where usuarioID = ?");
		insert = conn.prepareStatement("insert into artista " + "(artistaID, nome, usuarioID) values (?, ?, ?)");
		delete = conn.prepareStatement("delete from artista where artistaID = ? AND usuarioID = ?");
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

	public Artista select (int artista_id, int usuario_id) throws SelectException {
		ResultSet rs;
		Artista a = null;
		try {
			select.setInt(1, artista_id);
			select.setInt(2, usuario_id);
			rs = select.executeQuery();
			if (rs.next()) {
				a = new Artista();
				a.setId(rs.getInt("artistaID"));
				a.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar artista");
		}
		return a;
	}
	
	public void insert (Artista a, Usuario u) throws InsertException, SelectException{
		try {
			insert.setInt(1, selectNewId());
			insert.setString(2, a.getNome());
			insert.setInt(3, u.getId());
			insert.executeUpdate();
		} catch (SQLException e) {
			throw new InsertException("Erro ao inserir artista");
		}
	}
	
	public void delete (Artista a, Usuario u) throws DeleteException {
		try {
			delete.setInt(1, a.getId());
			delete.setInt(2, u.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			throw new DeleteException("Erro ao deletar artista");
		}
	} 
	
	public List<Artista> selectAll (Usuario u) throws SelectException {
		List<Artista> a = new ArrayList<Artista>();
		Artista at;
		
		try {
			selectAll.setInt(1, u.getId());
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()) {
				at = new Artista();
				at.setId(rs.getInt("artistaID"));
				at.setNome(rs.getString("nome"));
				
				a.add(at);
			}
		} catch (SQLException e) {
			throw new SelectException("Erro ao buscar todos os artistas");
		}
		
		return a;
	}

}
