package aplicacao;

import java.io.IOException;

import gui.InicioControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene mainScene;

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(InicioControler.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
	
	@Override
	public void start(Stage stage) throws IOException {

		//Carregar View
		ScrollPane scrollPane = (ScrollPane) loadFXML("Inicio");
		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);

		mainScene = new Scene(scrollPane);

		stage.setScene(mainScene);
		stage.setTitle("Login");
		stage.show();
	}
	
	public static Scene getmainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch();
	}
}
	
	