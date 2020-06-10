package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.ContaDao;
import model.dao.DaoFactory;
import model.dao.LancamentoDao;
import model.dao.UsuarioDao;
import models.entidade.Conta;
import models.entidade.Lancamento;
import models.entidade.Usuario;

public class Applicacao {

	public static void main(String[] args) {

		
		ContaDao contaDao = DaoFactory.createContaDao();
		// Conta conta = new Conta(1,"Poupanca", 399, null);
		

		// teste busca por ID
		Conta lan = contaDao.findById(1);
		System.out.println("=== Conta by id ===");
		System.out.println(lan.toString());
		
		
		
		// teste busca por usuario
		System.out.println("=== Conta by Usuario ===");
		Usuario usuario = new Usuario(2,"Damiao","Damiao@gmail.com","2233");
		List<Conta> contas = new ArrayList<>();
		contas = contaDao.findByUsuario(usuario);
		for (Conta conta: contas) {
			System.out.println(conta);
		}

		
		// teste busca todos
		System.out.println("=== Conta ALl ===");
		contas = new ArrayList<>();
		contas = contaDao.findAll();
		for (Conta conta : contas) {
			System.out.println(conta);
		}
		
////		// inserindo conta
//		System.out.println("=== Conta Insert ===");
//		Conta conta2 = new Conta(15, "Investimento", 500, usuario);
//		contaDao.insert(conta2);
//		System.out.println(conta2.getId());
//		
//		
//		// Atualizando conta
//		System.out.println("=== Conta Update ===");
//		System.out.println("Valor antes: " + conta2.getSaldo());
//		conta2.setSaldo(1234d);
//		contaDao.update(conta2);
//		System.out.println("Valor Depois: " + conta2.getSaldo());
		
//		// Apagando Conta
		System.out.println("=== Conta Delete ===");
		contaDao.deleteById(12);
		
		
		
		
		
//		UsuarioDao usuario = DaoFactory.createUsuarioDao();
//		LancamentoDao lanDao = DaoFactory.createLancamentoDao();
//		Usuario usuario = new Usuario(1,"Damiao","damiao@gmail.com");

//		// teste busca por ID
//		Lancamento lan = lanDao.findById(1);
//
//		System.out.println("=== Lancamento by id ===");
//		System.out.println(lan.toString());
//
//		// teste busca por conta
//		System.out.println("=== Lancamento by Conta ===");
//		Conta conta = new Conta(1, "Poupança", 900, null);
//		List<Lancamento> lans = new ArrayList<>();
//
//		lans = lanDao.findByConta(conta);
//
//		for (Lancamento lancamento : lans) {
//			System.out.println(lancamento);
//		}
//
//		// teste busca todos
//		System.out.println("=== Lancamento ALl ===");
//		lans = new ArrayList<>();
//
//		lans = lanDao.findAll();
//
//		for (Lancamento lancamento : lans) {
//			System.out.println(lancamento);
//		}
//
////		// inserindo lancamento
////		System.out.println("=== Lancamento Insert ===");
////		Lancamento lan2 = new Lancamento(5,"Console Wi",new Date(), 420.00d, conta);
////		lanDao.insert(lan2);
////		System.out.println(lan2.getId());
////		
////		// Atualizando lancamento
////		System.out.println("=== Lancamento Update ===");
////		System.out.println("Valor antes: " + lan2.getValor());
////		lan2.setValor(1234d);
////		lanDao.update(lan2);
////		System.out.println("Valor Depois: " + lan2.getValor());
//	
//		// Apagando lancamento
//		System.out.println("=== Lancamento Delete ===");
//		lanDao.deleteById(5);
				
	}
}
