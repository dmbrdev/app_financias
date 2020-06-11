package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InicioControler implements Initializable {

	@FXML
	public Button btnLogin;
	
	@FXML
	public TextField txtEmail;	

	@FXML
	public TextField txtSenha;
	
	public void btnLoginAcao() throws IOException {		
		LancamentoListControler.carregar();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
