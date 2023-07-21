package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Musica;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class MusicaDAO {

		private static MusicaDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		
		public static MusicaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new MusicaDAO();
			return instance;
		}
		
		private MusicaDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('musicaID')" ) ;
			select = conn.prepareStatement("select * from musica where musicaID = ? AND usuarioID = ?");
			selectAll = conn.prepareStatement("select * from musica where usuarioID = ?");
			insert = conn.prepareStatement("insert into musica " + "(musicaID, nome, arquivo, UsuarioID) values (?, ?, ?, ?)");
			delete = conn.prepareStatement("delete from musica where musicaID = ? AND usuarioID = ?");
		}
		
		private int selectNewId() throws SelectException {
			try {
				ResultSet rs = selectNewId.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
			} catch( SQLException e) {
				throw new SelectException("Erro ao buscar novo ID na tabela musica");
			}
			
			return 0;
		}

		public Musica select (int musica_id, int usuario_id) throws SelectException {
			ResultSet rs;
			Musica m = null;
			try {
				select.setInt(1, musica_id);
				select.setInt(2, usuario_id);
				rs = select.executeQuery();
				if (rs.next()) {
					m = new Musica();
					m.setId(rs.getInt("musicaID"));
					m.setNome(rs.getString("nome"));
					m.setArquivo(rs.getString("arquivo"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar musica");
			}
			return m;
		}
		
		public void insert (Musica m, Usuario u) throws InsertException, SelectException {
			try {
				insert.setInt(1, selectNewId());
				insert.setString(2, m.getNome());
				insert.setString(3, m.getArquivo());
				insert.setInt(4, u.getId());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir música");
			}
		}
		
		public void delete (Musica m, Usuario u) throws DeleteException {
			try {
				delete.setInt(1, m.getId());
				delete.setInt(2, u.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar música");
			}
		} 
		
		public List<Musica> selectAll (Usuario u) throws SelectException {
			List<Musica> m = new ArrayList<Musica>();
			Musica mt;
			
			try {
				selectAll.setInt(1, u.getId());
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					mt = new Musica();
					mt.setId(rs.getInt("musicaID"));
					mt.setNome(rs.getString("nome"));
					mt.setArquivo(rs.getString("arquivo"));
					
					m.add(mt);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todas as músicas");
			}
			return m;
		}

}
