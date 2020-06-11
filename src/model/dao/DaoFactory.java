package model.dao;

import db.DB;
import model.dao.impl.ContaDaoJDBC;
import model.dao.impl.LancamentoDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static LancamentoDao createLancamentoDao() {
		return new LancamentoDaoJDBC(DB.getConnection());
	}

	public static ContaDao createContaDao() {
		return new ContaDaoJDBC(DB.getConnection());
	}
}
