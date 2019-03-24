package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.util.PropertyUtil;

/**
 *
 * @author adelie
 *
 */
public class MainApp extends Application {
	private enum App {
		AppFxml, AppCss, AppTitle;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			PropertyUtil property = PropertyUtil.getInstnce();
			VBox root = (VBox) FXMLLoader.load(getClass().getResource(property.getValue(App.AppFxml.toString())));
			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource(property.getValue(App.AppCss.toString())).toExternalForm());
			primaryStage.setTitle(property.getValue(App.AppTitle.toString()));
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
