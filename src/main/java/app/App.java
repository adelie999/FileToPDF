package app;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 *
 * @author adelie
 *
 */
public class App {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// 変換前ファイル
		Path beforeFile = Paths.get("***");
		// 変換後ファイル
		String fileName = beforeFile.getFileName().toString();
		String pdfName = fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
		Path pdfFile = beforeFile.getParent().resolve(Paths.get(pdfName));

		// 変換
		convert(beforeFile, pdfFile);
	}

	/**
	 * 変換用メソッド
	 * @param excelFile
	 * @param pdfFile
	 */
	private static void convert(Path beforeFile, Path pdfFile) {
		// officeのパス
		final String OFFICE_HOME = "***";
		// officeのポート
		final int OFFICE_PORT = 8100;

		// officeの設定
	    DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
	    config.setOfficeHome(OFFICE_HOME);
	    config.setPortNumber(OFFICE_PORT);

	    // スタート
	    OfficeManager manager = config.buildOfficeManager();
	    manager.start();

	    // ストップ
	    OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
	    converter.convert(beforeFile.toFile(), pdfFile.toFile());
	    manager.stop();
	}

}
