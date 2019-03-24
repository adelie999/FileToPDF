package model.util;

import java.io.File;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * メッセージに関するユーティリティクラス
 * @author adelie
 */
public class MessageUtil {

	private static String FILENAME_MESSAGE = "%s をpdfに変換しました。";
	private static String FILECOUNT_MESSAGE = "... %d 件のファイルを変換しました。";

	private MessageUtil() {
	}

	public static void getAlertMessage(final AlertType alertType, final String title, final String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.show();
	}

	public static String addMessage(List<File> list) {
		StringBuilder builder = new StringBuilder();
		list.forEach(file -> {
			builder.append(String.format(FILENAME_MESSAGE, file.getName()));
			builder.append(System.lineSeparator());
		});
		builder.append(System.lineSeparator());
		builder.append(String.format(FILECOUNT_MESSAGE, list.size()));
		return builder.toString();
	}

	public static String addMessage(File file) {
		return String.format(FILENAME_MESSAGE, file.getName());
	}

}
