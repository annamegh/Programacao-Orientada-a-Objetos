package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class UsuarioDAO  {
	
		private static UsuarioDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		
		public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new UsuarioDAO();
			return instance;
		}
		
		private UsuarioDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('usuarioID')" ) ;
			select = conn.prepareStatement("select * from usuario where usuarioID = ? ");
			selectAll = conn.prepareStatement("select * from usuario");
			insert = conn.prepareStatement("insert into public.usuario" + "(usuarioID, username, senha) values (?, ?, ?)");
			delete = conn.prepareStatement("delete from usuario where usuarioID = ?");
		}
		
		private int selectNewId() throws SelectException {
			try {
				ResultSet rs = selectNewId.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
			} catch( SQLException e) {
				throw new SelectException("Erro ao buscar novo ID na tabela usuario");
			}
			
			return 0;
		}

		public Usuario select (int code) throws SelectException {
			ResultSet rs;
			Usuario us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Usuario();
					us.setId(rs.getInt("usuarioID"));
					us.setNome(rs.getString("nome"));
					us.setSenha(rs.getString("senha"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar usuario");
			}
			return us;
		}
		
		public int insert (Usuario us) throws InsertException, SelectException{
			try {
				int code = selectNewId();
				insert.setInt(1, code);
				insert.setString(2, us.getNome());
				insert.setString(3, us.getSenha());
				insert.executeUpdate();
				return code;
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir usuario");
			}
		}
		
		public void delete (Usuario us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar usuário");
			}
		} 
		
		public List<Usuario> selectAll () throws SelectException {
			List<Usuario> us = new ArrayList<Usuario>();
			Usuario u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Usuario();
					u.setId(rs.getInt(1));
					u.setNome(rs.getString(2));
					u.setSenha(rs.getString(3));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos os usuários");
			}
			return us;
		}
}

