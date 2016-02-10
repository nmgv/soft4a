package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {
	private static final String defaultSeparator = ",";

	private FileReader fr = null;
	private ArrayList<ArrayList<String>> data;
	private String fileName, separator;

	// 初期化
	public CSVReader(String fileName, String separator) throws FileNotFoundException {
		this.fileName = fileName;
		this.separator = separator;
		init();
	}

	public CSVReader(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		this.separator = defaultSeparator;
		init();
	}
	
	public void init() throws FileNotFoundException {
		fr = new FileReader(fileName);
		data = new ArrayList<ArrayList<String>>();
	}
	
	// CSVファイルを読み込み ArrayList を返す
	public ArrayList<ArrayList<String>> read() throws IOException {
		BufferedReader br = new BufferedReader(fr);
		String lineText;
		while((lineText = br.readLine()) != null) {
			String[] split = lineText.split(separator);
			ArrayList<String> line = new ArrayList<String>(Arrays.asList(split));
			data.add(line);
		}
		br.close();
		fr.close();

		return data;
	}
}
