package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entidade.Conta;
import model.entidade.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

	private Connection conn;

	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Usuario obj) {
		
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into usuario (id, nome,email,senha)\n" + 
					"values(?,?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId()); // temporário
			st.setString(2, obj.getNome());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getSenha());
			
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro ao inserir os dados");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

//	@Override
//	public void update(Usuario obj) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Usuario findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Usuario> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select id, nome,email,senha from usuario;");

			rs = st.executeQuery();

			List<Usuario> list = new ArrayList<>();

			while (rs.next()) {

				Usuario obj = instantiateUsuario(rs);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		Usuario obj = new Usuario();
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));
		obj.setEmail(rs.getString("email"));
		obj.setSenha(rs.getString("senha"));
		return obj;
	}
	
}
