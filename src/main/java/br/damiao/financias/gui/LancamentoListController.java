package br.damiao.financias.gui;

import java.io.IOException;

import br.damiao.financias.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.entidade.Lancamento;

public class LancamentoListController {

//	@FXML
//	private TableView<Lancamento> tableViewLancamento;
//
//	@FXML
//	private TableColumn<Lancamento, Integer> tableColumnId;
//
//	@FXML
//	private TableColumn<Lancamento, String> tableColumnNome;
//	
//	@FXML
//	private TableColumn<Lancamento, String> tableColumnData;
//	
//	@FXML
//	private TableColumn<Lancamento, Double> tableColumnValor;
//	
//	@FXML
//	private Button btnNovo;
	
	@FXML
	public void bntNovoAction() {
		
	}
	@FXML
	public synchronized void loadView(String nome) {
		
		try {
			FXMLLoader loader = new FXMLLoader(LancamentoListController.class.getResource(nome + ".fxml"));
			VBox newVBox = loader.load();
			
			Scene mainScene = App.getmainScene();
			
			ScrollPane spane = (ScrollPane) mainScene.getRoot();
			
//			spane.setContent(null);
			spane.setContent(newVBox);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
