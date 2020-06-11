package br.damiao.financias;

import java.io.IOException;

import br.damiao.financias.gui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();

//		ScrollPane scrollPane = new ScrollPane(loadFXML("main"));
		
		FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main.fxml"));
		
		ScrollPane scrollPane = new ScrollPane(fxmlLoader.load());
		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);

		mainScene = new Scene(scrollPane);
		
		stage.setScene(mainScene);
		stage.setTitle("Login");
		stage.show();

	}

	static void setRoot(String fxml) throws IOException {
		mainScene.setRoot(loadFXML(fxml));
	}

	public static Scene getmainScene() {
		return mainScene;
	}
	
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}