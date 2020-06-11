package model.dao;

import java.util.List;

import model.entidade.Conta;
import model.entidade.Lancamento;

public interface LancamentoDao {

	void insert(Lancamento obj);
	void update(Lancamento obj);
	void deleteById(Integer id);
	Lancamento findById(Integer id);
	List<Lancamento> findAll();

	List<Lancamento> findByConta(Conta conta);
}
