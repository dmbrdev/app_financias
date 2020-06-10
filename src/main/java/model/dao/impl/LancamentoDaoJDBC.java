package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.LancamentoDao;
import models.entidade.Conta;
import models.entidade.Lancamento;

public class LancamentoDaoJDBC implements LancamentoDao {

	private Connection conn;

	public LancamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Lancamento obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Lancamento obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

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
		obj.setDescricao(rs.getString("descricao"));
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
