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
import model.dao.ContaDao;
import models.entidade.Conta;
import models.entidade.Usuario;

public class ContaDaoJDBC implements ContaDao {

	private Connection conn;

	public ContaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Conta obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into conta (id, nome, saldo, id_usuario)\n" + 
					"values(?,?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId()); // temporário
			st.setString(2, obj.getNome());
			st.setDouble(3, obj.getSaldo());
			st.setInt(4, obj.getUsuario().getId()); 
			
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

	@Override
	public void update(Conta obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update conta set id = ?, nome = ?, saldo = ?, id_usuario = ? where id = ?;\n" + 
					"",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId()); // temporário
			st.setString(2, obj.getNome());
			st.setDouble(3, obj.getSaldo());
			st.setInt(4, obj.getUsuario().getId()); 
			st.setInt(5, obj.getId());
			st.executeUpdate();
			
//			int rowsAffected = st.executeUpdate();
//			if (rowsAffected > 0) {
//				ResultSet rs = st.getGeneratedKeys();
//				if (rs.next()) {
//					int id = rs.getInt(1);
//					obj.setId(id);
//				}
//				DB.closeResultSet(rs);
//			}
//			else {
//				throw new DbException("Erro ao inserir os dados");
//			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}


	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM conta WHERE Id = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new DbException("Erro ao deletar os dados");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Conta findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement("select \n" + 
					"	conta.id as id_conta,\n" + 
					"	conta.nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario,\n" + 
					"	usuario.nome as usuario_nome,\n" + 
					"	usuario.email,\n" + 
					"	senha\n" + 
					"	from conta inner join usuario on id_usuario = ?;");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Usuario usuario = instantiateUsuario(rs);
				Conta conta = instantiateConta(rs, usuario);

				return conta;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Conta> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select \n" + 
					"	conta.id as id_conta,\n" + 
					"	conta.nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario,\n" + 
					"	usuario.nome as usuario_nome,\n" + 
					"	usuario.email,\n" + 
					"	senha\n" + 
					"	from conta inner join usuario on id_usuario = usuario.id;");

			rs = st.executeQuery();

			List<Conta> list = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();

			while (rs.next()) {

				Usuario user = map.get(rs.getInt("id_usuario"));

				if (user == null) {
					user = instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), user);
				}

				Conta obj = instantiateConta(rs, user);
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
		obj.setId(rs.getInt("id_usuario"));
		obj.setNome(rs.getString("usuario_nome"));
		obj.setEmail(rs.getString("email"));
		obj.setSenha(rs.getString("senha"));
		return obj;
	}

	private Conta instantiateConta(ResultSet rs, Usuario usuario) throws SQLException {
		Conta conta = new Conta();
		conta.setId(rs.getInt("id_conta"));
		conta.setNome(rs.getString("nome"));
		conta.setSaldo(rs.getDouble("saldo"));
		conta.setUsuario(usuario);
		return conta;
	}

	@Override
	public List<Conta> findByUsuario(Usuario usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select \n" + 
					"	conta.id as id_conta,\n" + 
					"	conta.nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario,\n" + 
					"	usuario.nome as usuario_nome,\n" + 
					"	usuario.email,\n" + 
					"	senha\n" + 
					"	from conta inner join usuario on id_usuario = usuario.id\n" + 
					"	where id_usuario = ?;");

			st.setInt(1, usuario.getId());

			rs = st.executeQuery();

			List<Conta> list = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();

			while (rs.next()) {

				Usuario user = map.get(rs.getInt("id_usuario"));

				if (user == null) {
					user = instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), user);
				}

				Conta obj = instantiateConta(rs, user);
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
}
