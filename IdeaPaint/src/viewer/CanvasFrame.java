package viewer;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import util.Util;

import command.ColorCommand;
import command.Command;
import command.DrawCanvas;
import command.DrawCommand;
import command.DrawFillRectCommand;
import command.MacroCommand;

public class CanvasFrame extends JFrame implements ActionListener,
		MouseMotionListener, WindowListener {
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

	private Random r_ = new Random();

	private String keyword_ = "てすと";
	private StringBuffer info_ = new StringBuffer();

	private final int INFO_LENGTH = 120; //ここに URL 付けてツイートできる文字．

	private boolean isClearAction_ = false;
	private boolean isColorAction_ = false;
	private boolean isFillRectAction_ = false;
	private boolean isFillOvalAction_ = false;
	private boolean isSaveAction_ = false;

	private boolean isUndoAction = false;

	//複数回の描画
	private MacroCommand multiHistory_ = new MacroCommand();

	private String prefixTweet_ = "前";
	private String sufixTweet_ = "後";
	private String sufixOverTweet_ = "文字";

	private String allFalse_ = "命令なし";

	private String date_;

	private Calendar cal_ = Calendar.getInstance();

	private JTextField textField_ = new JTextField();

	private int actionNum_ = 3;

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

		Box buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(saveButton_);
		buttonBox.add(clearButton_);
		buttonBox.add(colorButton_);
		buttonBox.add(fillRectButton_);
		buttonBox.add(fillOvalButton_);
		buttonBox.add(keywordButton_);

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
	}

	//contoroller
	public void byKeywordAction(String keyword) {
		//リセット
		resetInfo();

		info_.append(prefixTweet_); //prefix をつける．

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
		} else {
			anyShapeAction();
		}

		//操作内容を表すテキスト生成
		createInfo(keyword);
	}

	private void undoAction() {
		isUndoAction = true;

		history_.undo();
		canvas_.newOffScreen();
		history_.execute();

		resetInfo();
		info_.append("やり直し");
	}

	private void anyColorAction() { //色の指定がないとき
		//TODO: 特定の条件で RandomColor を呼ぶ
	}

	private void anyShapeAction() { //形の指定がないとき
		//TODO: 任意の形状を描画するメソッドを呼ぶ
	}

	private void fillOvalAction() {
		isFillOvalAction_ = true;
		info_.append("丸");

		multiHistory_.clear(); //複数回
		for (int i = 0; i < Util.makeCount(keyword_); i++) {
			Point p = Util.makePoint(keyword_, width_, height_);
			Command cmd = new DrawCommand(canvas_, p);
			multiHistory_.append(cmd);
		}
		multiHistory_.execute();
		history_.append(multiHistory_);
		canvas_.repaint();
	}

	private void fillRectAction() {
		isFillRectAction_ = true;

		info_.append("四角");

		multiHistory_.clear();
		for (int i = 0; i < Util.makeCount(keyword_); i++) { //複数回
			Point p = Util.makePoint(keyword_, width_, height_);
			Command cmd = new DrawFillRectCommand(canvas_, p, r_.nextInt(3) + 1);

			multiHistory_.append(cmd);
		}
		multiHistory_.execute();
		history_.append(multiHistory_);
	}

	private void colorAction() {
		isColorAction_ = true;
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
			info_.append("黒で");
		} else if (keyword.contains("赤")) {
			color = new Color(255, ((i * i) + 55) % 255, (i / 3 + i / 2 + 128) % 255); //randomColor
			info_.append("赤で");
		} else if (keyword.contains("青")) {
			color = new Color(((i * i) + 55) % 255, (i / 3 + i / 2 + 128) % 255, 255); //randomColor
			info_.append("青で");
		}
		//TODO: キーワードで指定できる色を追加する

		if (color == null) {
			info_.append("その色は知らない");
			return null;
		}
		return color;
	}

	private void clearAction() {
		canvas_.setCanvasPath_(canvas_.getDefaultCanvasPath_());

		isClearAction_ = true;
		resetInfo();
		info_.append("消す");

		history_.clear();
		canvas_.repaint();
		canvas_.newOffScreen();
	}

	public void saveAction(boolean isUpdater) {
		if (!isUpdater) {
			isSaveAction_ = true;
		}
		resetInfo();
		info_.append("完成");

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

	public void createInfo(String keyword) {
		if (!isClearAction_ && !isSaveAction_ && !isUndoAction) { //特定のアクションのとき以外にデフォルトの sufix を付ける．
			if (info_.length() < INFO_LENGTH) { //info_ が規定より短ければ sufix を付与
				info_.append(sufixTweet_);
			} else { //info_ が規定より長ければ info_ を消してデフォルトの info_を作る．
				resetInfo();
				info_.append(sufixOverTweet_);
			}
		}

		resetAction(); //boolean リセット
	}

	private void resetAction() {
		//boolean をリセットする．
		isClearAction_ = false;
		isColorAction_ = false;
		isFillOvalAction_ = false;
		isFillRectAction_ = false;
		isSaveAction_ = false;

		isUndoAction = false;
	}

	public void resetInfo() {
		//消す
		info_.delete(0, info_.length());
	}

	public void mouseDragged(MouseEvent e) {
		Command cmd = new DrawCommand(canvas_, e.getPoint());
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

	public StringBuffer getInfo() {
		return info_;
	}

	public String getFilePathinString() {
		return canvas_.getFilePath().toString();
	}

	public DrawCanvas getCanvas() {
		return canvas_;
	}

}