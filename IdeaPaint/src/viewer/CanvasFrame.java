package viewer;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import util.CSVReader;
import util.FigFileParser;
import util.Util;
import command.ColorCommand;
import command.Command;
import command.DrawCanvas;
import command.DrawFillOvalCommand;
import command.DrawFillRectCommand;
import command.MacroCommand;

public class CanvasFrame extends JFrame implements ActionListener, MouseMotionListener, WindowListener {
	private int width_ = 400;
	private int height_ = 400;

	//描画履歴
	private MacroCommand history_ = new MacroCommand();
	//描画領域
	private DrawCanvas canvas_ = new DrawCanvas(width_, height_, history_);

	//消去ボタン
	private JButton clearButton_ = new JButton("clear");
	//カラーボタン
	private JButton colorButton_ = new JButton("Color");

	private JButton fillRectButton_ = new JButton("Rect");

	private JButton fillOvalButton_ = new JButton("Oval");

	private JButton saveButton_ = new JButton("save");

	private JButton keywordButton_ = new JButton("keyword!");

	private JButton testFuncButton = new JButton("テスト");

	private Random r_ = new Random();

	private String keyword_ = "てすと";

	//複数回の描画
	private MacroCommand multiHistory_ = new MacroCommand();

	private String date_;

	private Calendar cal_ = Calendar.getInstance();

	private JTextField textField_ = new JTextField();

	//コンストラクタ
	public CanvasFrame(String title) {
		super(title);

		this.setBounds(700, 300, getWidth(), getHeight());
		this.addWindowListener(this);

		canvas_.addMouseMotionListener(this);
		clearButton_.addActionListener(this);
		colorButton_.addActionListener(this);
		fillRectButton_.addActionListener(this);
		fillOvalButton_.addActionListener(this);
		saveButton_.addActionListener(this);
		keywordButton_.addActionListener(this);
		testFuncButton.addActionListener(this);

		Box buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(saveButton_);
		buttonBox.add(clearButton_);
		buttonBox.add(colorButton_);
		buttonBox.add(fillRectButton_);
		buttonBox.add(fillOvalButton_);
		buttonBox.add(keywordButton_);
		buttonBox.add(testFuncButton);

		Box mainBox = new Box(BoxLayout.Y_AXIS);
		mainBox.add(new OptionPanel(this, canvas_.getPropCanvas_(), "canvasPath"));
		mainBox.add(buttonBox);
		mainBox.add(textField_);
		mainBox.add(canvas_);

		getContentPane().add(mainBox);

		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		keyword_ = textField_.getText();
		if (e.getSource() == clearButton_) {
			clearAction();
		}
		if (e.getSource() == colorButton_) {
			colorAction();
		}
		if (e.getSource() == fillRectButton_) {
			fillRectAction();
		}
		if (e.getSource() == fillOvalButton_) {
			fillOvalAction();
		}
		if (e.getSource() == saveButton_) {
			saveAction(false);
		}
		if (e.getSource() == keywordButton_) {
			textField_.setText("");

			byKeywordAction(keyword_);
		}
		if(e.getSource() == testFuncButton) {
			testFuncAction();
		}
	}

	//contoroller
	public void byKeywordAction(String keyword) {
		if (keyword.contains("やり直し")) {
			undoAction();
		}

		if (keyword.contains("消す")) {
			clearAction();
		}

		if (keyword.contains("保存")) {
			saveAction(false);
		}

		if (keyword.contains("色")) {
			colorAction();
		} else {
			anyColorAction();
		}

		if (keyword.contains("四角")) {
			fillRectAction();
		} else if (keyword.contains("丸")) {
			fillOvalAction();
		} else if (keyword.contains("test")){
			drawFromFigFile("hara.txt");
		} else {
			anyShapeAction();
		}
	}

	private void undoAction() {
		history_.undo();
		canvas_.newOffScreen();
		history_.execute();
	}

	private void anyColorAction() { //色の指定がないとき
		//TODO: 特定の条件で RandomColor を呼ぶ
	}

	private void anyShapeAction() { //形の指定がないとき
		//TODO: 任意の形状を描画するメソッドを呼ぶ
	}

