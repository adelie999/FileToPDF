package model.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * プロパティファイルに関するユーティリティクラス
 * @author adelie
 */
public class PropertyUtil {

	private static PropertyUtil instance;
	private final Properties PROPERTY = new Properties();
	private final String COMMON_FILE = System.getProperty("user.dir") + "/src/resouce/common.properties";

	private PropertyUtil() {
		load();
	}

	public static synchronized PropertyUtil getInstnce() {
		if (instance == null) {
			instance = new PropertyUtil();
		}
		return instance;
	}

	public String getValue(final String key) {
		return PROPERTY.getProperty(key);
	}

	private void load() {
		try {
			PROPERTY.load(new FileReader(COMMON_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
