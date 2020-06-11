package br.damiao.financias.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.damiao.financias.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import models.entidade.Lancamento;

public class MainController implements Initializable{

	@FXML
	public Button btnLogin;
	
	@FXML
	public void btnLoginAction() {
		loadView("lancamentosList");
	}
	
	@FXML
	public synchronized void loadView(String nome) {
		
		try {
			FXMLLoader loader = new FXMLLoader(MainController.class.getResource(nome + ".fxml"));
			VBox newVBox = loader.load();
			
			Scene mainScene = App.getmainScene();
			
			ScrollPane spane = (ScrollPane) mainScene.getRoot();
			
//			spane.setContent(null);
			spane.setContent(newVBox);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
