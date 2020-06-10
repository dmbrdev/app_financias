package model.dao;

import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

	public UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC();
	}
}
