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
import model.dao.LancamentoDao;
import model.entidade.Conta;
import model.entidade.Lancamento;

public class LancamentoDaoJDBC implements LancamentoDao {

	private Connection conn;

	public LancamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Lancamento obj) {
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("insert into lancamento ( descricao, dt_lancamento, valor, id_conta)\n" + 
			"values (?, ?, ?, ?);",
			Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getData().getTime()));
			st.setDouble(3, obj.getValor());
			
			try {
				st.setInt(4, obj.getConta().getId());
			} catch (Exception e) {
				st.setInt(4, 1);
			}
			
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
	public void update(Lancamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update lancamento set\n" + 
					"id = ?, descricao = ?, dt_lancamento = ?, valor=?, id_conta=?\n" + 
					"where id = ?;",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId()); // temporário
//			st.setNull(1, (Integer) null);
			
			
			st.setString(2, obj.getNome());
			st.setDate(3, new java.sql.Date(obj.getData().getTime()));
			st.setDouble(4, obj.getValor());
//			st.setInt(5, obj.getConta().getId());
			st.setInt(5, 1);
			st.setInt(6, obj.getId());
			
//			st.setInt(5, obj.getConta().getId());
			
			st.executeUpdate();

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
			st = conn.prepareStatement("DELETE FROM lancamento WHERE Id = ?");
			
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
	public Lancamento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement("select \n" + 
					"	lancamento.id,\n" + 
					"	descricao,\n" + 
					"	dt_lancamento,\n" + 
					"	valor,\n" + 
					"	nome,\n" + 
					"	id_conta,\n" + 
					"	nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario\n" + 
					"from lancamento\n" + 
					"inner join conta on conta.id = lancamento.id_conta\n" + 
					"where lancamento.id = ?;");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta conta = instantiateConta(rs);
				Lancamento lan = instantiateLancamento(rs, conta);
				return lan;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Lancamento instantiateLancamento(ResultSet rs, Conta conta) throws SQLException {
		Lancamento obj = new Lancamento();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("descricao"));
		obj.setValor(rs.getDouble("valor"));
		obj.setData(rs.getDate("dt_lancamento"));
		obj.setConta(conta);
		return obj;
	}

	private Conta instantiateConta(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		conta.setId(rs.getInt("id_conta"));
		conta.setNome(rs.getString("nome"));
		conta.setSaldo(rs.getDouble("saldo"));
		return conta;
	}

	@Override
	public List<Lancamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select \n" + 
					"	lancamento.id,\n" + 
					"	descricao,\n" + 
					"	dt_lancamento,\n" + 
					"	valor,\n" + 
					"	nome,\n" + 
					"	id_conta,\n" + 
					"	nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario\n" + 
					"from lancamento\n" + 
					"inner join conta on conta.id = lancamento.id_conta\n" + 
					"order by lancamento.id;");

			rs = st.executeQuery();

			List<Lancamento> list = new ArrayList<>();
			Map<Integer, Conta> map = new HashMap<>();

			while (rs.next()) {

				Conta cont = map.get(rs.getInt("id_conta"));

				if (cont == null) {
					cont = instantiateConta(rs);
					map.put(rs.getInt("id_conta"), cont);
				}

				Lancamento obj = instantiateLancamento(rs, cont);
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

	@Override
	public List<Lancamento> findByConta(Conta conta) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select \n" + 
					"	lancamento.id,\n" + 
					"	descricao,\n" + 
					"	dt_lancamento,\n" + 
					"	valor,\n" + 
					"	nome,\n" + 
					"	id_conta,\n" + 
					"	nome,\n" + 
					"	saldo,\n" + 
					"	id_usuario\n" + 
					"from lancamento\n" + 
					"inner join conta on conta.id = lancamento.id_conta\n" + 
					"where id_conta = ?\n" + 
					"order by lancamento.id;");

			st.setInt(1, conta.getId());

			rs = st.executeQuery();

			List<Lancamento> list = new ArrayList<>();
			Map<Integer, Conta> map = new HashMap<>();

			while (rs.next()) {

				Conta cont = map.get(rs.getInt("id_conta"));

				if (cont == null) {
					cont = instantiateConta(rs);
					map.put(rs.getInt("id_conta"), cont);
				}

				Lancamento obj = instantiateLancamento(rs, cont);
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