	private void fillOvalAction() {
		multiHistory_.clear(); //複数回
		for (int i = 0; i < Util.makeCount(keyword_); i++) {
			Point p = Util.makePoint(keyword_, width_, height_);
			Command cmd = new DrawFillOvalCommand(canvas_, p);
			multiHistory_.append(cmd);
		}
		multiHistory_.execute();
		history_.append(multiHistory_);
		canvas_.repaint();
	}

	private void fillRectAction() {
		multiHistory_.clear();
		for (int i = 0; i < Util.makeCount(keyword_); i++) { //複数回
			Point p = Util.makePoint(keyword_, width_, height_);
			Command cmd = new DrawFillRectCommand(canvas_, p, 10, 20);

			multiHistory_.append(cmd);
		}
		multiHistory_.execute();
		history_.append(multiHistory_);
	}

	private void colorAction() {
		Color color;

		color = byKeywordColor(keyword_);
		if (color == null) {
			color = randomColor(); //random に色作る
		}
		Command cmd = new ColorCommand(canvas_, color);
		history_.append(cmd);
		cmd.execute();
	}

	private Color randomColor() {
		int i = r_.nextInt(100);

		Color color = new Color(((i * 5) + 1) % 255, ((i * i) + 55) % 255, (i / 3 + i / 2 + 128) % 255); //randomColor

		Command cmd = new ColorCommand(canvas_, color);
		history_.append(cmd);
		cmd.execute();

		return color;
	}

	private Color byKeywordColor(String keyword) { //TODO:色追加可能なように別クラスへ
		Color color = null;
		int i = r_.nextInt(100); //add gradation
		if (keyword.contains("黒")) {
			color = Color.black;
		} else if (keyword.contains("赤")) {
			color = new Color(255, ((i * i) + 55) % 255, (i / 3 + i / 2 + 128) % 255); //randomColor
		} else if (keyword.contains("青")) {
			color = new Color(((i * i) + 55) % 255, (i / 3 + i / 2 + 128) % 255, 255); //randomColor
		}
		//TODO: キーワードで指定できる色を追加する

		if (color == null) {
			return null;
		}
		return color;
	}

	private void clearAction() {
		canvas_.setCanvasPath_(canvas_.getDefaultCanvasPath_());

		history_.clear();
		canvas_.repaint();
		canvas_.newOffScreen();
	}

	public void saveAction(boolean isUpdater) {
		//日時作成
		date_ = "-" + cal_.get(Calendar.YEAR) + cal_.get(Calendar.MONTH) + cal_.get(Calendar.DATE)
				+ cal_.get(Calendar.HOUR) + cal_.get(Calendar.MINUTE) + cal_.get(Calendar.SECOND);

		//リセットしてからfilepath作成
		canvas_.getFilePath().delete(0, canvas_.getFilePath().length());
		canvas_.getFilePath().append(canvas_.getFilePathDir() + canvas_.getFilePrefix());
		canvas_.getFilePath().append(date_);
		canvas_.getFilePath().append(canvas_.getFilePathSufix());

		canvas_.save(canvas_.getFilePath().toString()); //指定名で save 実行
	}

	private void testFuncAction() {
		String fileName = "test.fig";
		drawFromFigFile(fileName);
	}

	private void drawFromFigFile(String fileName) {
		ArrayList<ArrayList<String>> data;

		String dir = canvas_.getFigFileDir();
		try {
			CSVReader reader = new CSVReader(dir + fileName);
			data = reader.read();
		} catch(IOException e) {
			System.err.println("図形ファイルが見つかりません");
			return;
		}

		FigFileParser parser = new FigFileParser(canvas_, data);
		MacroCommand cmd = parser.parse();
		history_.append(cmd);
		cmd.execute();	
	}

	public void mouseDragged(MouseEvent e) {
		Command cmd = new DrawFillOvalCommand(canvas_, e.getPoint());
		history_.append(cmd);
		cmd.execute();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	public void windowClosing(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public String getKeyword() {
		return keyword_;
	}

	public void setKeyword(String keyword) {
		keyword_ = keyword;
	}

	public String getFilePathinString() {
		return canvas_.getFilePath().toString();
	}

	public DrawCanvas getCanvas() {
		return canvas_;
	}
}
