package ph.kana.clock.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private final static double APP_WIDTH = 300;
	private final static double APP_HEIGHT = 180;

	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("fxml/clock.fxml"));

		stage.setTitle("Dot-Dot Clock");
		stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));

		stage.setResizable(false);
		stage.setMinWidth(APP_WIDTH);
		stage.setMinHeight(APP_HEIGHT);

		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
