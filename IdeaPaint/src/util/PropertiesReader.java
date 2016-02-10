package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 *
 * @author ueno
 *
 */
public class PropertiesReader {
	private Properties prop_;
	String propertyFileName_;

	public PropertiesReader(String propertyFileName) {
		this.propertyFileName_ = propertyFileName;
		initialize();
	}

	private void initialize(){
		prop_ = new Properties();
//		readProperities(); //read を外部から呼ぶようにする
	}

	public void readProperities(){
		InputStream inStream = null;
		try {
			inStream = new BufferedInputStream(
					new FileInputStream(propertyFileName_));
			prop_.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeProperties(){
    	try {
    		//ファイルがなければ作成する
    		File file = new File(propertyFileName_);
    		file.createNewFile();

			prop_.store(new FileOutputStream(propertyFileName_), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getProp() {
		return prop_;
	}
}