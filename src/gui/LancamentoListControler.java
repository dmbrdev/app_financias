package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.entidade.Lancamento;
import model.services.LancamentosService;

public class LancamentoListControler implements Initializable {

	private LancamentosService service;

	private ObservableList<Lancamento> obsList;

	public void setService(LancamentosService service) {
		this.service = service;
	}

	@FXML
	private TableView<Lancamento> tableLancamento;

	@FXML
	private TableColumn<Lancamento, Lancamento> tabelaBtnEditar;

	@FXML
	private TableColumn<Lancamento, String> colunaNome;

	@FXML
	private TableColumn<Lancamento, String> colunaConta;

	@FXML
	private TableColumn<Lancamento, String> colunaData;

	@FXML
	private TableColumn<Lancamento, Double> colunaValor;

	@FXML
	public Button btnNovo;

	public void btnNovoAcao() throws IOException {

		System.out.println("Novo Lancamento");

		// Novo VBox
		VBox newVBox = (VBox) App.loadFXML("LancamentoForm");

		// Pegar Palco principal
		Scene mainScene = App.getmainScene();
		ScrollPane spane = (ScrollPane) mainScene.getRoot();

		// Setar Novo VBox
		spane.setContent(newVBox);

		// Carregar formLancamento
		LancamentFormController l = new LancamentFormController();
//		l.atualizarForm();

	}

//	@FXML
//	public Button btnCarregar;
//
//	public void btnCarregarAcao() {
//		atualzarTabela();
//	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (service != null) {
			setService(new LancamentosService());
		}

//		System.out.println("Iniciando sem chamar");
		try {
			iniciarNodes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void iniciarNodes() throws IOException {

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		colunaConta.setCellValueFactory(new PropertyValueFactory<>("conta"));

		setService(new LancamentosService());
		atualzarTabela();
	}

	public static void carregar() throws IOException {
		// Novo VBox
		VBox newVBox = (VBox) App.loadFXML("LancamentoList");

		// Pegar Palco principal
		Scene mainScene = App.getmainScene();
		ScrollPane spane = (ScrollPane) mainScene.getRoot();

		// Setar Novo VBox
		spane.setContent(newVBox);

	}

	public void atualzarTabela() {

		// Recuperar lista de lancamento do servico
		List<Lancamento> lista = service.findAll();

		// Carregar na ObsList
		obsList = FXCollections.observableArrayList(lista);

		tableLancamento.getItems().clear();
		tableLancamento.setItems(obsList);
		addBtnEditar();

		System.out.println(lista);
	}

	private void addBtnEditar() {
		TableColumn<Lancamento, Void> colBtn = new TableColumn("Button Column");

		Callback<TableColumn<Lancamento, Void>, TableCell<Lancamento, Void>> cellFactory = new Callback<TableColumn<Lancamento, Void>, TableCell<Lancamento, Void>>() {
			@Override
			public TableCell<Lancamento, Void> call(final TableColumn<Lancamento, Void> param) {
				final TableCell<Lancamento, Void> cell = new TableCell<Lancamento, Void>() {

					private final Button btn = new Button("Editar");

					{
						btn.setOnAction((ActionEvent event) -> {

							Lancamento data = getTableView().getItems().get(getIndex());

							LancamentFormController.entidade = data;
							try {
								LancamentFormController.carregar();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		tableLancamento.getColumns().add(colBtn);

	}

}
