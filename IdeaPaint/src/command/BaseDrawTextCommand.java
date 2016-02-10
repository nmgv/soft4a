package command;

import java.awt.Font;

public class BaseDrawTextCommand implements Command {
	public static final int defaultFontSize = 12;
	public static final int defaultFontStyle = Font.PLAIN;
	private static final String defaultFontName = Font.SERIF;
	
	//描画対象
	protected Drawable drawable_;
	private String text;
	private int x, y, fontSize, fontStyle;

	//コンストラクタ
	public BaseDrawTextCommand(Drawable drawable, int x, int y, String text, int fontStyle, int fontSize){
		this.drawable_ = drawable;
		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = fontStyle & 3;
		this.fontSize = fontSize;
	}

	public BaseDrawTextCommand(Drawable drawable, int x, int y, String text, int fontSize){
		this.drawable_ = drawable;
		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = defaultFontStyle;
		this.fontSize = fontSize;
	}

	public BaseDrawTextCommand(Drawable drawable, int x, int y, String text){
		this.drawable_ = drawable;
		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = defaultFontStyle;
		this.fontSize = defaultFontSize;
	}
	
	//実行
	public void execute() {
		Font font = new Font(defaultFontName, fontStyle, fontSize);
		drawable_.baseDrawText(x, y, text, font);
	}
}
