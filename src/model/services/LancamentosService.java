package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LancamentoDao;
import model.entidade.Lancamento;

public class LancamentosService {

	private LancamentoDao dao = DaoFactory.createLancamentoDao();

	public List<Lancamento> findAll() {

		return dao.findAll();
	}

	public void salvarOuAtualizar(Lancamento lan) {

		System.out.println("Salvando ou Atualizando ===");
		System.out.println(lan.toString());

		if (lan.getId() == null) {
			dao.insert(lan);
		} else {
			dao.update(lan);
		}
	}

	public void apagar(Lancamento lan) {
		dao.deleteById(lan.getId());
	}
}
