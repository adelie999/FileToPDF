package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.function.FileConvert;
import model.util.MessageUtil;

/**
 * コントローラー
 * @author adelie
 *
 */
public class BasicController {

	@FXML
	private AnchorPane panel;
	@FXML
	private MenuItem open;
	@FXML
	private MenuItem exit;

	private final String ALL_FILE = "すべてのファイル";
	private final String ALL_EXTENSION = "*.*";
	private final String USER_HOME = "user.home";

	public BasicController() {
	}

	@FXML
	public void handleDragOver(DragEvent dragEvent) {
		Dragboard board = dragEvent.getDragboard();
		if (board.hasFiles()) {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		}
	}

	@FXML
	public void handleDragDropped(DragEvent dragEvent) {
		Dragboard board = dragEvent.getDragboard();
		List<File> list = new ArrayList<>();
		if (board.hasFiles()) {
			board.getFiles().forEach(file -> {
				list.add(file);
				new FileConvert().convert(file.toPath());
			});
			dragEvent.setDropCompleted(true);
			MessageUtil.getAlertMessage(AlertType.INFORMATION, AlertType.INFORMATION.toString(),
					MessageUtil.addMessage(list));
		} else {
			dragEvent.setDropCompleted(false);
		}
	}

	@FXML
	public void handleOpen(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(ALL_FILE, ALL_EXTENSION));
		fc.setInitialDirectory(new File(System.getProperty(USER_HOME)));
		File file = fc.showOpenDialog(null);
		if (file != null) {
			new FileConvert().convert(file.toPath());
			MessageUtil.getAlertMessage(AlertType.INFORMATION, AlertType.INFORMATION.toString(),
					MessageUtil.addMessage(file));
		}
	}

	@FXML
	public void handleExit(ActionEvent event) {
		Platform.exit();
	}

}
