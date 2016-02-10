package command;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import util.PropertiesReader;

public class DrawCanvas extends Canvas implements Drawable {
	//描画色
	private Color color_ = Color.red;

	private BufferedImage readImage_ = null;

	private Graphics g_;

	private Graphics off_;

	//layer1 --背景
	private BufferedImage layer1_ = null;
	private Graphics l1g_;

	//layer2 --パーツ関係（背景以外）
	private BufferedImage layer2_ = null;

	//描画する点の半径
	private int radius_ = 6;
	//履歴
	private MacroCommand history_;

	private String wakuFilePath_;
	private String defaultCanvasPath_;
	private String canvasPath_;

	private String filePathDir_;
	private String filePathPrefix_;
	private String filePathSufix_;

	private StringBuffer filePath_ = new StringBuffer();

	private PropertiesReader propCanvas_ = new PropertiesReader("./conf/canvas.properties");

	//コンストラクタ
	public DrawCanvas(int width, int height, MacroCommand history) {
		setSize(width, height);
		setBackground(Color.white);
		this.history_ = history;

		init();
	}

	private void init() {
		propCanvas_.readProperities();

		canvasPath_ = propCanvas_.getProp().getProperty("canvasPath");
		wakuFilePath_ = propCanvas_.getProp().getProperty("wakuPath");
		defaultCanvasPath_ = propCanvas_.getProp().getProperty("defaultCanvasPath_");
		filePathDir_ = propCanvas_.getProp().getProperty("filePathDir");
		filePathPrefix_ = propCanvas_.getProp().getProperty("filePrefix");
		filePathSufix_ = propCanvas_.getProp().getProperty("fileSufix");

		try {
			if (canvasPath_ == null) { //default
				layer1_ = ImageIO.read(new File(defaultCanvasPath_));
			} else {
				layer1_ = ImageIO.read(new File(canvasPath_));
			}

		} catch (Exception e) {
			e.printStackTrace();
			layer1_ = null;
		}

		if (readImage_ == null) {
			readImage_ = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_BGR);
		}

		if (layer1_ != null) { //ファイルがあれば描画
			l1g_ = layer1_.createGraphics();
			l1g_.drawImage(layer1_, 0, 0, null);
			off_ = readImage_.createGraphics();
			off_.drawImage(layer1_, 0, 0, null); //off の担当する readImage に layer1 を描く
			off_.drawImage(readImage_, 0, 0, this); //off からキャンバスに readImage を描く

			layer2_ = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			off_ = layer2_.createGraphics(); //off に layer2 を担当させる
		}

		if (layer1_ == null) {
			layer1_ = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_BGR);
		}
	}

	public void newOffScreen() {
		init();
	}

	//履歴全体を再描画
	public void paint(Graphics g) {
		history_.execute();
	}

	//描画
	public void draw(int x, int y) {
		off_.setColor(color_);
		off_.fillOval(x - radius_, y - radius_, radius_ * 2, radius_ * 2);
		update();
	}

	//四角を描画
	public void drawFillRect(int x, int y, int length) {
		off_.setColor(color_);
		off_.fillRect(x - length, y - length, length * 2, length * 2);
		update();
	}

	//色セット
	public void setColor(Color color) {
		this.color_ = color;
	}

	public void update() {
		if (layer2_ != null) {
			off_.drawImage(layer2_, 0, 0, null); //現在の保持内容（layer2 への graphic）を layer 2 に書き込む
			off_ = readImage_.createGraphics(); //readImage を担当させる
			off_.drawImage(layer1_, 0, 0, null); //off の担当する readImage に layer1 を描く
			off_.drawImage(layer2_, 0, 0, null); //off の担当する readImage に layer2 を描く

			off_ = layer2_.createGraphics(); //layer2 を担当させる //セットし直す

			g_ = getGraphics();
			g_.drawImage(readImage_, 0, 0, this);
		}
	}

	public void save(String filepath) {

		propCanvas_.getProp().setProperty("canvasPath", filepath);

		try {
//			boolean result = ImageIO.write(readImage, "png", new File(filepath));
			boolean result = ImageIO.write(readImage_, "png", new File("test.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringBuffer getFilePath() {
		return filePath_;
	}

	public void setFilePath(StringBuffer filePath) {
		filePath_ = filePath;
	}

	public String getFilePathDir() {
		return filePathDir_;
	}

	public String getFilePrefix() {
		return filePathPrefix_;
	}

	public String getFilePathSufix() {
		return filePathSufix_;
	}

	public String getWakuFilePath() {
		return wakuFilePath_;
	}

	public BufferedImage getReadImage() {
		return readImage_;
	}

	//透明レイヤーのみ
	public BufferedImage getLayer2() {
		return layer2_;
	}

	public void setCanvasPath_(String canvasPath_) {
		this.canvasPath_ = canvasPath_;
	}

	public String getCanvasPath_() {
		return canvasPath_;
	}

	public String getDefaultCanvasPath_() {
		return defaultCanvasPath_;
	}

	public PropertiesReader getPropCanvas_() {
		return propCanvas_;
	}

}
