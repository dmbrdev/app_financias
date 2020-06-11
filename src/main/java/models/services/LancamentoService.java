package models.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LancamentoDao;
import models.entidade.Lancamento;

public class LancamentoService {

	private LancamentoDao dao = DaoFactory.createLancamentoDao();
	
	public List<Lancamento> findAll(){
		return dao.findAll();
	}
	
}
