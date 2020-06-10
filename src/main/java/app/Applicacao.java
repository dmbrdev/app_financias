package app;

import model.dao.DaoFactory;
import model.dao.LancamentoDao;
import models.entidade.Lancamento;

public class Applicacao {

	public static void main(String[] args) {

		LancamentoDao lanDao = DaoFactory.createLancamentoDao();
		
		Lancamento lan = lanDao.findById(1);
		
		System.out.println(lan.toString());

	}
}
