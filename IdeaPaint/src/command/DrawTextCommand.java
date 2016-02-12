/*
 * 基本文字列描画クラス
 * 
 * - 変数 -
 * text_ : 描画する文字列
 * x_ : 左上の x 座標
 * y_ : 左上の y 座標
 * fontSize_ : フォントサイズ
 * fontStyle_ : フォントスタイル
 *  
 */

package command;

import java.awt.Color;

public class DrawTextCommand extends AbstractDrawShapeCommand {
	private String text_;
	private int x_, y_, fontSize_, fontStyle_;

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text, int fontStyle, int fontSize) {
		super(drawable_, fillColor, null, 0);

		this.x_ = x;
		this.y_ = y;
		this.text_ = text;
		this.fontStyle_ = fontStyle;
		this.fontSize_ = fontSize;
	}

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text, int fontSize) {
		super(drawable_, fillColor, null, 0);

		this.x_ = x;
		this.y_ = y;
		this.text_ = text;
		this.fontStyle_ = BaseDrawTextCommand.defaultFontStyle;
		this.fontSize_ = fontSize;
	}

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text) {
		super(drawable_, fillColor, null, 0);

		this.x_ = x;
		this.y_ = y;
		this.text_ = text;
		this.fontStyle_ = BaseDrawTextCommand.defaultFontStyle;
		this.fontSize_ = BaseDrawTextCommand.defaultFontSize;
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new BaseDrawTextCommand(drawable_, x_, y_, text_, fontStyle_, fontSize_);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		return null;
	}
}
