package app;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.LancamentoDao;
import models.entidade.Conta;
import models.entidade.Lancamento;

public class Applicacao {

	public static void main(String[] args) {

		LancamentoDao lanDao = DaoFactory.createLancamentoDao();
		
		Lancamento lan = lanDao.findById(1);

		System.out.println("=== Lancamento by id ===");
		System.out.println(lan.toString());
		
		// =================================
		
		//teste busca por conta
		System.out.println("=== Lancamento by Conta ===");
		Conta conta = new Conta(1,"Poupança",900,null);
		List<Lancamento> lans = new ArrayList<>();
		
		lans = lanDao.findByConta(conta);
		
		for (Lancamento lancamento : lans) {
			System.out.println(lancamento);
		}
		
	}
}
