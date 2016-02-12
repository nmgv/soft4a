/*
 * 基本文字列描画クラス
 * 
 * - 変数 -
 * defaultFontSize : 標準フォントサイズ
 * defaultFontStyle : 標準フォントスタイル
 * defaultFonaName : 標準フォント名
 * 
 * drawable_ : 描画対象
 * text_ : 描画する文字列
 * x_ : 左上の x 座標
 * y_ : 左上の y 座標
 * fontSize_ : フォントサイズ
 * fontStyle_ : フォントスタイル
 *  
 * - メソッド -
 * execute() : 実行(描画関数の呼び出し)
 * 
 */

package command;

import java.awt.Font;

public class BaseDrawTextCommand implements Command {
	public static final int defaultFontSize = 12;
	public static final int defaultFontStyle = Font.PLAIN;
	private static final String defaultFontName = Font.SERIF;
	
	protected Drawable drawable_;
	private String text_;
	private int x_, y_, fontSize_, fontStyle_;

	public BaseDrawTextCommand(Drawable drawable_, int x, int y, String text, int fontStyle, int fontSize){
		this.drawable_ = drawable_;
		this.x_ = x;
		this.y_ = y;
		this.text_ = text;
		this.fontStyle_ = fontStyle & 3;
		this.fontSize_ = fontSize;
	}
	
	public void execute() {
		Font font = new Font(defaultFontName, fontStyle_, fontSize_);
		drawable_.baseDrawText(x_, y_, text_, font);
	}
}
