package gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import aplicacao.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.entidade.Conta;
import model.entidade.Lancamento;
import model.services.LancamentosService;

public class LancamentFormController implements Initializable {

	public static Lancamento entidade;

	private LancamentosService lanService;

	@FXML
	public TextField txtId;

//	@SuppressWarnings("exports")
	@FXML
	public TextField txtNome;

	@FXML
	public TextField txtData;

	@FXML
	public TextField txtValor;

	@FXML
	public ComboBox<Conta> Conta;

	@FXML
	public Button btnSalvar;

	@FXML
	public Button btnCancelar;
	
	@FXML
	public Button btnApagar;

	@FXML
	public void btnApagarAcao() throws IOException {
		lanService.apagar(entidade);
		LancamentoListControler.carregar();
	}
	
	@FXML
	public void btnSalvarAcao() throws ParseException, IOException {

		entidade = getFormDados();
		lanService.salvarOuAtualizar(entidade);

		// depois de salvar
		LancamentoListControler.carregar();

//		atualizarForm();
	}

	private Lancamento getFormDados() throws ParseException {
		
		Lancamento lan = new Lancamento();	
	
		try {
			lan.setId(Integer.parseInt(txtId.getText()));
		} catch (Exception e) {
			lan.setId(null);
		}

		lan.setNome(txtNome.getText());
		lan.setValor(Double.parseDouble(txtValor.getText()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		lan.setData(sdf.parse(txtData.getText()));

		lan.setConta(null);

		System.out.println("Lan antes do retorno + " + lan.toString());

		return lan;
	}

	@FXML
	public void btnCancelarAcao() {
		System.out.println("Cancelar");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setLanService(new LancamentosService());
		
		try {
			atualizarForm();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void atualizarForm() throws ParseException {
	
		if(entidade ==  null) {
			entidade =  new Lancamento();
		}
		try {
			txtId.setText(String.valueOf(entidade.getId()));
			txtNome.setText(entidade.getNome());
			txtData.setText(new SimpleDateFormat("yyyy-mm-dd").format(entidade.getData()));
			txtValor.setText(String.valueOf(entidade.getValor()));
		} catch (Exception e) {
			
		}
		

	}

	public static void carregar() throws IOException {
		// Novo VBox
		VBox newVBox = (VBox) App.loadFXML("LancamentoForm");

		// Pegar Palco principal
		Scene mainScene = App.getmainScene();
		ScrollPane spane = (ScrollPane) mainScene.getRoot();

		// Setar Novo VBox
		spane.setContent(newVBox);

	}

	public void setEntidade(Lancamento entidade) {
		this.entidade = entidade;
	}

	public void setLanService(LancamentosService lanService) {
		this.lanService = lanService;
	}

}
