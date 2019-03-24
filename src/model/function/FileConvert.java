package model.function;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 * ファイルをpdfに変換するクラス
 * @author adelie
 *
 */
public class FileConvert {
	private final String OFFICE_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
	private final int OFFICE_PORT = 8100;
	private final String PDF = ".pdf";

	public FileConvert() {
	}

	public void convert(Path beforeFile) {
		Path afterFile = beforeFile.getParent().resolve(Paths.get(reName(beforeFile)));
		convertToPDF(beforeFile, afterFile);
	}

	private void convertToPDF(Path beforeFile, Path pdfFile) {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
		config.setOfficeHome(OFFICE_HOME);
		config.setPortNumber(OFFICE_PORT);

		OfficeManager manager = config.buildOfficeManager();
		manager.start();

		OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
		converter.convert(beforeFile.toFile(), pdfFile.toFile());
		manager.stop();
	}

	private String reName(Path beforeFile) {
		String fileName = beforeFile.getFileName().toString();
		String pdfName = fileName.substring(0, fileName.lastIndexOf(".")) + PDF;
		return pdfName;
	}

}
