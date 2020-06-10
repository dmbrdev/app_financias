package app;

import java.util.ArrayList;
import java.util.Date;
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

		// teste busca por conta
		System.out.println("=== Lancamento by Conta ===");
		Conta conta = new Conta(1, "Poupança", 900, null);
		List<Lancamento> lans = new ArrayList<>();

		lans = lanDao.findByConta(conta);

		for (Lancamento lancamento : lans) {
			System.out.println(lancamento);
		}

		// teste busca todos
		System.out.println("=== Lancamento ALl ===");
		lans = new ArrayList<>();

		lans = lanDao.findAll();

		for (Lancamento lancamento : lans) {
			System.out.println(lancamento);
		}

		// inserindo lancamento
		System.out.println("=== Lancamento Insert ===");
		Lancamento lan2 = new Lancamento(5,"Console Wi",new Date(), 420.00d, conta);
		lanDao.insert(lan2);
		System.out.println(lan2.getId());
		
		// Atualizando lancamento
		System.out.println("=== Lancamento Update ===");
		System.out.println("Valor antes: " + lan2.getValor());
		lan2.setValor(1234d);
		lanDao.update(lan2);
		System.out.println("Valor Depois: " + lan2.getValor());
	

	}
}
