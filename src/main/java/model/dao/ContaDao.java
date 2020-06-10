package model.dao;

import java.util.List;

import models.entidade.Conta;
import models.entidade.Usuario;

public interface ContaDao {

	void insert(Conta obj);
	void update(Conta obj);
	void deleteById(Integer id);
	Conta findById(Integer id);
	List<Conta> findAll();
	
	List<Conta> findByUsuario(Usuario usuario);
	
}
