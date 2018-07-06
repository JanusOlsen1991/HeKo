package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Deadline;

public class Main extends Application {
	GUI g = new GUI();

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Scene scene = new Scene();
		g.hovedMenu(primaryStage);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
	}
}
